package utils.imageUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import constants.Constants;

public class ImagePanel extends JPanel {
	private Graphics g;
	ArrayList<GraphPixel> pxs = new ArrayList<>();
	ArrayList<GraphPixel> pxsToAdd = new ArrayList<>();
	private int widthImage;
	private int heightImage;

	public ImagePanel(int widthImage, int heightImage) {
		this.widthImage = widthImage;
		this.heightImage = heightImage;
	//	this.setBackground(Color.black);

	}

	public void paint(Graphics g) {

		super.paint(g);

		synchronized (this) {
			g.setFont(Constants.FONT.deriveFont(11f));
			g.drawString("Alpha", 20, 520);
			g.drawString("With real colors", 620, 520);
			g.drawString("In figures", 1220, 520);
			for (GraphPixel p : pxs) {

				if (p.isDib()) {
					g.setColor(Color.white);
					g.drawRect(20 + p.getX(), 20 + p.getY(), 1, 1);
				} else {

					g.drawRect(20 + p.getX(), 20 + p.getY(), 1, 1);
				}
				g.setColor(Color.black);
				g.drawRect(600 + p.getX(), 20 + p.getY(), 1, 1);
				if (p.isDib()) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setColor(p.getC());

					g2.drawRect(600 + p.getX(), 20 + p.getY(), 1, 1);
				} else {

				}
				g.setColor(Color.black);
				g.drawRect(20 + 1200 + p.getX(), 20 + p.getY(), 1, 1);

			}	
			for (Figura f : ImageUtils.figuras) {
				g.setColor(f.getC());
				g.drawRect(20 + 1200 + f.getX(), 20 + f.getY(), f.getWidth(), f.getHeight());
			}
			g.setColor(Color.BLACK);
			
			int borderX = 40;
			int borderY = 20;
			int x = borderX;
			int y = 600;
			DecimalFormat df = new DecimalFormat("0.######");
			if (ImageUtils.figurasFiltradas != null && ImageUtils.figurasFiltradas.size() != 0) {
				int posibilites= (widthImage * heightImage) * (widthImage * heightImage);
			
				String probability=df.format((ImageUtils.figuras.size()*100)/(float)posibilites);
				for (int i = 0; i < ImageUtils.figurasFiltradas.size(); i++) {
					g.drawString("Detected "+ ImageUtils.figurasFiltradas.size() +" subimages of the image " + "out of "
							+ posibilites + " (" +probability+"%) posibilities. ERROR: Possible figures: "
							+ ImageUtils.figuras.size()+" Aumented precision by: "+df.format((((ImageUtils.figuras.size()-ImageUtils.figurasFiltradas.size())/(double)ImageUtils.figuras.size())*100))+"%", 50, 550);
					g.setColor(Color.BLACK);

					g.drawString("id " + i, x, y - borderY);
					g.drawString(
							ImageUtils.figurasFiltradas.get(i).getWidth() + " x "
									+ ImageUtils.figurasFiltradas.get(i).getHeight(),
							x, y + ImageUtils.figurasFiltradas.get(i).getHeight() + borderY);
					g.drawImage(ImageUtils.figurasFiltradas.get(i).getI(), x, y, null);
					g.drawRect(x, y, ImageUtils.figurasFiltradas.get(i).getWidth(),
							ImageUtils.figurasFiltradas.get(i).getHeight());
					if (x >= 1700) {
						x = borderX;
						y += 200;
					} else {
						x += ImageUtils.figurasFiltradas.get(i).getI().getWidth(null) + borderX;
					}

				}
			} else {
				g.drawString("WAITING FOR RENDER Total figures: "+ImageUtils.figuras.size()+" Completed: "+df.format(ImageUtils.checkedPixels)+"%", 50, 550);
			}

		}

		repaint();
	}

	public void paintPixel(int x, int y, Color c) {
		synchronized (this) {
			pxs.add(new GraphPixel(x, y, c, false));
		}

	}

	public void editPixel(int x, int y, int pos) {

		pxs.set(pos, new GraphPixel(x, y, pxs.get(pos).getC(), true));

	}

	public void changeColorFigure(Color c) {
		g.setColor(c);
	}
}

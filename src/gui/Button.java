package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import constants.Constants;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import juegoEspacio.PanelDibujo;

public class Button extends JButton {
	BufferedImage i;
	private int x2;
	private int y2;
	BufferedImage bi;
	ImageIcon ic, ic2;

	public Button(int x, int y) {
		this.setVisible(false);
		BufferedImage img;
		try {
			img = ImageIO.read(new File(Constants.imagesPath+"button.jpg"));
			int w = img.getWidth(null);
			int h = img.getHeight(null);
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ic = new ImageIcon(Constants.imagesPath+"button.jpg");
		ic2 = new ImageIcon(Constants.imagesPath+"button2.png");
		this.setIcon(ic);
	
	

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	
		this.setSize(ic.getIconWidth(), ic.getIconHeight());
		this.setLocation(x, y);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				IAGenerator.characters.remove(IAGenerator.mainPlayer);
				
				GameHandler.getVentana().getPanelActual().resetElements();
				GameHandler.getVentana().getPanelActual().inicializarPartida();
				GameHandler.getVentana().getPanelActual().getPanelLife().restart();
				GameHandler.getVentana().requestFocus();
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Button.this.setIcon(ic);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Button.this.setIcon(ic2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

}

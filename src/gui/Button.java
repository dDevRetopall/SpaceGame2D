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
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import constants.Constants;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import juegoEspacio.PanelDibujo;

public abstract class Button extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage i;
	private int x2;
	private int y2;
	BufferedImage bi;
	ImageIcon ic, ic2;

	public Button(int x, int y,String f,String text) {
		this.setVisible(false);
		this.setOpaque(true);
		this.setBorder(new MatteBorder(2,2,2,2,Color.black));
		this.setContentAreaFilled(false);
		Image i;
		try {
			i = ImageIO.read(new File(f));
			i=i.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon ii = new ImageIcon(i);
			
		
			this.setIcon(ii);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	
		this.setFont(Constants.FONT);
		this.setText("  "+text);
		this.setSize(150,75);
		this.setLocation(x, y);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				doAction();
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Button.this.setBorder(new MatteBorder(2,2,2,2,Color.black));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Button.this.setBorder(new MatteBorder(2,2,2,2,Color.gray));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	public abstract void doAction();
		
	
}

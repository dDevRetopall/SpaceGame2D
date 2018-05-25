package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import graphics.shooting.Bomba;
import juegoEspacio.GameHandler;

public class BuildPanel extends JPanel {
	private Bomba b;
	boolean alive = true;
	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	JPanel pInfo= new JPanel();
	StuffPanel sp;
	public BuildPanel() {
		 sp= new StuffPanel();
		this.setLayout(new GridLayout(2, 1));
		
		
		pInfo.setLayout(new GridLayout(20,1));
		this.setBounds(GameHandler.getVentana().screenSize.width-250, 250, 240, 500);
		JButton button = new JButton("Hola");
		JButton button2 = new JButton("Hola2");
	
		l2.setSize(200, 200);
		
		l1.setText("BUILD PANEL");
		
		
		l3.setText("Total defenses");
		
		l1.setFont(Constants.FONT);
		l1.setForeground(Color.GRAY);
		l2.setFont(Constants.FONT);
		l2.setForeground(Color.GRAY);
		l3.setFont(Constants.FONT);
		l3.setForeground(Color.GRAY);
		l4.setFont(Constants.FONT);
		l4.setForeground(Constants.colorBomb);

		pInfo.add(l1);
		pInfo.add(l2);
		pInfo.add(l3);
		pInfo.add(l4);
	
		pInfo.setOpaque(false);
		this.setOpaque(false);
		this.add(pInfo );
		this.add(sp);
	}
	public void  putRemoveMessage(boolean accept) {
		if(accept) {
		l4.setText("R TO REMOVE");
		}else {
			l4.setText("");
		}
		
	}
	public StuffPanel getStuffPanel() {
		return sp;
	}
	public void setSp(StuffPanel sp) {
		this.sp = sp;
	}
	
	
}

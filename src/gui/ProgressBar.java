package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ProgressBar extends JProgressBar{
	
	public ProgressBar(int lenght,boolean multicolor) {
	
		this.setStringPainted(true);
		this.setString("");
		this.setValue(0);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 76, 153)));
		this.setForeground(Color.GREEN);
		
	}
	public ProgressBar(int lenght,int width,int height) {
		
		this.setStringPainted(true);
		this.setString("");
		this.setValue(0);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 153, 0)));
		this.setForeground(Color.GREEN);
		this.setValue(100);
		
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
}

package juegoEspacio;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import constants.Constants;
import gui.ProgressBar;
import ia.Motor;

public class Status{
	int idealenght = 6;
	public static JPanel pStatus ;
	public static HashMap<ProgressBar, JLabel>paneles = new HashMap<>();
	public static void initStatus() {
		pStatus= new JPanel();
		pStatus.setSize(425, 275);
		pStatus.setLayout(new BoxLayout(pStatus, BoxLayout.Y_AXIS));
		pStatus.setOpaque(false);
		
		
	}
	public static ProgressBar addProgressBar(String text) {
		JLabel l = new JLabel();
		l.setForeground(Color.WHITE);
		l.setFont(Constants.FONT);
		l.setText(text);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

		p.setOpaque(false);
		p.add(l);
		ProgressBar pb = new ProgressBar(100, true);
		p.add(pb);
		pStatus.add(p);
		paneles.put(pb, l);
		pStatus.updateUI();
		return pb;
	}

	public static ProgressBar addMotorStatus(int id) {
		String name="";
		int spaces=0;
		if (id == Motor.DOWN_SIDE) {
			name="Down";
			spaces=3;
		}
		if (id == Motor.UP_SIDE) {
			name="Upper";
			spaces=2;
		}
		if (id == Motor.RIGHT_SIDE) {
			name="Right";
			spaces=4;
		}
		if (id == Motor.LEFT_SIDE) {
			name="Left";
			spaces=7;
		}

		JLabel l = new JLabel();
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Arial", Font.BOLD, 16));
		l.setText(name + " motor");

		for (int i = 0; i < spaces; i++) {
			l.setText(l.getText() + " ");
		}
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

		p.setOpaque(false);
		p.add(l);
		ProgressBar pb = new ProgressBar(100, true);
		p.add(pb);
		pStatus.add(p);
		paneles.put(pb, l);
		pStatus.updateUI();
		return pb;
	}


	public static HashMap<ProgressBar, JLabel> searchPaneles() {
		return paneles;
	}
	
	
	
}

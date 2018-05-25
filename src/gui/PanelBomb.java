package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import graphics.shooting.Bomba;
import ia.IAGenerator;
import juegoEspacio.GameHandler;

public class PanelBomb extends JPanel {
	private Bomba b;
	boolean alive=true;
	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	public PanelBomb(Bomba b, int posicion) {
		this.b = b;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		

		JButton button = new JButton("Detonar");
		JButton button2 = new JButton("Desactivar");
		l1.setText("Bomba " + posicion);

		l1.setFont(Constants.FONT);
		l1.setForeground(Color.GRAY);
		l2.setFont(Constants.FONT);
		l2.setForeground(Constants.colorBomb);
		l3.setFont(Constants.FONT);
		l3.setForeground(Color.WHITE);
		l4.setFont(Constants.FONT);
		l4.setForeground(Color.WHITE);
		l2.setText("CONFIGURING BOMB");
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(button);
		this.add(button2);
		button.setOpaque(false);
		button2.setOpaque(false);
		this.setOpaque(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(b.isActivated()) {
				if (b.isDetonable()) {
					alive=false;
					b.detonarManualmente();
					IAGenerator.mainPlayer.getCañon().getBombas().remove(b);
					
				} else {
					GameHandler.getVentana().getPanelActual().changeMessage("La bomba no se puede detonar");
				}
				}else {
					GameHandler.getVentana().getPanelActual().changeMessage("La bomba esta desactivada y es indetectable");
				}
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (button2.getText().equals("Desactivar")) {
					if(b.isDetonable()) {
					b.desactivate();
					l2.setText("DEACTIVATED");
					button2.setText("Activar");
					
					}else {
						GameHandler.getVentana().getPanelActual().changeMessage("La bomba se esta configurando");
					}

				} else {
					button2.setText("Desactivar");
					l2.setText("DETECTING ENEMIES");
					
					b.activate();
				}

			}
		});
	
	}
	public JLabel getL1() {
		return l1;
	}
	public JLabel getL2() {
		return l2;
	}
	

}

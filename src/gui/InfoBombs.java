package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import graphics.shooting.Bomba;
import juegoEspacio.GameHandler;

public class InfoBombs extends JPanel{
	ArrayList<PanelBomb>paneles = new ArrayList<>();
	public InfoBombs() {
		this.setBounds(10,GameHandler.getVentana().screenSize.height/2-200,200,500);
		this.setLayout(new GridLayout(5, 1));
		this.setOpaque(false);
		
	}
	public int addBombEntity(Bomba b) {
		PanelBomb pb=new PanelBomb(b,paneles.size()+1);
		
		this.add(pb);
		this.updateUI();
		 paneles.add(pb);
		 return paneles.size()-1;
		
	}
	public ArrayList<PanelBomb> getPaneles() {
		return paneles;
	}
	

}

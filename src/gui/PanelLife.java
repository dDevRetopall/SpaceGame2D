package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import constants.Constants;
import graphics.fireworks.FuegoArtificial;
import ia.Engine;
import ia.IAGenerator;
import juegoEspacio.Main;

public class PanelLife extends JPanel {
	int width = 500;
	
	MainProgressBar pb = new MainProgressBar(100, new Color(0, 200, 0), new Color(0, 153, 0), 400, 25,100,"health");
	MainProgressBar pb2 = new MainProgressBar(100, new Color(0, 0, 100), new Color(0, 0, 153), 400, 25,0,"shield");
	JPanel pLife = new JPanel();
	JPanel pShield = new JPanel();
	JLabel shield = new JLabel("Shield: ");
	JLabel health = new JLabel("Health: ");
	boolean c = false;

	public PanelLife() {
		
		shield.setFont(Constants.FONT);
		health.setFont(Constants.FONT);
		health.setForeground(Color.WHITE);
		shield.setForeground(Color.white);
		pLife.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pShield.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(pLife);
		this.setLocation(Main.getVentana().screenSize.width / 2 - width / 2, Main.getVentana().screenSize.height - 90);
		this.setSize(width, 80);
		this.setOpaque(false);
		pLife.setOpaque(false);
		pShield.setOpaque(false);
		pLife.add(health);
		pLife.add(pb);
		pShield.add(shield);
		pShield.add(pb2);

		this.add(pLife);
		this.add(pShield);
		pb.setValue(100);
		pb2.setValue(0);
		pb2.initialize();
		pb.initialize();

	}

	public void makeDamage(int damage,FuegoArtificial fa) {
		//Si fa es null no se genera fuego articial
		if (((pb2.getPointsOfLife() + pb.getPointsOfLife()) <= damage)) {
			IAGenerator.mainPlayer.muerto = true;
			pb.resetValue();
			pb2.resetValue();
			Engine.explosion = new FuegoArtificial(fa.getX(),fa.getY(),fa.getC(),5f,2000);
		} else {
			float xP = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
			float yP = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
			if (Engine.explosion == null) {

				Engine.explosion = fa;
			} else if (Engine.explosion.hasFinished()) {
				Engine.explosion = fa;
			}
			
			if (pb2.getPointsOfLife() > 0) {
				int restante = pb2.getPointsOfLife() - damage;
				if (restante <= 0) {
					pb.quitHealth(damage - pb2.getPointsOfLife());
					pb2.resetValue();

				} else {
					pb2.quitHealth(damage);
				}
			} else {
				pb.quitHealth(damage);
			}
		}
		

	}

	public void restart() {
		pb.editValue(100);
		pb2.editValue(0);
	}

	public MainProgressBar getHealthBar() {
		return pb;
	}

	public MainProgressBar getShieldBar() {
		return pb2;
	}

}

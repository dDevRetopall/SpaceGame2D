package graphics.shooting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;

import javax.swing.JPanel;

import org.omg.Messaging.SyncScopeHelper;

import constants.Constants;
import graphics.builds.Ladrillo;
import graphics.builds.LadrilloConColision;
import graphics.fireworks.FuegoArtificial;
import gui.HealthPanel;
import gui.ProgressBar;
import ia.Engine;
import ia.IAGenerator;
import ia.Player;
import juegoEspacio.Main;
import vector.OperacionesVectores;
import vector.Vector;

public class Bomba {
	float radio;
	float vx;
	float vy;
	private double a;
	int i = 0;

	public int heightPlayer;
	public int widthPlayer;
	public float y;
	public float x;
	public int heightBall;
	public int widthBall;
	long initialTime;
	boolean alive = true;
	public Color actualColor;
	boolean detonable = false;
	int id = 0;
	boolean activated = true;
	HealthPanel hp;

	public Bomba(int widthBall, int heightBall, float xPlayer, float yPlayer, int widthPlayer, int heightPlayer,
			float vx, float vy) {
		this.widthBall = widthBall;
		this.heightBall = heightBall;
		this.x = xPlayer;
		this.y = yPlayer;
		this.widthPlayer = widthPlayer;
		this.heightPlayer = heightPlayer;
		this.vx = vx;
		this.vy = vy;
		this.i = 0;
		initialTime = System.currentTimeMillis();
		hp = new HealthPanel((int) x, (int) (y), true);
		Main.getVentana().getPanelActual().add(hp);
		Main.getVentana().getPanelActual().updateUI();

		id = Main.getVentana().getPanelActual().getInfoBombsPanel().addBombEntity(this);

	}

	public void mover(float vx2, float vy2) {
		this.x = this.x + vx + vx2;
		this.y = this.y + vy + vy2;

		if (System.currentTimeMillis() - initialTime >= 2000) {
			alive = false;
		}
		if (System.currentTimeMillis() - initialTime >= 2000) {
			if (!detonable) {
				Main.getVentana().getPanelActual().getInfoBombsPanel().getPaneles().get(id).getL2()
						.setText("DETECTING ENEMIES");
				detonable = true;
				hp.setProgressIndeterminate(false);
			}
		}
		hp.editLocationBomba((int) x, (int) y);
	}

	public boolean checkDetona(Player p) {

		if (detonable && activated) {
			Vector v = OperacionesVectores.generarVector((x) - widthBall / 2, (y) - heightBall / 2, IAGenerator.mainPlayer.realXPlayerCoordinate,
					IAGenerator.mainPlayer.realYPlayerCoordinate);
			double v2 = OperacionesVectores.moduloVector(v);
			if (v2 < Constants.ratio) {
				Engine.fuegosArtificiales.add(new FuegoArtificial((x), (y), Constants.colorBomb, 4, 2000));
				Main.getVentana().getPanelActual().getInfoBombsPanel()
						.remove(Main.getVentana().getPanelActual().getInfoBombsPanel().getPaneles().get(id));

				Main.getVentana().getPanelActual().remove(hp);
				Main.getVentana().getPanelActual().updateUI();

				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}
	}

	// public boolean checkChoqueConLadrillo(Ladrillo l) {
	// if (detonable && activated) {
	// Vector v = OperacionesVectores.generarVector(l.x + l.ancho / 2, l.y + l.alto
	// / 2,
	// this.x + this.widthBall / 2, this.y + this.heightBall / 2);
	// double v2 = OperacionesVectores.moduloVector(v);
	// System.out.println(v2);
	// if (v2 < Constants.ratio) {
	// Engine.fuegosArtificiales.add(new FuegoArtificial((x), (y),
	// Constants.colorBomb, 4, 2000));
	// Main.getVentana().getPanelActual().getInfoBombsPanel()
	// .remove(Main.getVentana().getPanelActual().getInfoBombsPanel().getPaneles().get(id));
	//
	// Main.getVentana().getPanelActual().remove(hp);
	// Main.getVentana().getPanelActual().updateUI();
	//
	// return true;
	// } else {
	//
	// return false;
	// }
	// } else {
	//
	// return false;
	// }
	// }

	public void detonarManualmente() {
		
		for (LadrilloConColision l : Main.getVentana().getPanelActual().ladrillos) {
		
			Vector v = OperacionesVectores.generarVector(
					
					x,
					y, l.x-IAGenerator.mainPlayer.getX()+l.ancho/2,
					l.y-IAGenerator.mainPlayer.getY()+l.alto/2);
			if (OperacionesVectores.moduloVector(v) <= Constants.radioBomba) {
				l.getHp().quitHealth(100);
				Engine.updateLadrillo(l);
				
			}
		}
		Engine.fuegosArtificiales.add(new FuegoArtificial((x), (y), Constants.colorBomb, 4, 2000));
		Main.getVentana().getPanelActual().getInfoBombsPanel()
				.remove(Main.getVentana().getPanelActual().getInfoBombsPanel().getPaneles().get(id));

		Main.getVentana().getPanelActual().remove(hp);
		Main.getVentana().getPanelActual().updateUI();
	}

	public boolean checkChoqueConBala() {
		// Pendiente a hacer
		return true;

	}

	public void desactivate() {
		activated = false;
	}

	public void activate() {
		activated = true;
	}

	public void dibujar(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double radian = 0.017453292519943295;
		int rojo = (int) ((Math.cos((double) this.i * radian) + 1.0) * 255.0 / 2.0);
		int negro = 0;
		int t = (int) ((Math.sin((double) this.i * radian) + 1.0) * 255.0 / 2.0);
		this.i += 5;
		if (Constants.DEBUG) {
			System.out.println("Bomb X: " + x + " Y: " + y);
		}

		if (activated) {
			actualColor = new Color(rojo, negro, negro, 255);
		} else {
			actualColor = new Color(153, 0, 0, 255);
		}
		Color[] colors = { actualColor, new Color(0, 0, 0, 0) };
		Paint paint = null;

		paint = new RadialGradientPaint((int) (x), (int) ((y)), widthBall, Constants.FRACTIONS, colors);
		g2.setPaint(paint);

		g2.fillOval((int) (x) - widthBall / 2, (int) ((y) - heightBall / 2), widthBall, heightBall);
		g.setColor(Color.BLACK);
		g.drawOval((int) (x) - widthBall / 2, (int) ((y) - heightBall / 2), widthBall, heightBall);
		if (detonable && activated) {
			g2.setPaint(paint);
			g2.fillOval((int) (x) - 200 / 2, (int) ((y) - 200 / 2), 200, 200);
		}
		if (Constants.DEBUG) {
			g.setColor(Color.red);
			g.drawLine((int) IAGenerator.mainPlayer.realXPlayerCoordinate, IAGenerator.mainPlayer.realYPlayerCoordinate,
					(int) x, (int) y);
			g.fillOval((int) IAGenerator.mainPlayer.getX(), (int) IAGenerator.mainPlayer.getY(), 50, 50);
		}

		this.a += 0.05;
	}

	public HealthPanel getProgressBarPanel() {
		return hp;
	}

	public long getInitialTime() {
		return initialTime;
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean isActivated() {
		return activated;
	}

	public boolean isDetonable() {
		return detonable;
	}

}

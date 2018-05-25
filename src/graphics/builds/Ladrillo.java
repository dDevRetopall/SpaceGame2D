package graphics.builds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;

import constants.Constants;
import graphics.shooting.Bala;
import gui.HealthPanel;
import ia.IAGenerator;
import ia.Player;
import juegoEspacio.GameHandler;
import juegoEspacio.Ventana;
import mapGenerator.Vec2;

public class Ladrillo {
	public Color c;
	private Color c2;
	public float y;
	public float x;
	public int ancho;
	public int alto;
	private Paint rp;
	HealthPanel hp;
	boolean initializeProgress = false;
	private boolean visible;
	public long initialCounter = 0;
	public boolean indestructible = false;
	private boolean building;
	private Vec2 v;

	public Ladrillo(float x, float y, Color c,Vec2 v) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.v = v;
		this.ancho = Constants.sizeBlocks;
		this.alto = Constants.sizeBlocks;
		//xPlayer=0, yPlayer=0;
		hp = new HealthPanel((int) (x), (int) (y),
				false, 100, 25);

	}

	public Ladrillo(float x, float y, Color c, boolean building,Vec2 v) {
		this.x = x;
		this.y = y;
		this.building = building;
		this.c = c;
		this.v = v;
		this.ancho = Constants.sizeBlocks;
		this.alto = Constants.sizeBlocks;
		hp = new HealthPanel((int) (x - IAGenerator.mainPlayer.getX()), (int) (y - IAGenerator.mainPlayer.getY()), 0,
				100, 25);
		if (building) {
			hp.startCountToBuild();
			putProgressVisible(true);
		}

	}

	public void putProgressVisible(boolean visible) {
		this.visible = visible;

		if (visible) {
			GameHandler.getVentana().getPanelActual().add(hp);
			GameHandler.getVentana().getPanelActual().updateUI();
			initialCounter = System.currentTimeMillis();
		} else {
			GameHandler.getVentana().getPanelActual().remove(hp);
			GameHandler.getVentana().getPanelActual().updateUI();
		}

		initializeProgress = true;
	}

	public void renderWall(Graphics g, float xPlayer, float yPlayer) {

		Graphics2D g2 = (Graphics2D) g;
		this.c2 = this.c.darker();
		g2.setColor(this.c2);
		if (Constants.levelGraphical >= 0) {
			g2.fillRoundRect((int) (this.x + xPlayer), (int) (this.y + yPlayer), (int) this.ancho, (int) this.alto,
					(int) (this.ancho / 5.0f), (int) (this.alto / 5.0f));
			g2.fillRoundRect((int) (this.x + xPlayer + 4.0f), (int) (this.y + yPlayer + 4.0f), (int) this.ancho,
					(int) this.alto, (int) (this.ancho / 5.0f), (int) (this.alto / 5.0f));
		}
		if (Constants.levelGraphical >= 2) {
			g2.setColor(this.c);
		}
		if (Constants.levelGraphical >= 4) {
			this.rp = new RadialGradientPaint(this.x + xPlayer + 2.0f + this.ancho / 3.0f,
					this.y + yPlayer + 2.0f + this.alto / 3.0f, this.ancho, new float[] { 0.0f, 0.5f },
					new Color[] { this.c2.darker(), this.c });
			g2.setPaint(this.rp);
		}
		if (Constants.levelGraphical >= 3) {

			g2.fillRoundRect((int) (this.x + xPlayer + 2.0f), (int) (this.y + yPlayer + 2.0f),
					(int) (this.ancho - 4.0f), (int) (this.alto - 4.0f), (int) (this.ancho / 5.0f),
					(int) (this.alto / 5.0f));
		}
		this.c2 = this.c2.darker();
		if (indestructible) {
			c2 = Color.BLACK;
		}
		if (Constants.levelGraphical >= 1) {
			g2.setColor(this.c2);
			g2.fillOval((int) (this.x + xPlayer + (this.ancho - 4.0f) / 4.0f),
					(int) (this.y + yPlayer + (this.ancho - 4.0f) / 4.0f), (int) (this.ancho / 10.0f),
					(int) (this.ancho / 10.0f));
			g2.fillOval((int) (this.x + xPlayer + 3.0f * (this.ancho - 4.0f) / 4.0f),
					(int) (this.y + yPlayer + (this.ancho - 4.0f) / 4.0f), (int) (this.ancho / 10.0f),
					(int) (this.ancho / 10.0f));
			g2.fillOval((int) (this.x + xPlayer + (this.ancho - 4.0f) / 4.0f),
					(int) (this.y + yPlayer + 3.0f * (this.ancho - 4.0f) / 4.0f), (int) (this.ancho / 10.0f),
					(int) (this.ancho / 10.0f));
			g2.fillOval((int) (this.x + xPlayer + 3.0f * (this.ancho - 4.0f) / 4.0f),
					(int) (this.y + yPlayer + 3.0f * (this.ancho - 4.0f) / 4.0f), (int) (this.ancho / 10.0f),
					(int) (this.ancho / 10.0f));
		}
		// if (initializeProgress) {
		// GameHandler.getVentana().getPanelActual().add(hp);
		//
		// GameHandler.getVentana().getPanelActual().updateUI();
		// initializeProgress = false;
		//
		// }

		if (visible) {
			if (!hp.isCounting()) {
				if (System.currentTimeMillis() - initialCounter >= Constants.timeToDisappearProgressBarLadrillos) {
					putProgressVisible(false);
				}
			}
			hp.editLocationLadrillo((int) (-IAGenerator.mainPlayer.getX() + this.x),
					(int) (-IAGenerator.mainPlayer.getY() + this.y));
			GameHandler.getVentana().getPanelActual().updateUI();

		}
	}

	public boolean isProgressBarVisible() {
		return visible;
	}

	public HealthPanel getHp() {
		return hp;
	}

	public Vec2 getInitialVector() {
		return v;
	}
	

}

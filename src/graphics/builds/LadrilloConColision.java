package graphics.builds;

import java.awt.Color;

import constants.Constants;
import graphics.fireworks.FuegoArtificial;
import graphics.shooting.Bala;
import gui.HealthPanel;
import ia.Engine;
import ia.IAGenerator;
import ia.Player;
import juegoEspacio.Main;
import mapGenerator.Vec2;

public class LadrilloConColision extends Ladrillo {


	private Vec2 v;
	private boolean building=false;

	public LadrilloConColision(float x, float y, Color c, Vec2 v) {
		super(x, y, c,v);
		this.v = v;
		if (c.getRed() == Constants.indestructibleColor.getRed() && c.getBlue() == Constants.indestructibleColor.getBlue() && c.getGreen() == Constants.indestructibleColor.getGreen()) {
			indestructible = true;
		}

	}
	public LadrilloConColision(float x, float y, Color c, boolean building,Vec2 v) {
		super(x, y, c,building,v);
		this.building=building;
		this.v = v;
		if (c.getRed() == Constants.indestructibleColor.getRed() && c.getBlue() == Constants.indestructibleColor.getBlue() && c.getGreen() == Constants.indestructibleColor.getGreen()) {
			indestructible = true;
		}

	}

	

	public boolean choca(Player p) {

		float x = (float) ((-p.realXPlayerCoordinate)) + this.x;
		float y = (float) ((-p.realYPlayerCoordinate)) + this.y;

		if (p.getX() < x + this.ancho && p.getX() + p.getWidth() > x && p.getY() < y + this.alto
				&& p.getY() + p.getHeight() > y) {
			return true;
		}
		return false;
	}

	public boolean chocaConBala(Bala b, float xPlayer, float yPlayer) {

		float x = (float) xPlayer + this.x;
		float y = (float) yPlayer + this.y;

		if (b.getX() < x + this.ancho && b.getX() + b.getWidthBall() > x && b.getY() < y + this.alto
				&& b.getY() + b.getHeightBall() > y) {
			
			
				

			
			return true;
		}

		return false;
	}

	public Vec2 getInitialVector() {
		return v;
	}
	public boolean isBuilding() {
		return building;
	}
	

}

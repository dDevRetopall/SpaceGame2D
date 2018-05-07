package graphics.builds;

import java.awt.Color;
import java.awt.Graphics;

import ia.Player;
import mapGenerator.Vec2;

public class SalidaKey extends LadrilloConColision {
	
	private float x;
	private float y;
	private int size=100; //¿?¿?
	public SalidaKey(float x,float y,Color c,Vec2 v) {
		super(x,y,c,v);
		this.x = x;
		this.y = y;
		this.indestructible=true;
	}
	public void checkCompleteTask(Player p) {
		if(super.choca(p)){
			
		}
		
	}
	public void renderSalida(Graphics g, float playerX,float playerY) {
		super.renderWall(g, playerX, playerY);
		
		
		
	}

}

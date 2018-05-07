package graphics.shooting;

import java.awt.Graphics;
import java.awt.Image;

import ia.Player;

public class Disparo {
	float vx = 1;
	float vy = 1;
	float x;

	float y;
	private Image i;

	public Disparo(int x, int y,Image i) {

		this.x = x;
		this.y = y;
		this.i = i;

	}

	public void mover() {
		x = vx + x;
		y = vy + y;
	}

	public void renderDisparo(Graphics g, Player p) {
		g.drawImage(i, (int)x,(int) y, null);
	}
}

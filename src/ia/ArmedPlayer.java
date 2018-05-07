package ia;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import cañon.Cañon;
import cañon.CañonPlayer;
import graphics.shooting.DisparoEspecial;

public class ArmedPlayer extends Player{
	ArrayList<DisparoEspecial> disparos = new ArrayList<>();
	CañonPlayer c;
	public boolean disparar = false;
	public ArmedPlayer(int x, int y, int type, Color color, int width, int height) {
		super(x, y, type, color, width, height);
		c = new CañonPlayer();
		
	}
	public void renderShoots(Graphics g) {
		
		for (DisparoEspecial de : disparos) {
			de.renderShoot(g);
		}
	}
	public void variableUpdaterArmedPlayer() {
		
		if (disparar) {
			shoot();
			disparar = false; // Efecto chulo quitar esto
		}
	}
	public void shoot() {
		disparos.add(new DisparoEspecial(20));
	}
	@Override
	public void paintCharacter(Graphics g) {
		if(!muerto &&!completed) {
		c.render(g);
		}
		super.paintCharacter(g);
		
		variableUpdaterArmedPlayer();
		renderShoots(g);
	
	}
	public CañonPlayer getCañon() {
		return c;
	}
	

}

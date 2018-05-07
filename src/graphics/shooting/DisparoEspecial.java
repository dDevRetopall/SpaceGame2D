package graphics.shooting;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import constants.Constants;
import graphics.fireworks.FuegoArtificial;
import graphics.fireworks.Punto;
import ia.Engine;
import ia.IAGenerator;
import juegoEspacio.Main;

public class DisparoEspecial {
	ArrayList<Bala> balas = new ArrayList<>();
	ArrayList<Punto> b1 = new ArrayList<>();
	
	float vx, vy;

	public DisparoEspecial(int bolas) {
		for (int i = 0; i < bolas; i++) {
			vx = (float) Math.cos(Math.toRadians((i * 360) / bolas)) * 3;
			vy = (float) Math.sin(Math.toRadians((i * 360) / bolas)) * 3;

			balas.add(new Bala(20, 20, IAGenerator.mainPlayer.realXPlayerCoordinate+IAGenerator.mainPlayer.getWidth()/2,
					IAGenerator.mainPlayer.realYPlayerCoordinate+IAGenerator.mainPlayer.getHeight() /2,
					IAGenerator.mainPlayer.getWidth(), IAGenerator.mainPlayer.getHeight(), vx, vy));
		}
	}

	public void renderShoot(Graphics g) {
		
		for (int i = 0; i < balas.size(); i++) {
			Bala b = balas.get(i);
			
			if (b.alive) {
				
				b.mover(-IAGenerator.mainPlayer.getVx(), -IAGenerator.mainPlayer.getVy());
				b.dibujar(g);
			} else {
				if (Constants.DEBUG) {
					b1.add(new Punto(b.x, b.y));
					b1.add(new Punto(-IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY()));
				}
				Engine.fuegosArtificiales.add(new FuegoArtificial(b.x,
						b.y , b.actualColor));
				balas.remove(b);

			}
		}
		if (Constants.DEBUG) {
			for (int i = 0; i < b1.size(); i += 2) {
				g.setColor(Color.red);
				g.fillOval((int) b1.get(i).x - 5, (int) b1.get(i).y - 5, 10, 10);
			}
		}
	}

	public ArrayList<Bala> getBalas() {
		return balas;
	}
	
}

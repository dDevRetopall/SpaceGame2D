/*
 * Decompiled with CFR 0_114.
 */
package graphics.fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ia.IAGenerator;
import juegoEspacio.Main;
import vector.Vector;

public class FuegoArtificial {
	public ArrayList<Particula> particulas = new ArrayList();
	private float x;
	private float y;
	private Color c;

	public FuegoArtificial(float x, float y, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
		int num = 1000;
		for (int i = 0; i < num; ++i) {
			double grados = 360 * i / num;
			Punto origenExplosion = new Punto(x, y);
			double vx = Math.cos(Math.toRadians(grados));
			double vy = Math.sin(Math.toRadians(grados));
			Vector velocidad = new Vector(vx *= Math.random() * 3.0 - 1.5, vy *= Math.random() * 3.0 - 1.5);
			Particula p = new Particula(origenExplosion, velocidad, (long) (Math.random() * 2500.0 - 500.0), c);
			// new Color((int) (Math.random() *
			// 255),(int)(Math.random()*255),(int)(Math.random()*255))
			this.particulas.add(p);
		}
	}
	public FuegoArtificial(float x, float y, Color c, float velocidadMax,int particulas) {
		this.x = x;
		this.y = y;
		this.c = c;
		int num = particulas;
		for (int i = 0; i < num; ++i) {
			double grados = 360 * i / num;
			Punto origenExplosion = new Punto(x, y);
			double vx = Math.cos(Math.toRadians(grados));
			double vy = Math.sin(Math.toRadians(grados));
			Vector velocidad = new Vector(vx *= Math.random() * velocidadMax - 1.5,
					vy *= Math.random() * velocidadMax - 1.5);
			Particula p = new Particula(origenExplosion, velocidad, (long) (Math.random() * 2500.0 - 500.0), c);
			// new Color((int) (Math.random() *
			// 255),(int)(Math.random()*255),(int)(Math.random()*255))
			this.particulas.add(p);
		}
	}

	
	public FuegoArtificial(float x, float y, Color c, float velocidadMax) {
		this.x = x;
		this.y = y;
		this.c = c;
		int num = 1000;
		for (int i = 0; i < num; ++i) {
			double grados = 360 * i / num;
			Punto origenExplosion = new Punto(x, y);
			double vx = Math.cos(Math.toRadians(grados));
			double vy = Math.sin(Math.toRadians(grados));
			Vector velocidad = new Vector(vx *= Math.random() * velocidadMax - 1.5,
					vy *= Math.random() * velocidadMax - 1.5);
			Particula p = new Particula(origenExplosion, velocidad, (long) (Math.random() * 2500.0 - 500.0), c);
			// new Color((int) (Math.random() *
			// 255),(int)(Math.random()*255),(int)(Math.random()*255))
			this.particulas.add(p);
		}
	}

	public boolean hasFinished() {
		if (this.particulas.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public void dibujar(Graphics g, float dx, float dy) {
		ArrayList<Particula> pMuertas = new ArrayList<Particula>();
		for (Particula viva : this.particulas) {
			if (viva.estaViva())
				continue;
			pMuertas.add(viva);
		}
		for (Particula viva : pMuertas) {
			this.particulas.remove(viva);
		}
		for (Particula viva : this.particulas) {
			viva.dibujar(g, dx, dy);
		}

	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public Color getC() {
		return c;
	}


	public void setC(Color c) {
		this.c = c;
	}


	public ArrayList<Particula> getParticulas() {
		return particulas;
	}


	public void setParticulas(ArrayList<Particula> particulas) {
		this.particulas = particulas;
	}
	

}

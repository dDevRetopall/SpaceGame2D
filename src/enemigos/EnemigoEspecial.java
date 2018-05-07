/*
 * Decompiled with CFR 0_114.
 */
package enemigos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import ia.Player;
import juegoEspacio.Main;
import mapGenerator.Vec2;

public class EnemigoEspecial extends Enemigo {
	public ArrayList<Enemigo> hijos = new ArrayList();
	Color c;

	public EnemigoEspecial(float x, float y,Color cc) {
		
		super(x, y,cc);
		
		
		float initialTransparency=75f;
		float transparencyDifferential=25f;
		int num = (int) (3+(int)(Math.random() * 7));
		
		for (int i = 0; i < num; ++i) {
		 c = new Color((int) (cc.getRed() * ((float) (num - i) / (float) num)),
					(int) (cc.getGreen()* ((float) (num - i) / (float) num)),
					(int) (cc.getBlue() * ((float) (num - i) / (float) num)),
					(int) (initialTransparency + transparencyDifferential * ((float) (num - i) / (float) num)));
			Enemigo ee = new Enemigo(x - (float) (i * 50), y, c) {

				@Override
				public void mover() {
					this.x += this.vx;
					this.y += this.vy;
					if (this.x > this.xi + this.limiteDerecho) {
						this.x = this.xi + this.limiteDerecho;
						this.vx = 0.0f;
						this.vy = -1.0f;
					}
					if (this.y < this.yi + this.limiteArriba) {
						this.y = this.yi + this.limiteArriba;
						this.vx = -1.0f;
						this.vy = 0.0f;
					}
					if (this.y > this.yi + this.limiteAbajo) {
						this.y = this.yi + this.limiteAbajo;
						this.vx = 1.0f;
						this.vy = 0.0f;
					}
					if (this.x < this.xi + this.limiteIzquierdo) {
						this.x = this.xi + this.limiteIzquierdo;
						this.vx = 0.0f;
						this.vy = 1.0f;
					}
					
				}
			};
			ee.vy = 0.0f;
			ee.vx = 1.0f;
			ee.limiteDerecho = 100 + i * 50;
			ee.limiteIzquierdo = -100 - (num - i) * 50;
			this.hijos.add((ee));
		}
	}

	@Override
	public void dibujar(Graphics g, float dx, float dy) {
		for (Enemigo e : this.hijos) {
			e.dibujar(g, dx, dy);
		}

	}

	@Override
	public boolean choca(Player c) {
		boolean choca = false;
		for (Enemigo e : this.hijos) {
			choca = choca || e.choca(c);
			boolean bl = choca;

		}
		return choca;
	}

	public Color quienChoca(Player c) {
		boolean choca = false;
		for (Enemigo e : this.hijos) {
			choca = choca || e.choca(c);
			boolean bl = choca;
			return e.getC();
		}
		return null;

	}

	@Override
	public void mover() {
		for (Enemigo e : this.hijos) {
			e.mover();
		}
	}

}

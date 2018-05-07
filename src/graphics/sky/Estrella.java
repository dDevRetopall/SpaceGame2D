/*

* Decompiled with CFR 0_114.
 */
package graphics.sky;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import constants.Constants;
import graphics.fireworks.Punto;
import juegoEspacio.Ventana;
import vector.OperacionesVectores;
import vector.Vector;

public class Estrella {
	private double y;
	private double x;
	private Color color;
	private float vx=0;
	private float vy=0;

	public Estrella(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void mover(float vx, float vy) {
		this.x += (double) vx;
		this.y += (double) vy;
		if (this.x < 0.0/* && vx < 0.0f */) {
			this.x = (double) Ventana.screenSize.width + this.x;
		}
		if (this.y < 0.0 /* && vy < 0.0f */) {
			this.y = (double) Ventana.screenSize.height + this.y;
		}
		if (this.x > (double) Ventana.screenSize.width /* && vx > 0.0f */) {
			this.x = -(double) Ventana.screenSize.width - this.x;
		}
		if (this.y > (double) Ventana.screenSize.height /* && vy > 0.0f */) {
			this.y = -(double) Ventana.screenSize.height - this.y;
		}
		this.vx=vx;
		this.vy=vy;
	}

	public void dibujar(Graphics g) {
		int x=1;
		int y=1;
		if(Constants.levelEffects>=2) {
		x=(int)( Math.random()*3);
		y= (int)( Math.random()*3);
		}
		//if (Math.random() < 0.8f) {
		if(Math.abs(vx)<0.01|| Math.abs(vy)<0.01) {

			g.setColor(color);
			g.fillRect((int) this.x, (int) this.y,x, (int)y);
		}else {
	
			Vector v=OperacionesVectores.generarVector(new Punto(this.x,this.y), new Punto(this.x-vx,this.y-vy));
		
			
		
			g.setColor(color);
			g.drawLine((int)this.x, (int)this.y, (int)(this.x-vx),(int)(this.y-vy));
		}
		//}

	}
}

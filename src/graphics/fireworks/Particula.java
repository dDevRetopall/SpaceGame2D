/*
 * Decompiled with CFR 0_114.
 */
package graphics.fireworks;


import java.awt.Color;
import java.awt.Graphics;

import vector.Vector;

public class Particula {
    private Punto posicion;
    private Vector velocidad;
    private long tiempoDeVida;
    private long tiempoInicial;
	private Color c;
	
    public Particula(Punto posicion, Vector velocidad, long tiempoDeVida,Color c) {
        this.tiempoDeVida = tiempoDeVida;
        this.posicion = posicion;
        this.velocidad = velocidad;
		this.c = c;
        this.tiempoInicial = System.currentTimeMillis();
    }

    public void dibujar(Graphics g, float dx, float dy) {
        g.setColor(c);
        g.drawLine((int)(this.posicion.x + (double)dx), (int)(this.posicion.y + (double)dy), (int)(this.posicion.x + (double)dx + this.velocidad.x * 20.0), (int)(this.posicion.y + (double)dy + this.velocidad.y * 20.0));
        this.posicion.x += this.velocidad.x+ dx;;
        this.posicion.y += this.velocidad.y+ dy;;
        this.velocidad.x *= 0.99;
        this.velocidad.y *= 0.99;
        this.velocidad.y += 5.0E-4;
    }

    public boolean estaViva() {
        if (System.currentTimeMillis() - this.tiempoInicial < this.tiempoDeVida) {
            return true;
        }
        return false;
    }
}


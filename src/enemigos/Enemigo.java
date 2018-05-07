/*
 * Decompiled with CFR 0_114.
 */
package enemigos;


import ia.Player;
import juegoEspacio.Main;
import mapGenerator.ColorUtils;
import mapGenerator.Vec2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

public class Enemigo {
    float y;
    float x;
    public static float ancho = 50.0f;
    public static float alto = 50.0f;
    private Color c;
    float vx = (float)(Math.random() + 0.1);
    float vy = (float)(Math.random() + 0.1);
    float xi;
    float yi;
    public float limiteDerecho = 100.0f;
    public float limiteIzquierdo = -100.0f;
    public float limiteAbajo = 100.0f;
    public float limiteArriba = -100.0f;
    private float xMap;
    private float yMap;
    public Enemigo(float x, float y,Color c) {
        this.x = x;
        this.y = y;
        this.xi = x;
        this.yi = y;
        this.c=c;
    }
   

    public void dibujar(Graphics g, float dx, float dy) {
        if (this.x + dx > (float)juegoEspacio.Ventana.screenSize.width || this.x + dx + this.ancho < 0.0f || this.y + dy > (float)juegoEspacio.Ventana.screenSize.height || this.y + dy + this.alto < 0.0f) {
            return;
        }
        Color c2 = this.c.darker();
        g.setColor(c2);
        g.fillRoundRect((int)(this.x + dx), (int)(this.y + dy), (int)this.ancho, (int)this.alto, (int)(this.ancho / 5.0f), (int)(this.alto / 5.0f));
        g.fillRoundRect((int)(this.x + dx + 4.0f), (int)(this.y + dy + 4.0f), (int)this.ancho, (int)this.alto, (int)(this.ancho / 5.0f), (int)(this.alto / 5.0f));
        g.setColor(this.c);
        g.fillRoundRect((int)(this.x + dx + 2.0f), (int)(this.y + dy + 2.0f), (int)(this.ancho - 4.0f), (int)(this.alto - 4.0f), (int)(this.ancho / 5.0f), (int)(this.alto / 5.0f));
    }

    public boolean choca(Player c) {
        float x = (float)((- juegoEspacio.Ventana.screenSize.width) / 2) + this.x;
        float y = (float)((- juegoEspacio.Ventana.screenSize.height) / 2) + this.y;
        if (c.getX() - c.radio < x + this.ancho && c.getX() + c.radio > x && c.getY() - c.radio < y + this.alto && c.getY() + c.radio > y) {
            return true;
        }
        return false;
    }

    public void mover() {
        this.x += this.vx;
        this.y += this.vy;
        if (this.x > this.xi + this.limiteDerecho || this.x < this.xi + this.limiteIzquierdo) {
            this.vx *= -1.0f;
        }
        if (this.y > this.yi + this.limiteAbajo || this.y < this.yi + this.limiteArriba) {
            this.vy *= -1.0f;
        }
   
    }

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}


	public float getxMap() {
		return xMap;
	}


	public void setxMap(float xMap) {
		this.xMap = xMap;
	}


	public float getyMap() {
		return yMap;
	}


	public void setyMap(float yMap) {
		this.yMap = yMap;
	}
	
    
}


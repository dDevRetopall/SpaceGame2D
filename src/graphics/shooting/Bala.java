/*
 * Decompiled with CFR 0_114.
 */
package graphics.shooting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;

import constants.Constants;
import juegoEspacio.Ventana;
import utils.imageUtils.ImageUtils;
import vector.OperacionesVectores;
import vector.Vector;

public class Bala {
    float radio;
    float vx;
    float vy;
    private double a;
    int i = 0;

	public int heightPlayer;
	public int widthPlayer;
	public float y;
	public float x;
	public int heightBall;
	public int widthBall;
	long initialTime;
	boolean alive=true;
	public Color actualColor;
    public Bala(int widthBall,int heightBall, float xPlayer, float yPlayer,int widthPlayer,int heightPlayer, float vx, float vy) {
        this.widthBall = widthBall;
		this.heightBall = heightBall;
		this.x = xPlayer;
		this.y = yPlayer;
		this.widthPlayer = widthPlayer;
		this.heightPlayer = heightPlayer;
        this.vx = vx;
        this.vy = vy;
        this.i = 0;
        initialTime=System.currentTimeMillis();
    }

    public void mover(float vx2,float vy2) {
        this.x = this.x + vx +vx2;
        this.y = this.y + vy +vy2;
       
        if(System.currentTimeMillis()-initialTime>=2000) {
        	alive=false;
        }
    }

    public void dibujar(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
        double radian = 0.017453292519943295;
        int rojo = (int)((Math.cos((double)this.i * radian) + 1.0) * 255.0 / 2.0);
        int blue = (int)((Math.sin((double)this.i * radian) + 1.0) * 255.0 / 2.0);
        int t = (int)((Math.sin((double)this.i * radian) + 1.0) * 255.0 / 2.0);
        this.i += 5;
        actualColor= new Color(rojo, blue, t, 255);
        Color []colors= {actualColor,new Color(0,0,0,0)};
    	Paint paint = new RadialGradientPaint((int)(x), (int)((y)), widthBall,
				Constants.FRACTIONS,colors);
		g2.setPaint(paint);
        g2.fillOval((int)(x)-widthBall/2, (int)((y)-heightBall/2), widthBall, heightBall);
        g.setColor(Color.BLACK);
        g.drawOval((int)(x)-widthBall/2, (int)((y)-heightBall/2), widthBall, heightBall);
//    	AffineTransform backup = g2.getTransform();
//        
//    	Vector v2=OperacionesVectores.resize(new Vector(vx, vy), 1);
//        AffineTransform a = AffineTransform.getRotateInstance(v2.getX(),v2.getY());
//        
//        g2.setTransform(a);
//        
   //     g2.drawImage(ImageUtils.figurasFiltradas.get(20).getI(), (int)(x)-widthBall/2, (int)((y)-heightBall/2), null);
       
       
//        g2.setTransform(backup);
    	
       
        this.a += 0.05;
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

	public int getHeightBall() {
		return heightBall;
	}

	public void setHeightBall(int heightBall) {
		this.heightBall = heightBall;
	}

	public int getWidthBall() {
		return widthBall;
	}

	public void setWidthBall(int widthBall) {
		this.widthBall = widthBall;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
    
}


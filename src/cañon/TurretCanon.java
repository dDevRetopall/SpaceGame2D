package cañon;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import constants.Constants;
import graphics.fireworks.Punto;
import ia.IAGenerator;
import vector.GraphicalUtils;
import vector.OperacionesVectores;
import vector.Vector;

public class TurretCanon {
	private int x;
	private int y;
	private int radiusOfAttack=200;
	private int increase=100;
	private int sizeTurret=50;
	Polygon poli ;
	public float radianPosition=0;
	private int xExtremo,yExtremo=0;
	public boolean warning=false;
	public TurretCanon(int x,int y,int radius) {
		this.x = x;
		this.y = y;
		radiusOfAttack=radius;
	
		
		
	}
	public void render(Graphics g) {
		int x2=(int) (x-IAGenerator.mainPlayer.getX());
		int y2=(int) (x-IAGenerator.mainPlayer.getY());
		Color[]colors= {new Color(0,153,0,153),new Color(0,0,0,0)};
		if(poli!=null) {
		g.fillPolygon(poli);
		Graphics2D g2 = (Graphics2D) g;
		if(warning) {
			colors[0]= new Color(253,0,0,80);
		}else {
			colors[0]= new Color(0,153,0,80);
		}
		RadialGradientPaint rgp = new RadialGradientPaint(x2, y2, radiusOfAttack, Constants.FRACTIONS, colors,CycleMethod.NO_CYCLE);
		g2.setPaint(rgp);
		
		g.fillOval(x2-radiusOfAttack, y2-radiusOfAttack, radiusOfAttack*2, radiusOfAttack*2);
		
		if(warning) {
			colors[0]= new Color(253,0,0,153);
		}else {
			colors[0]= new Color(0,153,0,153);
		}
		if(Constants.DEBUG) {
		g.setColor(Color.red);
		g.drawRect(x2-1, y2-1,2, 2);
		g.drawOval(x2-radiusOfAttack, y2 -radiusOfAttack, radiusOfAttack*2, radiusOfAttack*2);
		}
		rgp = new RadialGradientPaint(x2, y2, radiusOfAttack-50, Constants.FRACTIONS, colors,CycleMethod.NO_CYCLE);
		g2.setPaint(rgp);

		
		g.fillArc(x2-(radiusOfAttack+50), y2-(radiusOfAttack+50), (radiusOfAttack+50)*2, (radiusOfAttack+50)*2,(int)GraphicalUtils.radiansToDegrees(radianPosition) , 60);
		colors[0]= new Color(153,153,153,200);
		rgp = new RadialGradientPaint(x2, y2, radiusOfAttack-50, Constants.FRACTIONS, colors,CycleMethod.NO_CYCLE);
		g2.setPaint(rgp);
		g.fillOval(x2-sizeTurret/2, y2-sizeTurret/2, sizeTurret, sizeTurret);
		}
	}
	public void rotateCanon(float radianPosition) {
		this.radianPosition=radianPosition;
		int width=50;
		int height=200;
		float x2=(float) (x+Math.cos(radianPosition));
		float y2=(float) (y+Math.sin(radianPosition));
		Punto p = OperacionesVectores.pasarAEjeCartesiano(new Punto(x, y));
		Punto p2 = OperacionesVectores.pasarAEjeCartesiano(new Punto(x2, y2));
		Vector vec = OperacionesVectores.generarVector(p, p2);
		Vector r = OperacionesVectores.resize(vec, height);
		
		 poli=GraphicalUtils.getPolygonRotation(x, y, (int)width,r);
		
	}
	
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadiusOfAttack() {
		return radiusOfAttack;
	}
	public void setRadiusOfAttack(int radiusOfAttack) {
		this.radiusOfAttack = radiusOfAttack;
	}
	
	
}

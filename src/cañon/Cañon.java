package cañon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;

import constants.Constants;
import graphics.builds.Ladrillo;
import graphics.fireworks.FuegoArtificial;
import graphics.fireworks.Punto;
import graphics.shooting.Bala;
import graphics.shooting.Bomba;
import ia.Engine;
import ia.IAGenerator;
import juegoEspacio.Main;
import juegoEspacio.Ventana;
import vector.GraphicalUtils;
import vector.OperacionesVectores;
import vector.Vector;

public class Cañon {
	int width = 100;
	double height = 20;
	int xMouse = 0;
	int yMouse = 0;
	
	float velocidad=10;
	public Cañon() {

	}

	public void renderCañon(Graphics g) {
		int x1 = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
		int y1 = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
		Punto p = OperacionesVectores.pasarAEjeCartesiano(new Punto(x1, y1));
		Punto p2 = OperacionesVectores.pasarAEjeCartesiano(new Punto(xMouse, yMouse));
		Vector vec = OperacionesVectores.generarVector(p, p2);
		Vector r = OperacionesVectores.resize(vec, 50);
		// double mRectaRadioPunto=(yMouse-y1)/(xMouse-x1);
		// double mRectaTan = - (1/mRectaRadioPunto);
		Polygon poli=GraphicalUtils.getPolygonRotation(x1, y1, (int)height,r);
		g.setColor(Color.GRAY);

		g.fillPolygon(poli);
		
		Graphics2D g2 = (Graphics2D)g;
		int radio=100;
		
		
		
		Vector r2 = OperacionesVectores.resize(r, 100);
		if(Main.getVentana().getPanelActual().getPanelLife().getShieldBar().getValue()>0) {
		Paint paint = new RadialGradientPaint((int)(x1 +r2.getX()),
				(int)(y1-r2.getY()), radio, Constants.FRACTIONS, Constants.BLUE);
		g2.setPaint(paint);
		g2.fillOval(x1-100, y1-100, 200, 200);
		}
		g2.setPaint(null);
	
//		g.fillOval(x1+(int)r2.getX()-10,y1-(int) r2.getY()-10, 20, 20);
//		g.drawOval((int)(x1-radio/2),
//				(int)(y1-radio/2),radio,radio);
		 
		
	}

	
	
	
	
}

package vector;

import java.awt.Polygon;

import graphics.fireworks.Punto;
import juegoEspacio.Main;

public class GraphicalUtils {
	public static Polygon getPolygonRotation(int x1,int y1,int height,Vector r) {
		
		
		
		Punto pFinal =OperacionesVectores.hallarPuntoCañon(r, height);
		double finalY=-y1+pFinal.y;
		double finalX=x1+pFinal.x;
		
		
		pFinal =OperacionesVectores.hallarPuntoCañon(r, -height);
		double finalY2=-y1+pFinal.y;
		double finalX2=x1+pFinal.x;
		
		pFinal =OperacionesVectores.hallarPuntoCañon(r, height);
		double finalY3=-y1+pFinal.y +r.y;
		double finalX3=x1+pFinal.x -r.x;
		
		
		pFinal =OperacionesVectores.hallarPuntoCañon(r, -height);
		double finalY4=-y1+pFinal.y +r.y;
		double finalX4=x1+pFinal.x -r.x;
		

		int[]xPoints= {(int)finalX,(int)finalX2,(int)finalX4,(int)finalX3};
		int[]yPoints= {(int)finalY,(int)finalY2,(int)finalY4,(int)finalY3};
		int nPoints =4;
	
		return new Polygon(xPoints, yPoints, nPoints);
	}
	public static float radiansToDegrees(float radians) {
		float f =(float) ((radians*360)/(float)(Math.PI*2));
		return f;
		
	}
}

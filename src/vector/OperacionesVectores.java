package vector;

import java.awt.Color;

import constants.Constants;
import graphics.fireworks.Punto;
import juegoEspacio.Ventana;

public class OperacionesVectores {
	public static Vector sumarVectores(Vector v1, Vector v2) {
		return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	public static Vector restarVectores(Vector v1, Vector v2) {
		return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}

	public static Vector escalar(Vector v1, double escalamiento) {
		return new Vector(escalamiento * v1.getX(), escalamiento * v1.getY());
	}

	public static Vector resize(Vector v1, double modulo) {
		double moduloInicial = Math.sqrt(v1.getX() * v1.getX() + v1.getY() * v1.getY());
		Vector rUnitario = dividirVectorEntreValor(v1, moduloInicial);
		Vector escalado = escalar(rUnitario, modulo);
		return escalado;
	}

	public static Vector dividirVectorEntreValor(Vector v1, double valor) {

		return new Vector(v1.getX() / valor, v1.getY() / valor);
	}

	public static double moduloVector(Vector v1) {
		double modulo = Math.sqrt(v1.getX() * v1.getX() + v1.getY() * v1.getY());
		return modulo;

	}

	public static Vector generarVector(float x1, float y1, float x2, float y2) {
		return new Vector(x2 - x1, y2 - y1);
	}

	public static Vector generarVector(Punto p1, Punto p2) {
		return new Vector(p2.x - p1.x, p2.y - p1.y);
	}

	public static Punto pasarAEjeCartesiano(Punto p) {
		return new Punto(p.x, Ventana.screenSize.height - p.y);
	}

	public static Punto pasarAlEjeComputacional(Punto p) {
		return new Punto(p.x, Ventana.screenSize.height - p.y);
	}

	
	
	
	public static Punto hallarPuntoCañon(Vector r, double height) {
		double moduloR = OperacionesVectores.moduloVector(r);
		double moduloS = Math.sqrt(moduloR * moduloR + (height / 2) * (height / 2));

		double alpha = Math.atan2(r.getY(), r.getX());
		double beta = Math.asin((height / 4) / moduloR);
		double angulo = 2 * beta + alpha;
		if (Constants.DEBUG) {
			System.out.println(" Alpha: " + alpha * 360 / (2 * (Math.PI)) + " Beta: " + beta * 360 / (2 * (Math.PI))
					+ " Modulo R: " + moduloR + " Modulo S: " + moduloS + " X: " + r.getX() + " Y: " + r.getY());
		}
		double y = (Math.sin(angulo) * moduloS);
		double x = (Math.cos(angulo) * moduloS);
		Punto pFinal = OperacionesVectores.pasarAlEjeComputacional(new Punto(x, y));
		if (Constants.DEBUG) {
			System.out.println("X: " + x + "Y: " + y);
		}
		return pFinal;
	}
}

package cañon;

import java.awt.Graphics;
import java.util.ArrayList;

import constants.Constants;
import graphics.fireworks.FuegoArtificial;
import graphics.fireworks.Punto;
import graphics.shooting.Bala;
import graphics.shooting.Bomba;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import vector.OperacionesVectores;
import vector.Vector;

public class CañonPlayer extends Cañon {
	ArrayList<Bala> balas = new ArrayList<>();
	ArrayList<Bala> balasToRemoveInQueue = new ArrayList<>();
	ArrayList<Bomba> bombas = new ArrayList<>();

	public CañonPlayer() {

	}
	public void render(Graphics g) {
		super.renderCañon(g);

		renderBombas(g);
		renderBalas(g);
	}
	public void disparar() {
		int x1 = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
		int y1 = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
		Punto p = OperacionesVectores.pasarAEjeCartesiano(new Punto(x1, y1));
		Punto p2 = OperacionesVectores.pasarAEjeCartesiano(new Punto(xMouse, yMouse));
		Vector vec = OperacionesVectores.generarVector(p, p2);
		Vector r = OperacionesVectores.resize(vec, velocidad);
		balas.add(new Bala(15, 15, IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2,
				IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2,
				IAGenerator.mainPlayer.getWidth(), IAGenerator.mainPlayer.getHeight(), (float) r.getX(),
				(float) -r.getY()));
	}

	public void ponerBomba() {
		if (bombas.size() < 5) {
			bombas.add(new Bomba(30, 30,
					IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2,
					IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2,
					IAGenerator.mainPlayer.getWidth(), IAGenerator.mainPlayer.getHeight(), 0, 0));
		} else {
			GameHandler.getVentana().getPanelActual().changeMessage("Limite debombas alcanzadas");
			System.out.println("Limite de bombas alcanzadas");
		}
	}

	public void renderBalas(Graphics g) {
		for (Bala b : balas) {
			if (!b.isAlive()) {
				balasToRemoveInQueue.add(b);
			}
			b.mover(-IAGenerator.mainPlayer.getVx(), -IAGenerator.mainPlayer.getVy());
			b.dibujar(g);
		}
		balas.removeAll(balasToRemoveInQueue);
	}

	public void renderBombas(Graphics g) {
		ArrayList<Bomba> bombasDetonadas = new ArrayList<>();
		for (Bomba b : bombas) {
			if (b.checkDetona(IAGenerator.mainPlayer)) {
				bombasDetonadas.add(b);
				int xP = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
				int yP = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
				GameHandler.getVentana().getPanelActual().getPanelLife().makeDamage(80,
						new FuegoArtificial(xP, yP, Constants.colorBomb));
			}
			// for(Ladrillo l :GameHandler.getVentana().getPanelActual().ladrillos) {
			// if(b.checkChoqueConLadrillo(l)) {
			// bombasDetonadas.add(b);
			// l.getHp().quitHealth(100);
			// }
			// }
			b.mover(-IAGenerator.mainPlayer.getVx(), -IAGenerator.mainPlayer.getVy());
			b.dibujar(g);
		}

		for (Bomba b : bombasDetonadas) {
			bombas.removeAll(bombasDetonadas);
		}
	}

	public void setxMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public void setyMouse(int yMouse) {
		this.yMouse = yMouse;
	}

	public ArrayList<Bala> getBalas() {
		return balas;
	}

	public ArrayList<Bala> getBalasToRemoveInQueue() {
		return balasToRemoveInQueue;
	}

	public ArrayList<Bomba> getBombas() {
		return bombas;
	}

	public void resetBombas() {
		for (Bomba b : bombas) {
			GameHandler.getVentana().getPanelActual().remove(b.getProgressBarPanel());
			GameHandler.getVentana().getPanelActual().updateUI();

		}

	}
}

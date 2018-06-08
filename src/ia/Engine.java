package ia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import constants.Constants;
import enemigos.Enemigo;
import enemigos.EnemigoEspecial;
import graphics.builds.Ladrillo;
import graphics.builds.LadrilloConColision;
import graphics.builds.SalidaKey;
import graphics.fireworks.FuegoArtificial;
import graphics.fireworks.Particula;
import graphics.fireworks.Punto;
import graphics.shooting.Bala;
import graphics.shooting.DisparoEspecial;
import juegoEspacio.GameHandler;
import juegoEspacio.Ventana;
import vector.Vector;

public class Engine {
	public static ArrayList<FuegoArtificial> fuegosArtificiales = new ArrayList<>();

	public static FuegoArtificial explosion;
	public static int xP;
	public static int yP = 0;
	static Timer timer;

	static TimerTask task;

	public static void renderWalls(Graphics g) {
		int contador = 0;
		int contadorTotal = 0;

		for (LadrilloConColision l : GameHandler.getVentana().getPanelActual().ladrillos) {
			renderDisparos(l);
			if (!objetoEnRangoJugador(l)) {
				if (l.isProgressBarVisible()) {
					l.putProgressVisible(false);

				}
			} else {

				contador++;

				l.renderWall(g, -IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY());
				if (!IAGenerator.mainPlayer.muerto) {

					if (l.choca(IAGenerator.mainPlayer)) {
						if (l instanceof SalidaKey) {
							if (!IAGenerator.mainPlayer.completed) {
								GameHandler.getVentana().building=false;
								xP = IAGenerator.mainPlayer.realXPlayerCoordinate
										+ IAGenerator.mainPlayer.getWidth() / 2;
								yP = IAGenerator.mainPlayer.realYPlayerCoordinate
										+ IAGenerator.mainPlayer.getHeight() / 2;
								Engine.startAnimacion();

								IAGenerator.mainPlayer.completed = true;
							}
						} else {
							// IAGenerator.mainPlayer.muerto = true;

							Color c = l.c;
							xP = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
							yP = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
							GameHandler.getVentana().getPanelActual().getPanelLife().makeDamage(15,
									new FuegoArtificial(xP, yP, c));

						}

					}
				}
			}
			contadorTotal++;

		}
		for (Ladrillo l : GameHandler.getVentana().getPanelActual().ladrillosToRemove) {
			GameHandler.getVentana().getPanelActual().ladrillos.remove(l);
		}
		GameHandler.getVentana().getPanelActual().ladrillosToRemove
				.removeAll(GameHandler.getVentana().getPanelActual().ladrillosToRemove);
		if (Constants.DEBUG) {
			System.out.println("Rendered blocks: " + contador + " Total: " + contadorTotal + " Size of blocks Array: "
					+ GameHandler.getVentana().getPanelActual().ladrillos.size());
		}
	}

	public static boolean objetoEnRangoJugador(LadrilloConColision l) {
		if (l.x + -IAGenerator.mainPlayer.getX() > (float) GameHandler.getVentana().screenSize.width

				|| l.x + -IAGenerator.mainPlayer.getX() + l.ancho < 0.0f
				|| l.y + -IAGenerator.mainPlayer.getY() > (float) GameHandler.getVentana().screenSize.height
				|| l.y + -IAGenerator.mainPlayer.getY() + l.alto < 0.0f) {
			return false;
		} else {
			return true;

		}

	}

	public static void updateLadrillo(LadrilloConColision lc) {
		if (!lc.indestructible)
			if (Constants.getGamemode() == 1) {

				if (lc.getHp().getHealth() <= 0) {
					lc.putProgressVisible(false);

					GameHandler.getVentana().getPanelActual().ladrillosToRemove.add(lc);
					GameHandler.getVentana().getPanelActual().ladrillosMapa.remove(lc.getInitialVector());

					fuegosArtificiales.add(new FuegoArtificial(lc.x + lc.ancho / 2 - IAGenerator.mainPlayer.getX(),
							lc.y + lc.alto / 2 - IAGenerator.mainPlayer.getY(), lc.c));
				} else {
					lc.putProgressVisible(true);
				}

			}
	}

	public static void renderDisparos(LadrilloConColision l) {
		for (Bala b : IAGenerator.mainPlayer.getCañon().getBalas()) {
			if (l.chocaConBala(b, -IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY())) {

				IAGenerator.mainPlayer.getCañon().getBalasToRemoveInQueue().add(b);

				if (objetoEnRangoJugador(l)) {
					Engine.fuegosArtificiales.add(new FuegoArtificial(b.x, b.y, l.c));
				}
				if (Constants.getGamemode() == 1) {
					l.getHp().quitHealth(25);
					updateLadrillo(l);

				}
			}

		}
		IAGenerator.mainPlayer.getCañon().getBalas()
				.removeAll(IAGenerator.mainPlayer.getCañon().getBalasToRemoveInQueue());
		for (DisparoEspecial de : IAGenerator.mainPlayer.disparos) {
			for (Bala b : de.getBalas()) {

				if (l.chocaConBala(b, -IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY())) {
					if (objetoEnRangoJugador(l)) {
						Engine.fuegosArtificiales.add(new FuegoArtificial(b.x, b.y, l.c));
					}

					IAGenerator.mainPlayer.getCañon().getBalasToRemoveInQueue().add(b);
					if(!l.indestructible)
					if (Constants.getGamemode() == 1) {
						l.getHp().quitHealth(25);
						if (l.getHp().getHealth() <= 0) {
							l.putProgressVisible(false);
							GameHandler.getVentana().getPanelActual().ladrillosToRemove.add(l);
							GameHandler.getVentana().getPanelActual().ladrillosMapa.remove(l.getInitialVector());

						} else {
							l.putProgressVisible(true);
						}
					
					}

				}

			}
			de.getBalas().removeAll(IAGenerator.mainPlayer.getCañon().getBalasToRemoveInQueue());
		}

	}

	public static void renderEnemies(Graphics g) {
		int contador2 = 0, contadorTotal2 = 0;
		for (Enemigo e : GameHandler.getVentana().getPanelActual().enemigos) {
			if (e.getX() + -IAGenerator.mainPlayer.getX() - e.limiteDerecho
					- e.getAncho() > (float) GameHandler.getVentana().screenSize.width

					|| e.getX() + -IAGenerator.mainPlayer.getX() + e.getAncho() - e.limiteIzquierdo < 0.0f
					|| e.getY() + -IAGenerator.mainPlayer.getY()
							- e.limiteAbajo > (float) GameHandler.getVentana().screenSize.height
					|| e.getY() + -IAGenerator.mainPlayer.getY() - e.limiteArriba + e.getAlto() < 0.0f) {

			} else {
				contador2++;
				e.dibujar(g, -IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY());
				e.mover();

				if (!IAGenerator.mainPlayer.muerto) {
					if (e.choca(IAGenerator.mainPlayer)) {

						Color c = Color.RED;
						if (e instanceof EnemigoEspecial) {
							EnemigoEspecial ee = (EnemigoEspecial) e;
							c = ee.quienChoca(IAGenerator.mainPlayer);
							c = new Color(c.getRed(), c.getGreen(), c.getBlue());
						} else {
							c = e.getC();
						}
						xP = IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2;
						yP = IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2;
						GameHandler.getVentana().getPanelActual().getPanelLife().makeDamage(2, new FuegoArtificial(xP, yP, c));

					}
				}
			}
			contadorTotal2++;
		}
		if (Constants.DEBUG) {
			System.out.println("Rendered enemies: " + contador2 + " Total: " + contadorTotal2
					+ " Size of enemies Array: " + GameHandler.getVentana().getPanelActual().enemigos.size());
		}

	}

	public static void renderFirework(Graphics g) {

		if (explosion != null) {
			if (explosion.particulas.isEmpty()) {
				explosion = null;
			} else {
				explosion.dibujar(g, -IAGenerator.mainPlayer.getVx(), -IAGenerator.mainPlayer.getVy());
			}
		}
		int renderedFireworks = 0;
		for (int i = 0; i < fuegosArtificiales.size(); i++) {
			FuegoArtificial fa = fuegosArtificiales.get(i);
			if (fa.particulas.isEmpty()) {
				fuegosArtificiales.remove(fa);
			} else {

				renderedFireworks++;
				fa.dibujar(g, -IAGenerator.mainPlayer.getVx(), -IAGenerator.mainPlayer.getVy());
			}
		}
		if (Constants.DEBUG) {
			System.out.println("Total rendered fireworks: " + renderedFireworks);
		}
	}

	public static void startAnimacion() {
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				int num = 1000;
				Random r = new Random();

				Color cc = Color.getHSBColor(r.nextFloat(), // random hue, color
						1.0f, // full saturation, 1.0 for 'colorful' colors, 0.0 for grey
						1.0f // 1.0 for bright, 0.0 for black
				);
				float xP = (float) ((float) (IAGenerator.mainPlayer.realXPlayerCoordinate
						+ IAGenerator.mainPlayer.getWidth() / 2f + GameHandler.getVentana().screenSize.width / 2 - 1)
						* Math.random()
						+ ((float) (IAGenerator.mainPlayer.realXPlayerCoordinate
								+ IAGenerator.mainPlayer.getWidth() / 2f - GameHandler.getVentana().screenSize.width / 2)));
				float yP = (float) ((float) (IAGenerator.mainPlayer.realYPlayerCoordinate
						+ IAGenerator.mainPlayer.getHeight() / 2f + GameHandler.getVentana().screenSize.height / 2 - 1)
						* Math.random()
						+ ((float) (IAGenerator.mainPlayer.realYPlayerCoordinate
								+ IAGenerator.mainPlayer.getHeight() / 2f - GameHandler.getVentana().screenSize.height / 2)));

				// int xP = IAGenerator.mainPlayer.realXPlayerCoordinate +
				// IAGenerator.mainPlayer.getWidth() / 2;
				// int yP = IAGenerator.mainPlayer.realYPlayerCoordinate +
				// IAGenerator.mainPlayer.getHeight() / 2;
				FuegoArtificial fa = new FuegoArtificial(xP, yP, cc, 10.0f);
				fuegosArtificiales.add(fa);
			}
		};
		// Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms
		timer.schedule(task, 10, 100);

	}

	public static void finishAnimation() {
		if (task != null || timer != null) {
			task.cancel();
			timer.cancel();
		}
	}

}

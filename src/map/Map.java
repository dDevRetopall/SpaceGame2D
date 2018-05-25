package map;

import java.awt.Color;
import java.awt.Graphics;

import constants.Constants;
import enemigos.Enemigo;
import enemigos.EnemigoEspecial;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import juegoEspacio.Ventana;
import mapGenerator.Vec2;

public class Map {
	int widthMap;
	int heightMap;
	int initialX;

	int initialY;

	public Map() {

	}

	public void pintarMapa(Graphics g) {
		int variacionSizeespecial = 5;
		int espacio = 20;
		int contador = 0;
		int sizePixels = 2;
		int sizeEnemies = 1;
		widthMap = (GameHandler.getVentana().getPanelActual().ladrillosMapa
				.get(GameHandler.getVentana().getPanelActual().ladrillosMapa.size() - 1).x) * sizePixels + sizePixels;
		heightMap = (GameHandler.getVentana().getPanelActual().ladrillosMapa
				.get(GameHandler.getVentana().getPanelActual().ladrillosMapa.size() - 1).y) * sizePixels + sizePixels;
		initialX = Ventana.screenSize.width - widthMap - espacio;
		initialY = espacio;
		g.setColor(Color.BLACK);
		g.fillRect(initialX, initialY, widthMap - 2, heightMap - 2);
		for (Vec2 v : GameHandler.getVentana().getPanelActual().ladrillosMapa) {
			g.setColor(v.c);
			if (v.isEspecial()) {
				g.fillOval(initialX + v.x * sizePixels - variacionSizeespecial / 2, initialY + v.y * sizePixels,
						sizePixels + variacionSizeespecial, sizePixels + variacionSizeespecial);
			} else {
				g.fillRect(initialX + v.x * sizePixels - variacionSizeespecial / 2, initialY + v.y * sizePixels,
						sizePixels, sizePixels);
			}

			contador++;
		}

		// Marco

		// NO FUNCIONA - rango de vista aparecer los enemigos

		// g.drawLine((int)(IAGenerator.mainPlayer.getX() - Ventana.screenSize.width /
		// 2), 20,
		// (int)(IAGenerator.mainPlayer.getX() - Ventana.screenSize.width / 2), 1060);
		// g.drawLine((int)(IAGenerator.mainPlayer.getX() + Ventana.screenSize.width /
		// 2), 20,
		// (int)(IAGenerator.mainPlayer.getX() + Ventana.screenSize.width / 2), 1060);
		// if ((IAGenerator.mainPlayer.getX() - Ventana.screenSize.width / 2 < e.getX()
		// && (IAGenerator.mainPlayer.getX() + Ventana.screenSize.width / 2) >
		// e.getX())) {
		for (Enemigo e : GameHandler.getVentana().getPanelActual().enemigos) {

			dibujarEnemigoEnMapa(e, initialX, initialY, sizePixels, widthMap, heightMap, sizeEnemies, g);

		}
		dibujarCamara(g, initialX, initialY, sizePixels, widthMap, heightMap);
	}

	public void dibujarEnemigoEnMapa(Enemigo e, int initialX, int initialY, int sizePixels, int widthMap, int heightMap,
			int sizeEnemies, Graphics g) {
		if (e instanceof EnemigoEspecial) {
			EnemigoEspecial ee = (EnemigoEspecial) e;
			for (Enemigo eee : ee.hijos) {
				g.setColor(eee.getC());
				g.fillRect(
						(int) (initialX + GameHandler.getVentana().getPanelActual().enemigosMapa.get(e).x
								+ eee.getAncho() * sizePixels + e.getVx() / widthMap),
						(int) (initialY + GameHandler.getVentana().getPanelActual().enemigosMapa.get(e).y * sizePixels
								+ e.getVy() / heightMap),
						(int) (sizeEnemies), (int) (sizeEnemies));
			}
		} else {
			g.setColor(e.getC());
			g.fillRect(
					(int) (initialX + GameHandler.getVentana().getPanelActual().enemigosMapa.get(e).x * sizePixels
							+ e.getVx() / widthMap),
					(int) (initialY + GameHandler.getVentana().getPanelActual().enemigosMapa.get(e).y * sizePixels
							+ e.getVy() / heightMap),
					(int) (sizeEnemies), (int) (sizeEnemies));
		}

	}

	public void dibujarCamara(Graphics g, int initialX, int initialY, int sizePixels, int widthMap, int heightMap) {
		float visibleMapX = Ventana.screenSize.width / (float) Constants.sizeBlocks * sizePixels;
		float visibleMapY = Ventana.screenSize.height / (float) Constants.sizeBlocks * sizePixels;
		float xMapPlayer = (initialX
				+ (int) (sizePixels * (IAGenerator.mainPlayer.realXPlayerCoordinate / ((float) Constants.sizeBlocks)))
				+ (IAGenerator.mainPlayer.getX()) / (float) Constants.sizeBlocks * sizePixels - visibleMapX / 2f);
		float yMapPlayer = (initialY
				+ (int) (sizePixels * (IAGenerator.mainPlayer.realYPlayerCoordinate / ((float) Constants.sizeBlocks)))
				+ (IAGenerator.mainPlayer.getY()) / (float) Constants.sizeBlocks * sizePixels - visibleMapY / 2f);

		float widthMapPlayer = 3;// not real
		float heightMapPlayer = 3;// not real
		g.setColor(Color.gray);
		g.drawRect((int) xMapPlayer, (int) yMapPlayer, (int) visibleMapX, (int) visibleMapY);
		g.setColor(Color.WHITE);
		g.fillOval((int) (xMapPlayer + visibleMapX / 2 - widthMapPlayer / 2),
				(int) (yMapPlayer + visibleMapY / 2 - heightMapPlayer / 2), (int) widthMapPlayer,
				(int) heightMapPlayer);

	}
}

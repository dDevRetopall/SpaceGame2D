package graphics.builds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import constants.Constants;
import ia.IAGenerator;
import items.Item;
import juegoEspacio.Main;
import mapGenerator.Vec2;
import vector.OperacionesVectores;
import vector.Vector;

public class BuildFunctions {

	public static void build(int x, int y) {

		int resX = Math
				.round(((x + IAGenerator.mainPlayer.getX() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks));
		int resY = Math
				.round(((y + IAGenerator.mainPlayer.getY() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks));
		if (hayLadrillo(resX, resY)) {
			Main.getVentana().getPanelActual().changeMessage("There is a block there");

		} else {
			Vector v = OperacionesVectores.generarVector(
					IAGenerator.mainPlayer.getX() + IAGenerator.mainPlayer.realXPlayerCoordinate,
					IAGenerator.mainPlayer.getY() + IAGenerator.mainPlayer.realYPlayerCoordinate,
					resX * Constants.sizeBlocks, resY * Constants.sizeBlocks);
			if (OperacionesVectores.moduloVector(v) >= Constants.maxDiameter / 2
					|| OperacionesVectores.moduloVector(v) <= Constants.minDiameter / 2) {
				Main.getVentana().getPanelActual().changeMessage("The block is not in the suggested area");
			} else {
				if (hayItem((resX) * Constants.sizeBlocks, (resY) * Constants.sizeBlocks)) {
					Main.getVentana().getPanelActual().changeMessage("There is an item in that area");
				} else {
					Main.getVentana().getPanelActual().ladrillos.add(new LadrilloConColision(resX * Constants.sizeBlocks, resY * Constants.sizeBlocks,
							Color.GRAY, true, new Vec2(resX, resY, Color.GRAY, false)));
					Main.getVentana().getPanelActual().ladrillosMapa.set(resY * (Constants.widthMap) + resX,
							Main.getVentana().getPanelActual().ladrillos.get(Main.getVentana().getPanelActual().ladrillos.size() - 1).getInitialVector());

				}

			}
		}

	}

	public static LadrilloConColision getLadrilloEnMouse() {
		int resX = (int) (Math.round(
				((Main.getVentana().getPanelActual().getxMouse() + IAGenerator.mainPlayer.getX() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks)));
		int resY = (int) (Math.round(
				((Main.getVentana().getPanelActual().getyMouse() + IAGenerator.mainPlayer.getY() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks)));
		
		for (LadrilloConColision l : Main.getVentana().getPanelActual().ladrillos) {
			if (l.getInitialVector().x == resX && l.getInitialVector().y == resY) {
				return l;
			}
		}
		return null;
	}

	public static  void showPrebuild(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		int resX = (int) (Math.round(
				((Main.getVentana().getPanelActual().getxMouse() + IAGenerator.mainPlayer.getX() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks)));
		int resY = (int) (Math.round(
				((Main.getVentana().getPanelActual().getyMouse() + IAGenerator.mainPlayer.getY() - Constants.sizeBlocks / 2) / (float) Constants.sizeBlocks)));
		g.setColor(Color.WHITE);

		if (hayLadrillo(resX, resY)) {

			g.setColor(new Color(153, 0, 0));
			LadrilloConColision lc = getLadrilloEnMouse();

			if (lc != null && lc.isBuilding()) {

				Main.getVentana().getPanelActual().getBuildPanel().putRemoveMessage(true);
			}

		} else {
			Main.getVentana().getPanelActual().getBuildPanel().putRemoveMessage(false);
			Vector v = OperacionesVectores.generarVector(
					IAGenerator.mainPlayer.getX() + IAGenerator.mainPlayer.realXPlayerCoordinate,
					IAGenerator.mainPlayer.getY() + IAGenerator.mainPlayer.realYPlayerCoordinate,
					resX * Constants.sizeBlocks, resY * Constants.sizeBlocks);
			if (OperacionesVectores.moduloVector(v) >= Constants.maxDiameter / 2
					|| OperacionesVectores.moduloVector(v) <= Constants.minDiameter / 2) {
				g.setColor(new Color(153, 0, 0));
			} else {
				if (hayItem(resX * Constants.sizeBlocks, resY * Constants.sizeBlocks, g)) {
					g.setColor(new Color(153, 0, 0));
				} else {
					g.setColor(new Color(0, 153, 0));
				}
			}
		}
		g.drawRect((int) (resX * Constants.sizeBlocks - IAGenerator.mainPlayer.getX()),
				(int) (resY * Constants.sizeBlocks - IAGenerator.mainPlayer.getY()), Constants.sizeBlocks,
				Constants.sizeBlocks);

		Shape otherRing = createRingShape(
				IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2,
				IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2,
				Constants.maxDiameter / 2, Constants.maxDiameter / 2 - Constants.minDiameter / 2);
		gd.setColor(new Color(0, 153, 0, 25));
		Color[] colors = { new Color(0, 153, 0, 100), new Color(0, 0, 0, 50) };

		gd.setPaint(new RadialGradientPaint(
				new Point(IAGenerator.mainPlayer.realXPlayerCoordinate + IAGenerator.mainPlayer.getWidth() / 2,
						IAGenerator.mainPlayer.realYPlayerCoordinate + IAGenerator.mainPlayer.getHeight() / 2),
				Constants.maxDiameter / 2, Constants.FRACTIONS, colors));
		gd.fill(otherRing);
		gd.setColor(new Color(255, 0, 0, 25));

	}

	public static boolean hayLadrillo(int x, int y) {
		for (Ladrillo l : Main.getVentana().getPanelActual().ladrillos) {
			if (l.getInitialVector().x == x && l.getInitialVector().y == y) {
				return true;
			}
		}
		return false;

	}

	public  static boolean hayItem(int x, int y, Graphics g) {

		for (Item i : Main.getVentana().getPanelActual().getItemsHandler().getItems()) {

			if (i.getX() + i.getI().getWidth(null) / 2 >= x && i.getY() + i.getI().getHeight(null) / 2 >= y
					&& i.getX() + i.getI().getWidth(null) / 2 < x + Constants.sizeBlocks
					&& i.getY() + i.getI().getHeight(null) / 2 < y + Constants.sizeBlocks) {
				return true;
			}
		}
		return false;

	}

	public static boolean hayItem(int x, int y) {

		for (Item i : Main.getVentana().getPanelActual().getItemsHandler().getItems()) {

			if (i.getX() + i.getI().getWidth(null) / 2 >= x && i.getY() + i.getI().getHeight(null) / 2 >= y
					&& i.getX() + i.getI().getWidth(null) / 2 < x + Constants.sizeBlocks
					&& i.getY() + i.getI().getHeight(null) / 2 < y + Constants.sizeBlocks) {
				return true;
			}
		}
		return false;

	}

	private static Shape createRingShape(double centerX, double centerY, double outerRadius, double thickness) {
		Ellipse2D outer = new Ellipse2D.Double(centerX - outerRadius, centerY - outerRadius, outerRadius + outerRadius,
				outerRadius + outerRadius);
		Ellipse2D inner = new Ellipse2D.Double(centerX - outerRadius + thickness, centerY - outerRadius + thickness,
				outerRadius + outerRadius - thickness - thickness, outerRadius + outerRadius - thickness - thickness);
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		return area;
	}

}

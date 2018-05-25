package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import constants.Constants;
import constants.FileConstants;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import proposiciones.ItemId;
import utils.imageUtils.ImageUtils;

public class ImageItem {
	Image i = null;
	private int x;
	private int y;
	private int pos;
	private int sizeImage;
	private int timeInMillies = 5000;
	private long initialTime = 0;
	boolean counting = false;
	int id = 0;
	private Item i2;
	private int quantity = 0;

	public ImageItem(int x, int y, int pos, int sizeImage) {
		this.x = x;
		this.y = y;
		this.pos = pos;
		this.sizeImage = sizeImage;

	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void quitQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public void renderImage(Graphics g, int startDepPos, int y, int size) {
	
		float fraccion = (System.currentTimeMillis() - initialTime) / (float) timeInMillies;
		if (IAGenerator.mainPlayer.muerto) {
			counting = false;
		}
		if (i != null) {
			renderBackground(g, startDepPos, y, size);

		}
		if (fraccion <= 1 && counting) {

			for (int i = 1; i < fraccion * size; i++) {
				g.setColor(new Color(0, (int) (150 * (i / (float) size)) + 55, 0));
				g.drawLine(startDepPos + i, y + 1, startDepPos + i, y + size - 1);

			}
			if (IAGenerator.mainPlayer.getVx() != 0 || IAGenerator.mainPlayer.getVy() != 0) {
				counting = false;
				GameHandler.getVentana().getPanelActual().changeMessage("Don´t move to use an item");
			}
		} else {

			if (counting == true) {
				if (quantity > 1) {
					counting = false;
					quantity--;
					carryOutFunctions();
					
				} else {
					counting = false;
				
					GameHandler.getVentana().getPanelActual().getItemStore().slotsToRemove.add(this);
					carryOutFunctions();
					GameHandler.getVentana().getPanelActual().getItemStore().putMessageToUse = false;
				}
			}
		}

		if (i != null) {
			
			
		
			g.drawImage(i, x, y, null);
			if (quantity > 1) {
				g.setFont(Constants.FONT);
				g.setColor(Color.white);
				g.drawString(Integer.toString(quantity), x + sizeImage - 20, y + sizeImage - 5);
			}

		}

	}

	
	public void renderBackground(Graphics g, int x, int y, int size) {

		FileConstants fc = new FileConstants();
		Color[] colors = fc.getGradient(InfoStore.getItems().get(id).getQuality()).getColors();
		Point2D center = new Point2D.Float(x + size / 2, y + size / 2);
		float radius = size;
		float[] dist = { 0.0f, 0.4f };

		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(p);
		g2.fillRect(x + 2, y + 2, size - 2, size - 2);

	}

	public int getQuantity() {
		return quantity;
	}

	public void carryOutFunctions() {
		ItemId ii = InfoStore.items.get(id);
		if (ii.getHealthIncreaseValue() > 0) {
			GameHandler.getVentana().getPanelActual().getPanelLife().getHealthBar().addHealth(ii.getHealthIncreaseValue());
		}
		if (ii.getShieldIncreaseValue() > 0) {
			GameHandler.getVentana().getPanelActual().getPanelLife().getShieldBar().addHealth(ii.getShieldIncreaseValue());
		}

	}

	public void starCount() {
		ItemId ii = InfoStore.items.get(id);
		initialTime = System.currentTimeMillis();
		timeInMillies = ii.getCountDown();
		boolean or = false;
		if (ii.getShieldIncreaseValue() > 0) {
			if (GameHandler.getVentana().getPanelActual().getPanelLife().getShieldBar()
					.canMakeHealth(ii.getShieldIncreaseValue())) {
				or = true;
			}
		}
		if (ii.getHealthIncreaseValue() > 0) {
			if (GameHandler.getVentana().getPanelActual().getPanelLife().getHealthBar()
					.canMakeHealth(ii.getHealthIncreaseValue())) {
				or = true;
			}
		}
		if (or) {
			counting = true;
			IAGenerator.mainPlayer.resetSpeed();
		}

	}


	public void setItem(Item i2) {
		this.i2 = i2;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Image getI() {
		return i;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPos() {
		return pos;
	}

	public void setI(Image i, int id, Item item) {
		i2 = item;
		Image img = ImageUtils.scale(i, sizeImage, sizeImage);
		this.i = img;
		this.id = id;
	}

	public void resetImage() {

		this.i = null;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Item getItem() {
		return i2;
	}

}

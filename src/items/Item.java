package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import constants.FileConstants;
import ia.IAGenerator;
import ia.Player;
import proposiciones.ItemId;
import utils.imageUtils.ImageUtils;

public class Item {
	Image i;
	ImageUtils iu = new ImageUtils();
	private float y;
	private float x;
	private int width;
	private int height;
	private int id;
	private Image initialImage = null;
	boolean inStore=false;
	int quantity=1;
	public Item(float x, float y, int width, int height, int id, String s,int quantity) {
		if(quantity>0) {
		this.quantity=quantity;
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		ItemId ii=InfoStore.getItems().get(id);
		i =ii.getImage();
		initialImage=ii.getInitialImage();
		
		

	}

	public Image getI() {
		return i;
	}

	public void renderImage(Graphics g, float xPlayer, float yPlayer) {
	
		
		float x =this.x+xPlayer;
		float y =this.y+yPlayer;
		glowingEffect(g,x,y);
		g.drawImage(i, (int) (x + Constants.sizeBlocks / 2 - width / 2),
				(int) (y + Constants.sizeBlocks / 2 - height / 2), width, height, null);

		if (Constants.DEBUG) {
			g.drawRect((int) (x), (int) (y ), Constants.sizeBlocks, Constants.sizeBlocks);
			g.fillRect((int) (x + Constants.sizeBlocks / 2 + width / 2),
					(int) (y  + Constants.sizeBlocks / 2 + height / 2), 10, 10);

		}
	
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void glowingEffect(Graphics g, float x, float y ) {

		FileConstants fc = new FileConstants();
		Color[] colors =fc.getGradient(InfoStore.getItems().get(id).getQuality()).getColors();
		colors[1]=new Color(colors[1].getRed(),colors[1].getGreen(),colors[1].getBlue(),0);
		colors[0]=new Color(colors[0].getRed(),colors[0].getGreen(),colors[0].getBlue(),153);
		Point2D center = new Point2D.Float(x+Constants.sizeBlocks/2 , y+Constants.sizeBlocks/2);
		float radius = width*2 ;
		float[] dist = { 0.0f, 0.4f };

		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(p);
		g2.fillOval((int)(x+Constants.sizeBlocks/2-width),(int)( y +Constants.sizeBlocks/2-height), (int)(width*2),(int)( height*2));
	}

	public boolean isPlayerInside(Player p) {
		float x = (float) ((-p.realXPlayerCoordinate)) + this.x;
		float y = (float) ((-p.realYPlayerCoordinate)) + this.y;

		if (p.getX() < x + Constants.sizeBlocks && p.getX() + p.getWidth() > x && p.getY() < y + Constants.sizeBlocks
				&& p.getY() + p.getHeight() > y) {
			return true;

		} else {
			return false;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getInitialImage() {
		return initialImage;
	}

	public boolean isInStore() {
		return inStore;
	}

	public void setInStore(boolean inStore) {
		this.inStore = inStore;
	}

	public float getY(Player p) {
		return  (-(p.getY())) + this.y;
	}

	public float getX(Player p) {
		return (-(p.getX())) + this.x;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public void changeY(float y) {
		this.y = y;
	}

	public void changeX(float x) {
		this.x = x;
	}
	
	
	
	

}

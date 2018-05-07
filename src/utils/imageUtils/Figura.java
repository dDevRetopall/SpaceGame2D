package utils.imageUtils;

import java.awt.Color;
import java.awt.Image;

public class Figura {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean start;
	private Color c;
	private Image i;

	public Figura(int x,int y,int width,int height,boolean start,Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.start = start;
		this.c = c;
		
	}

	public Image getI() {
		return i;
	}

	public void setI(Image i) {
		this.i = i;
	}

	public int getX() {
		return x;
	}
	

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}

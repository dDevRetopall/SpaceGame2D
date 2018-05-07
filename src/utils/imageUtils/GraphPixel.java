package utils.imageUtils;

import java.awt.Color;

public class GraphPixel {
	private int x;
	private int y;
	private Color c;
	private boolean dib;

	public GraphPixel(int x,int y,Color c,boolean dib) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.dib = dib;
		
	}

	
	public boolean isDib() {
		return dib;
	}


	public void setDib(boolean dib) {
		this.dib = dib;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	
	
}

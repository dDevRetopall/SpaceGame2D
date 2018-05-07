package utils.imageUtils;

import java.awt.Color;

public class Pixel {
	private int x;
	
	private int y;
	private Color c;

	private int status;

	public Pixel(int x,int y,Color c,int status) {
	
		this.x = x;
		this.y = y;
		this.c = c;
		this.status = status;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getC() {
		return c;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}

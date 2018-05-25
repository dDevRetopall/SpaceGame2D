package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

import juegoEspacio.GameHandler;

public class MainProgressBar extends JProgressBar {
	private int value = 100;
	ThreadProgress pg;
	Thread t;
	private int progressValue = value;
	final int maximaLatencia = 20;
	final int minimo = 4;
	private String name2;

	public MainProgressBar(int lenght, Color c, Color c2, int width, int height, int initialValue, String name) {
		this.value = initialValue;
		this.progressValue = initialValue;
		name2 = name;
		this.setValue(initialValue);
		this.setStringPainted(true);
		this.setString("");
		this.setValue(0);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, c));
		this.setForeground(c2);
		this.setPreferredSize(new Dimension(width, height));

		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					if (value < progressValue) {
						progressValue--;

					} else if (value > progressValue) {
						progressValue++;

					} else {

					}
					// MainProgressBar.this.setString(MainProgressBar.this.getValue()+" / "+"100");
					MainProgressBar.this.setValue(progressValue);
					double proporcion = (Math.sin((((Math.abs(value - progressValue) / 100d) * Math.PI))))
							* (maximaLatencia - minimo) + minimo;

					// proporcion = (1/(1+(Math.E/Math.abs((value-progressValue)))))*20;
					try {
						Thread.sleep((int) proporcion);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

	}

	public void restoreHealth() {

	}

	public void editValue(int value) {
		this.value = value;

	}

	public boolean setFullBar() {

		if (this.value >= 100) {
			GameHandler.getVentana().getPanelActual().changeMessage("The " + name2 + " bar is full");
			return false;
		} else {
			this.value = 100;

			return true;
		}

	}

	public boolean quitHealth(int value) {
		this.value = this.value - value;
		return true;
	}

	public boolean canMakeHealth(int value) {
		if (this.value + value > 100) {

			if (this.value >= 100) {
				GameHandler.getVentana().getPanelActual().changeMessage("The " + name2 + " bar is full");
				return false;
			}
		}
		return true;
	}

	public boolean addHealth(int value) {
		if (this.value + value > 100) {

			if (this.value >= 100) {
				GameHandler.getVentana().getPanelActual().changeMessage("The " + name2 + " bar is full");
				return false;
			}
			editValue(100);

		} else {
			this.value += value;

		}
		return true;

	}

	public void resetValue() {
		value = 0;
	}

	public void initialize() {
		t.start();
		// pg= new ThreadProgress(this);
		// pg.start();
	}

	public void putValueColor(int value, Color color) {
		this.setBackground(color);
		this.setValue(value);
	}

	public int getPointsOfLife() {
		return value;
	}
}

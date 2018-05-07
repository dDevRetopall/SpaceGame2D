
package ia;

import java.awt.Color;
import java.awt.Graphics;

import constants.Constants;
import gui.ProgressBar;
import juegoEspacio.Main;
import juegoEspacio.Status;

public class Motor {
	private int incrementBetweenCirclesDefault;
	private int circleDiameterDefault;
	private int numCirclesDefault;
	private int countWithMotor = 0;

	public static final int LEFT_SIDE = 0;
	public static final int RIGHT_SIDE = 1;
	public static final int UP_SIDE = 2;
	public static final int DOWN_SIDE = 3;

	private int location;
	private boolean motorHot = false;
	public boolean custom = false;
	public Color customColor;
	public ProgressBar pb;
	public Motor(int incrementBetweenCircles, int circleDiameter, int location) {
		this.incrementBetweenCirclesDefault = incrementBetweenCircles;
		this.circleDiameterDefault = circleDiameter;
		this.numCirclesDefault = 10;
		this.location = location;
		pb=Status.addMotorStatus(location);

	}

	public void renderMotor(Graphics g, float xPlayer, float yPlayer, int widthPlayer, int heightPlayer) {
		int numCircles = numCirclesDefault;
		countWithMotor++;
		float circleDiameter = ((15 + circleDiameterDefault * countWithMotor / 100f));
		float increment = 5 + (incrementBetweenCirclesDefault * (countWithMotor / 100f));
		if (countWithMotor <= 124 && !motorHot) {
			for (int i = 0; i < numCircles; i++) {
				circleDiameter -= 1;
				float changerX = 1, changerY = +1;
				if (location == UP_SIDE) {
					changerX = -circleDiameter / 2;
					changerY = -i * increment - circleDiameter / 2;
				}
				if (location == DOWN_SIDE) {
					changerX = -circleDiameter / 2;
					changerY = +i * increment - circleDiameter / 2;
				}
				if (location == LEFT_SIDE) {
					changerX = -i * increment - circleDiameter / 2;
					changerY = -circleDiameter / 2;
				}
				if (location == RIGHT_SIDE) {
					changerX = +i * increment - circleDiameter / 2;
					changerY = -circleDiameter / 2;
				}
				setColor(g, i);

				g.fillOval((int) ((xPlayer + widthPlayer / 2) + changerX),
						(int) ((yPlayer + heightPlayer / 2) + changerY), (int) (circleDiameter), (int) circleDiameter);
				if(Constants.levelGraphical>1) {
				g.setColor(Color.black);
				g.drawOval((int) ((xPlayer + widthPlayer / 2) + changerX),
						(int) ((yPlayer + heightPlayer / 2) + changerY), (int) (circleDiameter), (int) circleDiameter);
				}
			}

		} else {

			motorHot = true;

		}
	}

	public void setColor(Graphics g, int i) {

		if (custom) {

			g.setColor(customColor);
		} else {
			g.setColor(new Color((int) (countWithMotor * 2), (int) (((255 * i) / 10f)), 255 - countWithMotor * 2,
					(int) (140 - i * 6)));
		}
	}

	public int getTemperatureMotor() {
		return countWithMotor;
	}

	public void coolTemperature() {
		countWithMotor --;
	}

	public void setTemperatureMotor(int temperature) {
		countWithMotor = temperature;
	}

	public int getLocation() {
		return location;
	}

	public boolean isMotorHot() {
		return motorHot;
	}

	public void setMotorHot(boolean motorHot) {
		this.motorHot = motorHot;
	}

}

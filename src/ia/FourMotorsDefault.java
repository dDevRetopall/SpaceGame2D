package ia;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import gui.ProgressBar;
import juegoEspacio.GameHandler;
import juegoEspacio.Status;

public class FourMotorsDefault {
	private boolean initRender = true;
	Motor mUp;
	Motor mDown;
	Motor mLeft;
	Motor mRight;

	public FourMotorsDefault(int incrementDistance, int incrementSize) {
		mUp = new Motor(incrementDistance, incrementSize, Motor.UP_SIDE);
		mDown = new Motor(incrementDistance, incrementSize, Motor.DOWN_SIDE);
		mRight = new Motor(incrementDistance, incrementSize, Motor.RIGHT_SIDE);
		mLeft = new Motor(incrementDistance, incrementSize, Motor.LEFT_SIDE);
	}

	public void loadingMotorUP(Graphics g) {

		mUp.setTemperatureMotor(mUp.getTemperatureMotor() - 1);

		if (mUp.getTemperatureMotor() <= 1) {
			mUp.setMotorHot(false);
			mUp.pb.setString("");
		}

	}

	public void loadingMotorDOWN(Graphics g) {

		mDown.setTemperatureMotor(mDown.getTemperatureMotor() - 1);

		if (mDown.getTemperatureMotor() <= 1) {
			mDown.setMotorHot(false);
			mDown.pb.setString("");
		}

	}

	public void loadingMotorLeft(Graphics g) {

		mLeft.setTemperatureMotor(mLeft.getTemperatureMotor() - 1);

		if (mLeft.getTemperatureMotor() <= 1) {
			mLeft.setMotorHot(false);
			mLeft.pb.setString("");
		}

	}

	public void loadingMotorRight(Graphics g) {

		mRight.setTemperatureMotor(mRight.getTemperatureMotor() - 1);

		if (mRight.getTemperatureMotor() <= 1) {
			mRight.setMotorHot(false);
			mRight.pb.setString("");
		}

	}

	public Color getTemperatureColor(int temperature) {

		if (temperature > 75) {
			return Color.red;
		}

		if (temperature > 50) {
			return Color.orange;
		}
		if (temperature > 25) {
			return Color.yellow;
		}
		if (temperature > 0) {
			return Color.green;
		}
		return null;

	}

	public void changeLabelColor(boolean hot, JProgressBar pb) {
		if (hot) {
			Status.searchPaneles().get(pb).setForeground(Color.red);
		}else {
			Status.searchPaneles().get(pb).setForeground(Color.white);
		}
	}

	public void updateProgressBar(Color color, ProgressBar pb, double progress) {
		
		pb.setForeground(color);
		progress = (int) progress;
		pb.setValue((int) progress);
		pb.setString(progress + " %");

	}

	public void dibujarMotores(Graphics g, Player p) {
		if (getMotorUp().isMotorHot()) {
			loadingMotorUP(g);
		}
		if (getMotorDown().isMotorHot()) {
			loadingMotorDOWN(g);
		}

		if (getMotorRight().isMotorHot()) {
			loadingMotorRight(g);
		}
		if (getMotorLeft().isMotorHot()) {
			loadingMotorLeft(g);
		}
		updateProgressBar(getTemperatureColor(mUp.getTemperatureMotor()), mUp.pb,
				mUp.getTemperatureMotor() * 100 / 124f);
		changeLabelColor(mUp.isMotorHot(), mUp.pb);
		if (p.abajo && !mUp.isMotorHot()) {
			mUp.renderMotor(g, p.realXPlayerCoordinate, p.realYPlayerCoordinate, p.getWidth(), p.getHeight());
		} else if (!mUp.isMotorHot()&&mUp.getTemperatureMotor()>=0) {
			mUp.coolTemperature();
		}
		updateProgressBar(getTemperatureColor(mDown.getTemperatureMotor()), mDown.pb,
				mDown.getTemperatureMotor() * 100 / 124f);
		changeLabelColor(mDown.isMotorHot(), mDown.pb);
		if (p.arriba && !mDown.isMotorHot()) {
			mDown.renderMotor(g, p.realXPlayerCoordinate, p.realYPlayerCoordinate, p.getWidth(), p.getHeight());
		} else if (!mDown.isMotorHot()&&mDown.getTemperatureMotor()>=0) {
			mDown.coolTemperature();
		}
		updateProgressBar(getTemperatureColor(mRight.getTemperatureMotor()), mRight.pb,
				mRight.getTemperatureMotor() * 100 / 124f);
		changeLabelColor(mRight.isMotorHot(), mRight.pb);
		if (p.izquierda && !mRight.isMotorHot()) {

			mRight.renderMotor(g, p.realXPlayerCoordinate, p.realYPlayerCoordinate, p.getWidth(), p.getHeight());
		} else if (!mRight.isMotorHot()&&mRight.getTemperatureMotor()>=0) {
			mRight.coolTemperature();
		}
		updateProgressBar(getTemperatureColor(mLeft.getTemperatureMotor()), mLeft.pb,
				mLeft.getTemperatureMotor() * 100 / 124f);
		changeLabelColor(mLeft.isMotorHot(), mLeft.pb);
		if (p.derecha && !mLeft.isMotorHot()) {
			mLeft.renderMotor(g, p.realXPlayerCoordinate, p.realYPlayerCoordinate, p.getWidth(), p.getHeight());
		} else if (!mLeft.isMotorHot()&&mLeft.getTemperatureMotor()>=0) {
			mLeft.coolTemperature();
		}

	}

	public void resetProgressMotors() {
		GameHandler.getVentana().getPanelActual().remove(Status.pStatus);
		GameHandler.getVentana().getPanelActual().updateUI();
		Status.initStatus();
		GameHandler.getVentana().getPanelActual().add(Status.pStatus);

	}

	public Motor getMotorUp() {
		return mUp;
	}

	public Motor getMotorDown() {
		return mDown;
	}

	public Motor getMotorLeft() {
		return mLeft;
	}

	public Motor getMotorRight() {
		return mRight;
	}

}

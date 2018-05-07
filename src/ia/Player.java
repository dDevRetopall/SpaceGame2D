package ia;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;

import constants.Constants;
import graphics.shooting.DisparoEspecial;
import graphics.shooting.Recharger;
import gui.Button;
import gui.ProgressBar;
import juegoEspacio.Main;
import juegoEspacio.Status;
import juegoEspacio.Ventana;

public class Player {
	float constanteFrenado = 0.994f;
	public static final int RECTANGLE = 0;
	public static final int SPHERE = 1;
	private float x;
	private float y;
	private int type;
	private Color color;
	private int width;
	private int height;
	private float vx = 0;
	private float vy = 0;
	public boolean frenando = false;
	public boolean arriba = false;
	public boolean abajo = false;
	public boolean derecha = false;
	public boolean izquierda = false;

	public float radio;
	public int realXPlayerCoordinate = 0;
	public int realYPlayerCoordinate = 0;
	public boolean initMotors = true;
	public boolean muerto = false;
	public boolean completed = false;
	FourMotorsDefault motors;

	boolean setupMenu = false;
	
	public Recharger r ;
	public Recharger r2 ;
	public Recharger r3 ;
	public Recharger r4 ;

	public Player(int x, int y, int type, Color color, int width, int height) {
		motors = new FourMotorsDefault(5, 15);
		this.x = x;
		this.y = y;
		this.type = type;
		this.color = color;
		this.width = width;
		this.height = height;
		radio = width / 2;

		Main.getVentana();
		realXPlayerCoordinate = Ventana.centroXPantalla - this.width / 2;
		realYPlayerCoordinate = Ventana.centroYPantalla - this.height / 2;
		
		createProgressBars();
	}
	public void createProgressBars() {
		ProgressBar specialAttack = Status.addProgressBar("Special attack     ");
		ProgressBar bomb=Status.addProgressBar("Bomb recharge  ");
		ProgressBar normal=Status.addProgressBar("Normal shot        ");
		ProgressBar shield=Status.addProgressBar("Shield                   ");
		r = new Recharger(200, normal) {

			@Override
			public void function() {
				Ventana.disparoNormalHabilitado=true;

			}
		};
		r2= new Recharger(20000, specialAttack) {

			@Override
			public void function() {
				Ventana.disparoEspecialHabilitado=true;
			}
		};
		 r3 = new Recharger(8000, bomb) {

			@Override
			public void function() {
				Ventana.bombaHabilitada=true;

			}
		};
		r4 = new Recharger(3000, shield) {

			@Override
			public void function() {
				

			}
		};
	}
	public void paintCharacter(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		if (Constants.DEBUG) {
			g.setColor(Color.red);
			g.drawOval((int)x,(int) y, width, height);
			g.drawLine((int)x+width/2, (int)y+height/2, realXPlayerCoordinate+width/2, realYPlayerCoordinate+height/2);
			System.out.println("X player: " + getX() + " Y player: " + getY());
			System.out.println("Real X player: " + this.realXPlayerCoordinate+ " Real Y player: " + this.realYPlayerCoordinate);
		}
		if (initMotors) {
			initMotors = false;
		}

		updateCoordinates(g);

		if (!muerto && !completed) {

			motors.dibujarMotores(g, this);
			g.setColor(color);

			if (type == RECTANGLE) {
				g.fillRect((int) x, (int) y, width, height);
			} else if (type == SPHERE) {
				if (Constants.levelGraphical == 0) {
					g.setColor(Color.WHITE);
					g.fillOval((int) realXPlayerCoordinate, (int) realYPlayerCoordinate, width, height);
				} else {
					Paint paint;
					if(Constants.DEBUG) {
					 paint = new RadialGradientPaint(realXPlayerCoordinate + width / 2,
							realYPlayerCoordinate + height / 2, width + 10, Constants.FRACTIONS, Constants.GREEN);
					g2.setPaint(paint);
					g2.fillOval((int) realXPlayerCoordinate - 25, (int) realYPlayerCoordinate - 25, width + 50,
							height + 50);
					}
				
					paint = new RadialGradientPaint(realXPlayerCoordinate + width / 2,
							realYPlayerCoordinate + height / 2, width, Constants.FRACTIONS, Constants.COLORS);
					g2.setPaint(paint);
					g2.fillOval((int) realXPlayerCoordinate, (int) realYPlayerCoordinate, width, height);
					
				}

			}
		}

	}

	public void putMenu(Graphics g,String texto) {
		Font f = new Font("Arial", Font.BOLD, 50);
	
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics();

		int widthTexto = fm.stringWidth(texto);
		int heightTexto = fm.getHeight();

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, Ventana.centroYPantalla + 150 + heightTexto / 2 - 100, Ventana.screenSize.width,
				heightTexto + 100);

		g.setColor(Color.WHITE);
		g.drawString(texto, Ventana.centroXPantalla - widthTexto / 2, Ventana.centroYPantalla + heightTexto / 2 + 150);

	}

	public void updateCoordinates(Graphics g) {

		if (!muerto &&!completed) {
			if (this.arriba && motors.getMotorDown().isMotorHot()) {
				Main.getVentana().getPanelActual().changeMessage("Motor superior caliente");
			}
			if (this.abajo && motors.getMotorUp().isMotorHot()) {
				Main.getVentana().getPanelActual().changeMessage("Motor inferior caliente");
			}
			if (this.izquierda && motors.getMotorRight().isMotorHot()) {
				Main.getVentana().getPanelActual().changeMessage("Motor izquierdo caliente");
			}
			if (this.derecha && motors.getMotorLeft().isMotorHot()) {
				Main.getVentana().getPanelActual().changeMessage("Motor derecho caliente");
			}
			
			if (this.arriba && !motors.getMotorDown().isMotorHot()) {
				this.vy = (float) ((double) this.vy - 0.05);
			}
			if (this.abajo && !motors.getMotorUp().isMotorHot()) {
				this.vy = (float) ((double) this.vy + 0.05);
			}
			if (this.izquierda && !motors.getMotorRight().isMotorHot()) {
				this.vx = (float) ((double) this.vx - 0.05);
			}
			if (this.derecha && !motors.getMotorLeft().isMotorHot()) {
				this.vx = (float) ((double) this.vx + 0.05);
			}
			if (this.frenando) {
				constanteFrenado = 0.98f;
				renderBrakeAnimation(g);

			} else {
				constanteFrenado = 0.994f;
			}
		} else {

			constanteFrenado = 0.985f;

		}
		this.y += this.vy;
		this.x += this.vx;

		this.vy = (float) ((double) this.vy * constanteFrenado);
		this.vx = (float) ((double) this.vx * constanteFrenado);

	}
	public void resetSpeed() {
		vx=0;
		vy=0;
	}

	public void renderBrakeAnimation(Graphics g) {
		Color brakeMotorColor = new Color(230, 230, 230, 124);
		if (this.vx > 0) {
			Motor ms = new BrakeMotor(10, 20, Motor.RIGHT_SIDE, brakeMotorColor);
			ms.renderMotor(g, this.realXPlayerCoordinate, this.realYPlayerCoordinate, this.width, this.height);
		}
		if (this.vx < 0) {
			Motor ms = new BrakeMotor(10, 20, Motor.LEFT_SIDE, brakeMotorColor);
			ms.renderMotor(g, this.realXPlayerCoordinate, this.realYPlayerCoordinate, this.width, this.height);
		}
		if (this.vy > 0) {
			Motor ms = new BrakeMotor(10, 20, Motor.DOWN_SIDE, brakeMotorColor);
			ms.renderMotor(g, this.realXPlayerCoordinate, this.realYPlayerCoordinate, this.width, this.height);
		}
		if (this.vy < 0) {
			Motor ms = new BrakeMotor(10, 20, Motor.UP_SIDE, brakeMotorColor);
			ms.renderMotor(g, this.realXPlayerCoordinate, this.realYPlayerCoordinate, this.width, this.height);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getVx() {
		return vx;
	}

	public float getVy() {
		return vy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCentroX() {
		return (int) ((int) x + width / 2);

	}

	public int getCentroY() {
		return (int) ((int) y + height / 2);
	}

	public FourMotorsDefault getMotors() {
		return motors;
	}
	public Recharger getNormalShootRecharger() {
		return r;
	}
	public Recharger getSpecialShootRecharger() {
		return r2;
	}
	public Recharger getBombRecharger() {
		return r3;
	}
	public Recharger getShieldRecharger() {
		return r4;
	}
	

}

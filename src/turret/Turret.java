package turret;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import cañon.Cañon;
import cañon.TurretCanon;
import ia.IAGenerator;
import vector.OperacionesVectores;
import vector.Vector;


public class Turret {
	public int x=500,y=500;
	
	public int weaponType;
	public static final int missileMode=0;
	public static final int laserMode=1;
	public static final int defenseMode=0;
	public static final int attackMode=0;
	public static final int health=500;
	public static float radianSpeed=(float) ((2*Math.PI)/720);
	public static float radianPosition=(float) (Math.PI/2);
	TurretCanon tc;

	private int radius;
	public Turret(int radius,int x,int y) {
		this.radius = radius;
		this.x=x;
		this.y=y;
		
	}
	public void rotate() {
		radianPosition+=radianSpeed;
		tc.rotateCanon(radianPosition);
	}
	public void setup() {
		 tc=new TurretCanon(x, y,radius);
	
	}
	public void render(Graphics g) {
		if(isInside()) {
			tc.setWarning(true);
		}else{
			tc.setWarning(false);
		}
	
		rotate();
		
		tc.render(g);
		
		
	}
	public boolean isInside() {
		Vector v = OperacionesVectores.generarVector(IAGenerator.mainPlayer.getX()+IAGenerator.mainPlayer.realXPlayerCoordinate, IAGenerator.mainPlayer.getY()+IAGenerator.mainPlayer.realYPlayerCoordinate, x, y);
		if(OperacionesVectores.moduloVector(v)<=tc.getRadiusOfAttack()) {
			
			return true;
		}
		return false;
	}
	
	public void setBeam(Image i) {
		
	}
}

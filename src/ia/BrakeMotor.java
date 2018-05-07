package ia;

import java.awt.Color;

public class BrakeMotor extends Motor{

	private Color c;

	public BrakeMotor(int incrementBetweenCircles, int circleDiameter, int location,Color c) {
		super(incrementBetweenCircles, circleDiameter, location);
		this.c = c;
		this.custom=true;
		this.customColor=c;
		
	}
	

}

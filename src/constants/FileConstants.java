package constants;

import java.awt.Color;

import utils.color.GradientColor;

public class FileConstants {
	public  GradientColor common=new GradientColor(new Color(108,108,108,200), new Color(160,160,160,200), 0);
	public  GradientColor special=new GradientColor(new Color(0, 153, 0,200), new Color(0, 102, 0,200) , 1);
	public  GradientColor rare=new GradientColor( new Color(33,101,171,200),new Color(22,106,201,200), 2);
	public  GradientColor epic=new GradientColor(new Color(197,128,250,200), new Color(155,29,252,200), 3);
	public  GradientColor legendary=new GradientColor( new Color(255,153,51,200),new Color(204,102,0,200), 4);
	
	public FileConstants() {
		
	}
	public GradientColor getGradient(int quality) {
		switch(quality) {
		case 0:
			return common;
		case 1:
			return special;
		case 2:
			return rare;
		case 3:
			return epic;
		case 4 :
			return legendary;
		}
		return common;
	}
}

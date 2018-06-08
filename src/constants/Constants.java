package constants;

import java.awt.Color;
import java.awt.Font;

public class Constants {
	public static final boolean startDirectlyGame=true;
	
	//Files
	public static String resources = "assets/files/resources.dbg";
	public static String data = "assets/files/data.dbg";
	public static String weapons = "assets/files/weapons.dbg";
	
	//Essentials ajustes
	public static int sizeBlocks=100;
	public static boolean useFileItemsSize=true;
	//to do
	public static boolean scaleAllFromSize=false;
	public static int levelEffects=1;
	public static int levelGraphical=1;
	public static Font FONT=new Font("Arial", Font.BOLD, 16);
	
	//Debug
	public static  boolean DEBUG=false;
	public static  boolean DEBUGFiles=false;
	
	//Colors UI editing
	public static final float[] FRACTIONS = { 0.0f, 1.0f };
	public static final Color[] COLORS = { Color.LIGHT_GRAY, new Color(0, 0, 0, 0) };
	public static final Color[] GREEN = {new Color(73,185,96,200), new Color(0, 0, 0, 0) };
	public static final Color[] BLUE = {new Color(0,0,153,255), new Color(0, 0, 153,0) };
	public static Color indestructibleColor = new Color(127,127,127);
	
	//Timers
	public static  int timeToDisappearProgressBarLadrillos=5000;
	public static  int durationOfWarnings=1000;
	private static int gameMode=1;
	public static Color colorBomb = new Color(153,0,0);
	
	//Game settings
	public static float probabilityOfItems = 0.01f;
	public static float ratio = 100;
	public static float buildTime=5000;
	public static float maxDiameter=800;
	public static float minDiameter=300;
	public static float radioBomba=200;
	public static int widthMap=0;
	public static int heightMap=0;
	
	//Images
	public static String imagesPath="assets/images/";
	public static String file=imagesPath+"Mapa1.png";

	//Analytics
	public static boolean showInfo=true;
	public static boolean seeImagetreatmentReal=false;
	
	public static int getGamemode() {
		return gameMode;
	}
	public static void setGamemode(int value) {
		gameMode=value;
	}
	


}

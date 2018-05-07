package ia;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class IAGenerator {
	public static ArrayList<Player> characters= new ArrayList<>();
	public static ArmedPlayer mainPlayer;

	public static void createMainCharacter(int x,int y,int width,int height,int type,Color color) {
		mainPlayer= new ArmedPlayer(x, y, type, color, width, height);
		characters.add(mainPlayer);
		
	}
	public static void createCharacter(int x,int y,int width,int height,int type,Color color) {
		Player player= new Player(x, y, type, color, width, height);
		characters.add(player);
		
	}
	public static void updateCharacters(Graphics g) {
		for(Player c :characters) {
			c.paintCharacter(g);
		}
	}
}

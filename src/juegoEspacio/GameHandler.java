package juegoEspacio;

import java.util.ArrayList;

import constants.Constants;
import items.InfoStore;
import launcher.SettingsLauncher;
import proposiciones.ItemId;
import utils.dataUtils.FileUtils;

public class GameHandler {
	static Ventana v;
	public static void startGame() {
		
		System.out.println("Opening assets");
		System.out.println("Starting sounds");
		//SoundHandler.createNewSound("assets/sounds/df.wav");
		System.out.println("Reading images");
		//ImageUtils.getBeam("beams.png", 1);
//		ImageUtils.f.dispose();
		System.out.println("Reading files");
		System.out.println("Reading data file");
		ArrayList<ItemId>items=FileUtils.readData(Constants.data);
		System.out.println("Reading resources");
		ArrayList<ItemId>items2=FileUtils.readData(Constants.resources);
		System.out.println("Reading weapons");
		ArrayList<ItemId>items3=FileUtils.readData(Constants.weapons);
		InfoStore.storeItems(items);
		InfoStore.storeItems(items2);
		InfoStore.storeItems(items3);

		
		v = new Ventana();
		v.setVisible(true);
	
	
		
	
		System.out.println("Total items: "+InfoStore.getItems().size());
		v.getPanelActual().guardarPixelesEnArray();
	
		v.getPanelActual().init();
		v.getPanelActual().inicializarPartida();

		v.gameScreenEnabled=true;

		
		
	}
	public static Ventana getVentana() {
		if(v==null) {
		//	System.out.println("Juego no inicializado todavia");
		}
		return v;
	}
	public static void stopGame() {
		
	}
}

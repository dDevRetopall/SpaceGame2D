package juegoEspacio;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import constants.Constants;
import items.InfoStore;
import proposiciones.ItemId;
import utils.dataUtils.FileUtils;
import utils.imageUtils.ImageUtils;
import utils.soundSystem.SoundHandler;

public class Main {
	static Ventana v;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			UIManager.put("ProgressBar.selectionForeground", new Color(0,0,153));
			UIManager.put("ProgressBar.selectionBackground", new Color(0,0,153));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
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
		System.out.println("Total items: "+InfoStore.getItems().size());
		v = new Ventana();
		v.setVisible(true);
		
		v.getPanelActual().guardarPixelesEnArray();
		
		v.getPanelActual().init();
		v.getPanelActual().inicializarPartida();
		v.gameScreenEnabled=true;


		
		

	}

	public static Ventana getVentana() {
		return v;
	}

}

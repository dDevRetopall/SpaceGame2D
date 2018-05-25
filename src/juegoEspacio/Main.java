package juegoEspacio;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import constants.Constants;
import items.InfoStore;
import launcher.SettingsLauncher;
import proposiciones.ItemId;
import utils.dataUtils.FileUtils;
import utils.imageUtils.ImageUtils;
import utils.soundSystem.SoundHandler;

public class Main {
	static SettingsLauncher sl ;
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
	
		sl= new SettingsLauncher();
		sl.setVisible(true);
		
		

		


		
		

	}

	

}

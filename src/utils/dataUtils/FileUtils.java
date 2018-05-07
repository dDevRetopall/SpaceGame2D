package utils.dataUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSpinnerUI;

import constants.Constants;
import proposiciones.Argumento;
import proposiciones.ItemId;
import proposiciones.Objeto;

public class FileUtils {
	// Rehacer
	//// Settings
	static String type = "";

	public static ArrayList<ItemId> readData(String archive) {
		File f = new File(archive);
		if (f.exists()) {
			System.out.println("-File already exists!");
			try {
				System.out.println("-Starting");
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String text, nextLine;
				ArrayList<String> lineas = new ArrayList<>();
				System.out.print("-Reading lines");
				try {
					while ((nextLine = br.readLine()) != null) {
						lineas.add(nextLine);

					}
					ArrayList<Objeto> objetos = checkSintax(lineas);
					if (Constants.DEBUGFiles) {
						printInfo(objetos);
					}
					System.out.println(" OK");
					br.close();

					System.out.println("-Detecting objetcs");
					return distribuirAtributos(objetos);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				System.out.println("-File doesnt exists... Creating");
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	private static void printInfo(ArrayList<Objeto> objetos) {

		for (int i = 0; i < objetos.size(); i++) {
			System.out.println("*" + objetos.get(i).getName());
			for (Argumento a : objetos.get(i).getArgumentos()) {
				System.out.println("	-" + a.getKey() + " -> " + a.getValue());
			}
		}
	}

	private static ArrayList<Objeto> checkSintax(ArrayList<String> lineas) {
		ArrayList<Objeto> objetos = new ArrayList<>();
		ArrayList<Argumento> argumentos = new ArrayList<>();
		String name = "";
		boolean llaveAbierta = false;
		boolean wasLlaveAbierta = false;
		boolean gotType = false;
		for (int i = 0; i < lineas.size(); i++) {

			for (int x = 0; x < lineas.get(i).length(); x++) {

				if (gotType) {
					if (!hasComment(lineas.get(i))) {
						if (llaveAbierta == false) {
							if (lineas.get(i).charAt(x) == '{') {
								argumentos = new ArrayList<>();
								llaveAbierta = true;
								wasLlaveAbierta = false;
								break;

							} else {
								name = name + lineas.get(i).charAt(x);
							}
						}
						if (lineas.get(i).charAt(x) == '}') {

							llaveAbierta = false;
							objetos.add(new Objeto(name, argumentos));
							name = "";
							break;
						}
					} else {
						break;
					}
				} else {
					if (lineas.get(i).charAt(x) == '*') {
						type = getType(lineas.get(i), x);
						gotType = true;
						break;
					}

				}
			}
			if (!hasComment(lineas.get(i))) {
				if (llaveAbierta == true) {
					if (!wasLlaveAbierta) {
						wasLlaveAbierta = true;
					} else {
						String linea = lineas.get(i);
						linea = linea.replaceAll(" ", "");
						linea = linea.trim();

						checkFunction(name, lineas.get(i), argumentos);

					}

				}
			}

		}
		return objetos;
	}

	private static String getType(String linea, int currentPos) {
		String type = "";
		for (int x = currentPos + 1; x < linea.length(); x++) {
			if (linea.charAt(x) != ';') {
				type = type + linea.charAt(x);
			} else {
				break;
			}
		}
		System.out.println( "Type detected: "+type);
		return type;

	}

	private static boolean hasComment(String linea) {
		for (int x = 0; x < linea.length(); x++) {
			if (linea.charAt(x) == '/' && linea.charAt(x + 1) == '/') {
				return true;
			}
		}
		return false;

	}

	private static void checkFunction(String name, String argumento, ArrayList<Argumento> argumentos) {

		String key = "";
		String value = "";
		boolean igualDetectado = false;
		for (int i = 0; i < argumento.length(); i++) {
			if (argumento.charAt(i) == ';') {
				break;
			} else {
				if (argumento.charAt(i) != '=') {
					if (igualDetectado) {
						value = value + argumento.charAt(i);
					} else {
						key = key + argumento.charAt(i);
					}
				} else {
					igualDetectado = true;
				}
			}
		}
		key.trim();
		value.trim();
		argumentos.add(new Argumento(key, value));
	}

	public static ArrayList<ItemId> distribuirAtributos(ArrayList<Objeto> objetos) {
		ArrayList<ItemId> items = new ArrayList<>();
		for (int i = 0; i < objetos.size(); i++) {
			Objeto o = objetos.get(i);
			items.add(getPosiblesArgumentos(o));
		}
		return items;
	}

	public static ItemId getPosiblesArgumentos(Objeto o) {
		for (int j = 0; j < o.getArgumentos().size(); j++) {
			Argumento a = o.getArgumentos().get(j);
			if (type.equals("resources")) {
				 Arguments.checkArgumentsResources(o.getName(), a);
			}
			if (type.equals("weapons")) {
				 Arguments.checkArgumentsWeapons(o.getName(), a);
			}
			if (type.equals("consumibles")) {
			
				 Arguments.checkArgumentsHealers(o.getName(), a);

			}
			

		}
		ItemId ii=Arguments.returnItem();
		Arguments.resetAll();
		
		return ii;
		
	}

}
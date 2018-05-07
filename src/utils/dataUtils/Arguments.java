package utils.dataUtils;

import proposiciones.Argumento;
import proposiciones.ItemId;

public class Arguments {
	static boolean healer = false;
	static boolean weapon = false;
	static boolean material = false;
	static boolean detectado = false;
	static int id = 0;
	static int width = 0;
	static int height = 0;
	static int quality = 0;
	static int maxStacks = 1;
	static String path = "";

	static float probability = 0f;

	static int initialQuantity = 1;
	private static String name;
	private static int incrementMaterial;
	private static int shieldIncreaseValue;
	private static int healthIncreaseValue;
	private static int countdown;

	public static void checkArgumentsWeapons(String name, Argumento a) {
		Arguments.name = name;
		
		weapon = true;
		
		
		
		checkGeneralArguments(a);
	
	}

	public static void checkArgumentsResources(String name, Argumento a) {
		Arguments.name = name;
		
	
		material = true;
		if (a.getKey().equals("incrementMaterial")) {
			incrementMaterial = Integer.parseInt((String) a.getValue());
			material = true;
			detectado = true;

		}
		checkGeneralArguments(a);
		
	}

	public static void checkArgumentsHealers(String name, Argumento a) {
		Arguments.name = name;
		
	
	
		
		healer = true;
		if (a.getKey().equals("incrementShield")) {
			shieldIncreaseValue = Integer.parseInt((String) a.getValue());
			healer = true;

			detectado = true;
		}
		if (a.getKey().equals("incrementHealth")) {
			healthIncreaseValue = Integer.parseInt((String) a.getValue());
			healer = true;
			detectado = true;
		}
		if (a.getKey().equals("countDown")) {
			countdown = Integer.parseInt((String) a.getValue());
			detectado = true;
		}
		checkGeneralArguments(a);
		
	}

	public static void checkGeneralArguments(Argumento a) {

		try {

			if (a.getKey().equals("id")) {
				detectado = true;

				id = Integer.parseInt((String) a.getValue());
			}

			if (a.getKey().equals("probability")) {
				probability = Float.parseFloat((String) a.getValue());
				detectado = true;
			}
			if (a.getKey().equals("path")) {
				path = (String) a.getValue();
				detectado = true;
			}

			if (a.getKey().equals("width")) {
				width = Integer.parseInt((String) a.getValue());
				detectado = true;
			}
			if (a.getKey().equals("height")) {
				height = Integer.parseInt((String) a.getValue());
				detectado = true;
			}

			if (a.getKey().equals("maxStacks")) {
				maxStacks = Integer.parseInt((String) a.getValue());
				detectado = true;

			}
			if (a.getKey().equals("initialQuantity")) {
				initialQuantity = Integer.parseInt((String) a.getValue());
				detectado = true;

			}

			if (a.getKey().equals("quality")) {
				String valor = ((String) a.getValue());
				detectado = true;
				if (valor.equals("common")) {
					quality = 0;
				}
				if (valor.equals("special")) {
					quality = 1;
				}
				if (valor.equals("rare")) {
					quality = 2;
				}
				if (valor.equals("epic")) {
					quality = 3;
				}
				if (valor.equals("legendary")) {
					quality = 4;
				}
			}
			if (!detectado) {
				System.out.println("Comando no  detectado: " + a.getKey());
			}
		} catch (NumberFormatException e) {
			System.out.println(
					"El valor dado del argumento " + a.getKey() + " no se corresponde con el predeterminado. Valor : "
							+ a.getValue() + " Cause: " + e.getMessage());
		}
	}
	public static ItemId returnItem() {
		if(healer) {
			return new ItemId(name, path, shieldIncreaseValue, healthIncreaseValue, probability, countdown, id, width,
					height, quality, maxStacks, initialQuantity);
		}
		if(material) {
			return new ItemId(name, path, true, probability, id, width, height, quality, incrementMaterial, maxStacks);
		}
		if(weapon) {
			return new ItemId(name, path, 0, 0, probability, 0, id, width, height, quality, maxStacks, initialQuantity);
		}
		System.out.println("Don´t detected type");
		return null;
	}

	public static void resetAll() {
		healer = false;
		weapon = false;
		material = false;
		detectado = false;
		id = 0;
		width = 0;
		height = 0;
		quality = 0;
		maxStacks = 1;
		String path = "";

		probability = 0f;

		initialQuantity = 1;

	}
}

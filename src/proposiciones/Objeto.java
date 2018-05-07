package proposiciones;

import java.util.ArrayList;

public class Objeto {
	private String name;
	private ArrayList<Argumento> argumentos;

	public Objeto(String name ,ArrayList<Argumento>argumentos) {
		this.name = name;
		this.argumentos = argumentos;
		
	}

	public String getName() {
		return name;
	}

	public ArrayList<Argumento> getArgumentos() {
		return argumentos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArgumentos(ArrayList<Argumento> argumentos) {
		this.argumentos = argumentos;
	}
	
}

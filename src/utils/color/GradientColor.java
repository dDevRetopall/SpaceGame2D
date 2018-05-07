package utils.color;

import java.awt.Color;

public class GradientColor {
	private Color principal;
	private Color secundario;
	private int id=0;
	public GradientColor(Color principal, Color secundario,int id) {
		this.principal = principal;
		this.secundario = secundario;

	}

	public Color[] getColors() {
		Color[] colores = { principal, secundario };
		return colores;
	}

	public Color getPrincipal() {
		return principal;
	}

	public Color getSecundario() {
		return secundario;
	}

	public int getId() {
		return id;
	}

	public void setPrincipal(Color principal) {
		this.principal = principal;
	}

	public void setSecundario(Color secundario) {
		this.secundario = secundario;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

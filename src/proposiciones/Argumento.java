package proposiciones;

public class Argumento {
	private Object value;
	private String key;
	

	public Argumento(String key,Object value) {
		this.key = key;
		
		this.value = value;
		
	}


	public Object getValue() {
		return value;
	}


	public String getKey() {
		return key;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public void setKey(String key) {
		this.key = key;
	}
	
}

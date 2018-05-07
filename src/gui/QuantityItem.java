package gui;

import javax.swing.JLabel;

public class QuantityItem extends JLabel{
	public QuantityItem() {
		
	}
	public void increaseQuantity(int i) {
		this.setText(Integer.toString(Integer.parseInt(this.getText())+i));
	}
	public void decreaseQuantity(int i) {
		this.setText(Integer.toString(Integer.parseInt(this.getText())-i));
	}
	public boolean hasEnoughQuantity(int i) {
		if((i<=Integer.parseInt(this.getText()))){
			return true;
		}
		return false;
	}
	public int getRemainQuantity(int i) {
		return(Integer.parseInt(this.getText())-i);
	}
}

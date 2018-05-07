package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import items.InfoStore;
import proposiciones.ItemId;

public class StuffPanel extends JPanel{
	
	HashMap<Integer , QuantityItem>materials = new HashMap<>();
	ArrayList<JLabel>labels = new ArrayList<>();
	public StuffPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
			JLabel lTitle = new JLabel("   Available materials");
			this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
			
			
			lTitle.setFont(Constants.FONT);
			lTitle.setForeground(Color.gray);
			for(ItemId ii:InfoStore.getItems()) {
				if(ii.isMaterial()) {
					JLabel l=generateNewItem(ii.getPath(),ii.getId());
					labels.add(l);
				}
			}
			//pTitle.setOpaque(false);
			
			this.add(lTitle);
			for(JLabel l:labels) {
				this.add(l);
			}
			
		
	}
	public JLabel generateNewItem(String file,int id) {
		QuantityItem l = new QuantityItem();
		try {
			Image i = ImageIO.read(new File(file));
			Image i2=i.getScaledInstance(50	, 50, Image.SCALE_SMOOTH);
			l.setIcon(new ImageIcon(i2));
			l.setFont(Constants.FONT);
			l.setForeground(Color.gray);
			l.setText("0");
			materials.put(id, l);
			return l;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	public void addQuantity(int id,int increase) {
		materials.get(id).increaseQuantity(increase);
		
	}
	public void quitQuantity(int id,int decrease) {
		materials.get(id).decreaseQuantity(decrease);
		
	}
	public boolean hasEnoughQuantity(int id,int requiredQuantity) {
		return materials.get(id).hasEnoughQuantity(requiredQuantity);
		
	}
	public int getRemainQuantity(int id,int decreaseQuantity) {
		return materials.get(id).getRemainQuantity(decreaseQuantity);
		
	}
	public HashMap<Integer, QuantityItem> getMaterials() {
		return materials;
	}
	public void setMaterials(HashMap<Integer, QuantityItem> materials) {
		this.materials = materials;
	}
	
}

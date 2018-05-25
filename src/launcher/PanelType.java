package launcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants.Constants;

public class PanelType extends JPanel{
	GridBagConstraints gbc;

	public PanelType(String name) {
		
		this.setLayout(new GridBagLayout());
		this.setBorder(new TitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE), name,TitledBorder.LEFT,TitledBorder.TOP,Constants.FONT,Color.WHITE));
		this.setOpaque(false);
		gbc= new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	}
	public void addComponent(JPanel p) {
		this.add(p, gbc);
		
		if ((float) gbc.gridy % 4f == 0 && gbc.gridy != 0) {

			gbc.gridy = 0;
			gbc.gridx++;
		} else {
			gbc.gridy++;

		}

	}
	
}

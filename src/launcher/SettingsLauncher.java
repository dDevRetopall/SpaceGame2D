package launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

import constants.Constants;
import juegoEspacio.GameHandler;
import juegoEspacio.Main;

public class SettingsLauncher extends JFrame {
	ArrayList<JPanel> paneles = new ArrayList<>();
	JPanel secundaryPanel = new JPanel();
	JPanel southPanel = new JPanel(new FlowLayout());
	JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel mainPanel;
	GridBagConstraints gbc;
	PanelType checks = new PanelType("");
	PanelType numbers = new PanelType("Game settings");
	PanelType paths = new PanelType("Path");
	PanelType spinners = new PanelType("Video and sound");
	int type = 0;
	JButton updateValues = new JButton("Update");
	JButton resetValues = new JButton("Reset");
	JButton start = new JButton("Start game");
	Image  bgImage;
	public SettingsLauncher() {

		try {
			bgImage = ImageIO.read(new File(Constants.imagesPath+"space.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainPanel = new JPanel() {
				@Override
				  protected void paintComponent(Graphics g) {

				    super.paintComponent(g);
				   
				        g.drawImage(bgImage, 0, 0,this.getWidth(),this.getHeight(), null);
				}
			};
		mainPanel.setOpaque(false);
		checks.setOpaque(false);
		northPanel.setOpaque(false);
		southPanel.setOpaque(false);
		secundaryPanel.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("Settings");
		mainPanel.setLayout(new BorderLayout());
		secundaryPanel.setLayout(new GridBagLayout());

		createNewSetting(0, "ResourcesFile");
		createNewSetting(0, "DataFile");
		createNewSetting(0, "WeaponsFile");
		createNewSetting(1, "SizeBlocks");
		createNewSetting(2, "Graphical effects");
		createNewSetting(2, "Graphics");
		createNewSetting(2, "Sound Effects");
		createNewSetting(3, "Debug");
		createNewSetting(3, "Debug File");
		createNewSetting(1, "Time of progressBars");
		createNewSetting(1, "GameMode");
		createNewSetting(1, "Duration of Warnings");
		createNewSetting(1, "Probability of items");
		createNewSetting(1, "Build Ratio");
		createNewSetting(1, "Build time");
		createNewSetting(1, "MaxDiameter build");
		createNewSetting(1, "MinDiameter build");
		createNewSetting(1, "BombRatio");
		createNewSetting(0, "ImagesPath");
		createNewSetting(0, "Map file");
		createNewSetting(3, "ShowInfo");
		createNewSetting(3, "Use file item size");
		createNewSetting(3, "Scale objects from size");

		this.setContentPane(mainPanel);
		mainPanel.add(secundaryPanel, BorderLayout.CENTER);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		secundaryPanel.add(paths);

		secundaryPanel.add(numbers);
		northPanel.add(checks);

		secundaryPanel.add(spinners);
		start.setOpaque(false);
		resetValues.setOpaque(false);
		updateValues.setOpaque(false);
		resetValues.setFocusable(false);
		southPanel.add(resetValues);
		updateValues.setFocusable(false);
		southPanel.add(updateValues);
		start.setFocusable(false);
		southPanel.add(start);
	
		initialize();
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsLauncher.this.dispose();
				GameHandler.startGame();

			}
		});
		resetValues.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialize();
				start.setEnabled(true);

			}
		});
		updateValues.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Constants.resources=((JTextField) paneles.get(0).getComponent(1)).getText();
				Constants.data=((JTextField) paneles.get(1).getComponent(1)).getText();
				Constants.weapons=((JTextField) paneles.get(2).getComponent(1)).getText();
				Constants.sizeBlocks=Integer.parseInt( (((JTextField) paneles.get(3).getComponent(1)).getText()));
				Constants.levelEffects=((JSlider) paneles.get(4).getComponent(1)).getValue();
				Constants.levelGraphical=((JSlider) paneles.get(5).getComponent(1)).getValue();
				((JSlider) paneles.get(6).getComponent(1)).getValue();
				Constants.DEBUG=((JCheckBox) paneles.get(7).getComponent(1)).isSelected();
				Constants.DEBUGFiles=((JCheckBox) paneles.get(8).getComponent(1)).isSelected();
				Constants.timeToDisappearProgressBarLadrillos=Integer.parseInt(((JTextField) paneles.get(9).getComponent(1)).getText());
				Constants.setGamemode(Integer.parseInt(((JTextField) paneles.get(10).getComponent(1)).getText()));
				Constants.durationOfWarnings=Integer.parseInt(((JTextField) paneles.get(11).getComponent(1)).getText());
				Constants.probabilityOfItems=Float.parseFloat(( ((JTextField) paneles.get(12).getComponent(1)).getText()));
				Constants.ratio=Float.parseFloat(((JTextField) paneles.get(13).getComponent(1)).getText());
				Constants.buildTime=Float.parseFloat( ((JTextField) paneles.get(14).getComponent(1)).getText());
				Constants.maxDiameter=Float.parseFloat(((JTextField) paneles.get(15).getComponent(1)).getText());
				Constants.minDiameter=Float.parseFloat( ((JTextField) paneles.get(16).getComponent(1)).getText());
				Constants.radioBomba=Float.parseFloat(((JTextField) paneles.get(17).getComponent(1)).getText());
				Constants.imagesPath=((JTextField) paneles.get(18).getComponent(1)).getText();
				Constants.file=((JTextField) paneles.get(19).getComponent(1)).getText();
				Constants.showInfo=((JCheckBox) paneles.get(20).getComponent(1)).isSelected();
				Constants.useFileItemsSize=((JCheckBox) paneles.get(21).getComponent(1)).isSelected();
				Constants.scaleAllFromSize=((JCheckBox) paneles.get(22).getComponent(1)).isSelected();
				start.setEnabled(true);
				}catch(NumberFormatException ee) {
					System.out.println("Error for input");
					start.setEnabled(false);
					System.out.println(ee.getMessage());
					JOptionPane.showMessageDialog(null, "Error in input setting.\n"+ee.getMessage());
					
				}
			}
		});
	}

	public void initialize() {

		((JTextField) paneles.get(0).getComponent(1)).setText(Constants.resources);
		((JTextField) paneles.get(1).getComponent(1)).setText(Constants.data);
		((JTextField) paneles.get(2).getComponent(1)).setText(Constants.weapons);
		((JTextField) paneles.get(3).getComponent(1)).setText(Integer.toString((Constants.sizeBlocks)));
		((JSlider) paneles.get(4).getComponent(1)).setValue(Constants.levelEffects);
		((JSlider) paneles.get(5).getComponent(1)).setValue(Constants.levelGraphical);
		((JSlider) paneles.get(6).getComponent(1)).setValue(1);
		((JCheckBox) paneles.get(7).getComponent(1)).setSelected(Constants.DEBUG);
		((JCheckBox) paneles.get(8).getComponent(1)).setSelected(Constants.DEBUGFiles);
		((JTextField) paneles.get(9).getComponent(1)).setText(Integer.toString(Constants.timeToDisappearProgressBarLadrillos));
		((JTextField) paneles.get(10).getComponent(1)).setText(Integer.toString(Constants.getGamemode()));
		((JTextField) paneles.get(11).getComponent(1)).setText(Integer.toString(Constants.durationOfWarnings));
		((JTextField) paneles.get(12).getComponent(1)).setText(Float.toString(Constants.probabilityOfItems));
		((JTextField) paneles.get(13).getComponent(1)).setText(Float.toString(Constants.ratio));
		((JTextField) paneles.get(14).getComponent(1)).setText(Float.toString(Constants.buildTime));
		((JTextField) paneles.get(15).getComponent(1)).setText(Float.toString(Constants.maxDiameter));
		((JTextField) paneles.get(16).getComponent(1)).setText(Float.toString(Constants.minDiameter));
		((JTextField) paneles.get(17).getComponent(1)).setText(Float.toString(Constants.radioBomba));
		((JTextField) paneles.get(18).getComponent(1)).setText(Constants.imagesPath);
		((JTextField) paneles.get(19).getComponent(1)).setText(Constants.file);
		((JCheckBox) paneles.get(20).getComponent(1)).setSelected(Constants.showInfo);
		((JCheckBox) paneles.get(21).getComponent(1)).setSelected(Constants.useFileItemsSize);
		((JCheckBox) paneles.get(22).getComponent(1)).setSelected(Constants.scaleAllFromSize);

	}
	
	public void createNewSetting(int type, String name) {

		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l = new JLabel(name);
		l.setForeground(Color.white);
		l.setFont(Constants.FONT.deriveFont(12f));
		p.add(l);
		if (type == 0) {
			JTextField tf = new JTextField(10);
			tf.setOpaque(false);
			tf.setForeground(Color.white);
			
			p.add(tf);
			paths.addComponent(p);
		} else if (type == 3) {
			JCheckBox check = new JCheckBox();
			check.setOpaque(false);
			check.setFocusable(false);
			p.add(check);
			checks.addComponent(p);
		} else if (type == 1) {
			JTextField tf = new JTextField(10);
			tf.setOpaque(false);
			tf.setForeground(Color.white);
			
			p.add(tf);
			
			numbers.addComponent(p);
		} else if (type == 2) {
			JSlider slider = new JSlider();
			slider.setPaintTicks(true);
			slider.setPaintTrack(true);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(1);
			slider.setMinimum(0);
			slider.setMaximum(5);
			slider.setOpaque(false);
			slider.setForeground(Color.white);
			slider.setFocusable(false);
			p.add(slider);
			spinners.addComponent(p);
		}
		paneles.add(p);

	}
}

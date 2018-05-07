package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import constants.Constants;
import juegoEspacio.Main;

public class HealthPanel extends JPanel {
	ProgressBar pb;
	int width = 100;
	int height = 25;
	long initialTime = 0;
	boolean counting = false;
	int suplement=0;
	public HealthPanel(int x, int y, boolean startingIndeterminate) {
		this.setBounds(x - width / 2, y - 50, width, height);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		pb = new ProgressBar(100, 60, 10);
		this.add(pb);
		pb.setIndeterminate(startingIndeterminate);

	}
	public HealthPanel(int x, int y, int initialValue, int width, int height) {
		this.setBounds(x , y, width, height);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		pb = new ProgressBar(100, 60, 10);
		this.add(pb);
		pb.setValue(initialValue);
		
		

	}

	public HealthPanel(int x, int y, boolean startingIndeterminate, int width, int height) {
		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		pb = new ProgressBar(100, 60, 10);
		this.add(pb);
		pb.setIndeterminate(startingIndeterminate);

	}

	public void startCountToBuild() {
		initialTime = System.currentTimeMillis();
		counting=true;
	
	}

	public ProgressBar getProgressBar() {
		return pb;
	}

	public void editLocationLadrillo(int x, int y) {
		this.setLocation(x, y);

		this.updateUI();
		if(counting) {
			if((System.currentTimeMillis()-initialTime)>=Constants.buildTime) {
				counting=false;
			}else {
			int s=(int)((((float)(System.currentTimeMillis()-initialTime-suplement))/(float)Constants.buildTime)*100) ;
			
			getProgressBar().setValue(s);
			
			}
			
		}
		
	}

	public void editLocationBomba(int x, int y) {
		this.setLocation(x - width / 2, y - 50);
		this.updateUI();
	}

	public void setProgressIndeterminate(boolean option) {
		pb.setIndeterminate(option);
	}

	public void quitHealth(int health) {
		pb.setValue(pb.getValue() - health);
		if(counting) {
			float f=(Constants.buildTime*health/100f);
		
			suplement+=f;
		}
	}

	public void addHealth(int health) {
		pb.setValue(pb.getValue() + health);
	}

	public int getHealth() {
		return pb.getValue();
	}
	public boolean isCounting() {
		return counting;
	}
	public void setCounting(boolean counting) {
		this.counting = counting;
	}
	

}

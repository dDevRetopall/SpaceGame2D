package graphics.shooting;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import gui.ProgressBar;
import juegoEspacio.Status;
import juegoEspacio.Ventana;

public abstract class Recharger {
	boolean continuing = true;
	int cooldown;
	Timer timer;
	double percentage = 0;
	long initialTime = 0;
	Thread t;
	boolean stop;
	float i=0;
	public Recharger(int cooldown, ProgressBar pb) {
		this.cooldown = cooldown;
		timer = new Timer();
		pb.setString("Ready");
		pb.setBackground(new Color(0,153,0));
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (continuing) {
			
					if (!stop) {
						Status.paneles.get(pb).setForeground(Color.white);
						pb.setBorder(new MatteBorder(2,2,2,2,new Color(0,0,153)));
						long tiempo = System.currentTimeMillis() - initialTime;
						long t = cooldown - tiempo;
						double r = (t * 100) / cooldown;
						pb.setValue((int) r);
						float seconds = (((int) (t / 100)) / 10f);
						pb.setString(seconds + "s");
					
						if ((tiempo >= cooldown)) {
							stop();
							function();
							pb.setString("Ready");

						}
					}else {
						double radian = 0.017453292519943295;
					    int color = (int)((Math.cos((double)i * radian) + 1.0) * 255.0 / 2.0);
					    Color c =new Color(color,0,(int)(255-color/2));
						Status.paneles.get(pb).setForeground(c);
						pb.setBorder(new MatteBorder(2,2,2,2,c));
						i+=0.2f;
						
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}

			}
		});
		

	}

	public void stop() {
		stop = true;

	}

	public void restart() {
		if (t.isAlive()) {
			
			initialTime=System.currentTimeMillis();
			stop=false;
		} else {
			t.start();
			initialTime = System.currentTimeMillis();
		}
	}

	public void start() {
  
		t.start();
		
	}

	public abstract void function();

}

package gui;

import javax.swing.JProgressBar;

public class ThreadProgress implements Runnable{
	boolean continuing=true;
	boolean suspended=false;
	int valueToReach=100;
	int countingValue=0;
	private JProgressBar pb;
	public ThreadProgress(JProgressBar pb) {
		this.pb = pb;
		valueToReach=pb.getValue();
		countingValue=0;
	}
	public void newAnimation(int value) {
		valueToReach=value;
		this.resume();
	}
	@Override
	public void run() {
		while(continuing) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(countingValue>valueToReach) {
			ThreadProgress.this.pb.setValue(countingValue--);
			}else if(countingValue<valueToReach){
			ThreadProgress.this.pb.setValue(countingValue++);
			}else {/* ===*/
			ThreadProgress.this.stop();
			}
			synchronized (this) {
				while(suspended) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	public void resume() {
		suspended=false;
		notify();
	}
	public void stop() {
		suspended=true;
	}
	public void finalize() {
		continuing=false;
	}
	public void start() {
		this.start();
	}

}

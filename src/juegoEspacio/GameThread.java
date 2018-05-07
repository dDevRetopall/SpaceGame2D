package juegoEspacio;

public class GameThread implements Runnable {
	public boolean running = true;
	private PanelDibujo panel;

	public GameThread(PanelDibujo panel) {
		this.panel = panel;
		
	}

	@Override
	public void run() {
		while (running) {
			synchronized (this) {
				if (Main.getVentana() != null) {
					if (Main.getVentana().getPanelActual() != null) {
						
						panel.repaint();
						
					
					}
				}
			}
			
		}

	}

}

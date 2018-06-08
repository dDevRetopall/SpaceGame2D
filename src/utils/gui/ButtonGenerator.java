package utils.gui;

import javax.swing.JButton;

import gui.Button;
import ia.IAGenerator;
import juegoEspacio.GameHandler;
import juegoEspacio.Ventana;

public class ButtonGenerator {
	static Button b, b1, b2, b3, b4;

	public static Button[] createButtons() {

		b = new Button(50, Ventana.screenSize.height - 100, "assets/images/restart.png", "New try") {

			private static final long serialVersionUID = -3242900535778031444L;

			public void doAction() {
				GameHandler.resetGame();
			}
		};

		b1 = new Button(250, Ventana.screenSize.height - 100, "assets/images/restart.png", "Change") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void doAction() {
				GameHandler.resetGame();
			}
		};
		b2 = new Button(450, Ventana.screenSize.height - 100, "assets/images/restart.png", "Restart") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void doAction() {
				
				GameHandler.resetGame();
				GameHandler.stopGame();
				
				GameHandler.startGame();

			}
		};
		b3 = new Button(1400, Ventana.screenSize.height - 100, "assets/images/restart.png", "Main menu") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void doAction() {
				IAGenerator.characters.remove(IAGenerator.mainPlayer);
				GameHandler.resetGame();
				GameHandler.stopGame();
			}
		};
		b4 = new Button(1600, Ventana.screenSize.height - 100, "assets/images/restart.png", "Exit game") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void doAction() {
				System.exit(0);
			}
		};
		Button[] buttons = { b, b1, b2, b3, b4 };
		return buttons;
	}
}

package juegoEspacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constants.Constants;
import graphics.builds.BuildFunctions;
import graphics.builds.LadrilloConColision;
import gui.Button;
import gui.PanelLife;
import ia.Engine;
import ia.IAGenerator;
import items.ImageItem;
import items.Item;

public class Ventana extends JFrame {
	public PanelDibujo panelActual;
	public static Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static int centroXPantalla;
	public static int centroYPantalla;
	public boolean acumulador = false;
	public static boolean disparoNormalHabilitado = true;
	public static boolean disparoEspecialHabilitado = true;
	public static boolean shieldHabilitado = true;
	public static boolean bombaHabilitada = true;
	public static boolean building = false;
	public boolean gameScreenEnabled = false;

	public Ventana() {

		centroXPantalla = screenSize.width/2;
		centroYPantalla = screenSize.height / 2;

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		panelActual = new PanelDibujo(Color.black);

		this.add(panelActual);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {

			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(new File(Constants.imagesPath+"cursor.png")),
					new Point(0, 0), "cursor"));
		} catch (HeadlessException | IndexOutOfBoundsException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (gameScreenEnabled) {
					if (disparoNormalHabilitado) {
						if (acumulador) {
							
							IAGenerator.mainPlayer.getCañon().disparar();
							acumulador = false;

							IAGenerator.mainPlayer.getNormalShootRecharger().restart();
							disparoNormalHabilitado = false;

						}
					}
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (gameScreenEnabled) {
					if (!building) {
						acumulador = true;
					} else {
						building = true;
						BuildFunctions.build(e.getX(), e.getY());
					}
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (gameScreenEnabled) {

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (gameScreenEnabled) {

				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameScreenEnabled) {

				}
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (gameScreenEnabled) {
					getPanelActual().setxMouse(e.getX());
					getPanelActual().setyMouse(e.getY());
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (gameScreenEnabled) {

				}

			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (gameScreenEnabled) {

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (gameScreenEnabled) {
					if (e.getKeyCode() == KeyEvent.VK_W) {

						IAGenerator.mainPlayer.arriba = false;
					}
					if (e.getKeyCode() == KeyEvent.VK_A) {
						IAGenerator.mainPlayer.izquierda = false;

					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						IAGenerator.mainPlayer.abajo = false;

					}
					if (e.getKeyCode() == KeyEvent.VK_D) {
						IAGenerator.mainPlayer.derecha = false;

					}
					if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
						IAGenerator.mainPlayer.frenando = false;
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (gameScreenEnabled) {

					if (e.getKeyCode() == KeyEvent.VK_W) {
						IAGenerator.mainPlayer.arriba = true;
					}

					if (e.getKeyCode() == KeyEvent.VK_A) {
						IAGenerator.mainPlayer.izquierda = true;

					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						IAGenerator.mainPlayer.abajo = true;

					}
					if (e.getKeyCode() == KeyEvent.VK_D) {
						IAGenerator.mainPlayer.derecha = true;

					}
					if (!IAGenerator.mainPlayer.muerto && !IAGenerator.mainPlayer.completed) {
						if (e.getKeyCode() == KeyEvent.VK_SPACE) {

							if (disparoEspecialHabilitado) {
								IAGenerator.mainPlayer.disparar = true;
								IAGenerator.mainPlayer.getSpecialShootRecharger().restart();
								disparoEspecialHabilitado = false;
							} else {
								getPanelActual().changeMessage("Espera a que el ataque especial este habilitado");
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
							IAGenerator.mainPlayer.resetSpeed();
						}
						if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
							IAGenerator.mainPlayer.frenando = true;
						}
						if (e.getKeyCode() == KeyEvent.VK_B) {
							if (bombaHabilitada) {
								IAGenerator.mainPlayer.getCañon().ponerBomba();
								IAGenerator.mainPlayer.getBombRecharger().restart();
								bombaHabilitada = false;
							} else {
								getPanelActual().changeMessage("La bomba esta cargandose");
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							System.exit(0);
							// if(JOptionPane.showConfirmDialog(Ventana.this, "Are you sure you want to exit
							// the game?")==0) {
							// System.exit(0);
							// }
						}

						if (e.getKeyCode() == KeyEvent.VK_1) {
							if (Main.getVentana().getPanelActual().getItemStore().isSelected(0)) {
								falseAllSelections();
							} else {
								changeSelection(0);
							}
							checkSettingUseMessage(0);
						}
						if (e.getKeyCode() == KeyEvent.VK_2) {
							if (Main.getVentana().getPanelActual().getItemStore().isSelected(1)) {
								falseAllSelections();
							} else {
								changeSelection(1);
							}
							checkSettingUseMessage(1);
						}
						if (e.getKeyCode() == KeyEvent.VK_3) {
							if (Main.getVentana().getPanelActual().getItemStore().isSelected(2)) {
								falseAllSelections();
							} else {
								changeSelection(2);
							}
							checkSettingUseMessage(2);
						}
						if (e.getKeyCode() == KeyEvent.VK_4) {
							if (Main.getVentana().getPanelActual().getItemStore().isSelected(3)) {
								falseAllSelections();
							} else {
								changeSelection(3);
							}
							checkSettingUseMessage(3);
						}
						if (e.getKeyCode() == KeyEvent.VK_5) {
							if (Main.getVentana().getPanelActual().getItemStore().isSelected(4)) {
								falseAllSelections();
							} else {
								changeSelection(4);
							}

							checkSettingUseMessage(4);
						}
						if (e.getKeyCode() == KeyEvent.VK_E) {
							Main.getVentana().getPanelActual().getItemsHandler().takeItem();

						}
						if (e.getKeyCode() == KeyEvent.VK_Q) {

							if (building) {
								Main.getVentana().getPanelActual().remove(Main.getVentana().getPanelActual().bp);
								building = false;
							} else {
								Main.getVentana().getPanelActual().add(Main.getVentana().getPanelActual().bp);
								building = true;
							}
							Main.getVentana().getPanelActual().updateUI();

						}
						if (e.getKeyCode() == KeyEvent.VK_R) {
							if (building) {
								LadrilloConColision lc = BuildFunctions.getLadrilloEnMouse();

								if (lc != null && lc.isBuilding()) {
									lc.getHp().quitHealth(100);

									Engine.updateLadrillo(lc);
								}
							} else {
								int pos = Main.getVentana().getPanelActual().getItemStore().getPosSelected();
								if (pos != -1) {
									if (Main.getVentana().getPanelActual().getItemStore().isthereItemInProgress()) {
										Main.getVentana().getPanelActual()
												.changeMessage("There is already an item in progress");
									} else if (!Main.getVentana().getPanelActual().getItemStore().isEmpty(pos)) {
										Main.getVentana().getPanelActual().getItemStore().getSlot(pos).starCount();
										;
									} else {
										Main.getVentana().getPanelActual().changeMessage("The slot is empty");
									}

								} else {
									Main.getVentana().getPanelActual().changeMessage("Any slot is selected");
								}
							}

						}
						if (e.getKeyCode() == KeyEvent.VK_F) {
							Main.getVentana().getPanelActual().getItemStore().leaveItemAndReset();
						}
					}
				}
			}
		});
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (gameScreenEnabled) {
					int s = e.getWheelRotation();
					int newPos;
					if (s > 0) {

						newPos = Main.getVentana().getPanelActual().getItemStore().getPosSelected() + 1;
						if (newPos > 4) {
							newPos = 0;
						}

					} else {
						newPos = Main.getVentana().getPanelActual().getItemStore().getPosSelected() - 1;
						if (newPos < 0) {
							newPos = 4;
						}
					}

					if (Main.getVentana().getPanelActual().getItemStore().isSelected(newPos)) {
						falseAllSelections();
					} else {
						changeSelection(newPos);
					}
					checkSettingUseMessage(newPos);
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (gameScreenEnabled) {
					IAGenerator.mainPlayer.getCañon().setxMouse(e.getX());
					;
					IAGenerator.mainPlayer.getCañon().setyMouse(e.getY());
					;
				}

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (gameScreenEnabled) {

				}

			}
		});

	}

	public void checkSettingUseMessage(int pos) {
		if (!Main.getVentana().getPanelActual().getItemStore().isEmpty(pos)
				&& !Main.getVentana().getPanelActual().getItemStore().allUnselected()) {
			Main.getVentana().getPanelActual().getItemStore().putMessageToUse(true);
		} else {
			Main.getVentana().getPanelActual().getItemStore().putMessageToUse(false);
		}

	}

	public void changeSelection(int posSelected) {
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 0);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 1);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 2);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 3);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 4);
		Main.getVentana().getPanelActual().getItemStore().setSelected(true, posSelected);

	}

	public void falseAllSelections() {
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 0);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 1);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 2);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 3);
		Main.getVentana().getPanelActual().getItemStore().setSelected(false, 4);

	}

	public PanelDibujo getPanelActual() {
		return panelActual;
	}

	public void resetRechargers() {
		this.bombaHabilitada = true;
		this.disparoEspecialHabilitado = true;
		this.disparoNormalHabilitado = true;

	}

}

	package juegoEspacio;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import enemigos.Enemigo;
import enemigos.EnemigoEspecial;
import graphics.builds.BuildFunctions;
import graphics.builds.Ladrillo;
import graphics.builds.LadrilloConColision;
import graphics.builds.SalidaKey;
import graphics.fireworks.FuegoArtificial;
import graphics.sky.Estrella;
import gui.BuildPanel;
import gui.Button;
import gui.HealthPanel;
import gui.InfoBombs;
import gui.PanelBomb;
import gui.PanelLife;
import gui.ProgressBar;
import ia.ArmedPlayer;
import ia.Engine;
import ia.IAGenerator;
import ia.Player;
import items.Item;
import items.ItemsHandler;
import items.ItemsStore;
import map.Map;
import mapGenerator.ColorUtils;
import mapGenerator.GeneratorTools;
import mapGenerator.Vec2;
import turret.Turret;
import utils.imageUtils.ImageUtils;
import utils.imageUtils.Pixel;
import vector.OperacionesVectores;
import vector.Vector;

public class PanelDibujo extends JPanel {
	ArrayList<HealthPanel> paneles = new ArrayList<>();
	int fps = 0;
	int inicialTime = (int) System.currentTimeMillis();
	int contadorSegundos = 1;
	int fpsDefinitivos = 0;
	private ArrayList<Estrella> estrellas = new ArrayList();
	int numEstrellas = 2000;
	public ArrayList<LadrilloConColision> ladrillos = new ArrayList<>();
	public ArrayList<LadrilloConColision> ladrillosToRemove = new ArrayList<>();
	public ArrayList<Vec2> ladrillosMapa = new ArrayList<>();
	public ArrayList<LadrilloConColision> ladrillosIniciales = new ArrayList<>();
	public ArrayList<Enemigo> enemigos = new ArrayList<>();
	public HashMap<Enemigo, Vec2> enemigosMapa = new HashMap<>();
	Button b;
	SalidaKey key;
	PanelLife pf;
	boolean restarting = false;
	private String mensaje = "";
	long changeMessageTime = 0;
	ItemsHandler ih = new ItemsHandler();
	InfoBombs ib;
	ItemsStore is;
	BuildPanel bp;
	int xMouse = 0;
	int yMouse = 0;
	long totalObjectsRendered = 0;
	Image i, i2;
	int percentage = 0;
	Map map;
	String statusMessage = "Starting";
Turret t = new Turret(500,500,500);
	public PanelDibujo(Color c) {
	
		t.setup();
		this.setLayout(null);
		this.setBackground(c);
		ib = new InfoBombs();
		pf = new PanelLife();
		bp = new BuildPanel();
		is = new ItemsStore();
		b = new Button(200, Ventana.screenSize.height - 450);
		//temporal
		this.add(bp);
		try {
			i = ImageIO.read(new File("assets/images/wallpaper.jpg"));
			i2 = new ImageIcon("assets/animations/loading.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t = new Thread(new GameThread(this));
		t.start();

	}

	public void init() {
		Status.initStatus();

		this.add(ib);
		this.add(pf);
		this.add(b);
		this.add(Status.pStatus);

		map = new Map();
		this.updateUI();

	}

	public void paint(Graphics g) {
		
		super.paint(g);
		
		if (GameHandler.getVentana().gameScreenEnabled) {
			render(g);
		} else {

			g.drawImage(i, 0, 0, null);
			g.setFont(Constants.FONT.deriveFont(40f).deriveFont(Font.PLAIN));

			g.setColor(Color.WHITE);
			g.drawString("Space game", 50, 50);
			g.drawString("Loading...  " + percentage + "%", 100, 1000);
			g.setFont(Constants.FONT);
			g.drawString(statusMessage, 100, 1020);
			g.drawImage(i2, 400, 960, this);

		}

	}

	public void render(Graphics g) {
		t.render(g);
		if (Constants.DEBUG) {
			System.out.println("Total components in panel dibujo: " + this.getComponents().length);
		}

		renderStars(g);
		
	

		IAGenerator.updateCharacters(g);

		if (key != null) {
			key.renderSalida(g, -IAGenerator.mainPlayer.getX(), -IAGenerator.mainPlayer.getY());
		}

		if (GameHandler.getVentana().building) {

			BuildFunctions.showPrebuild(g);
		}

		if (!restarting) {
			Engine.renderEnemies(g);
			Engine.renderWalls(g);
			Engine.renderFirework(g);
		}

		pintarMenu(g);

		refreshFps(g);

		ih.renderItems(g);
		is.render(g);
		if (GameHandler.getVentana().building) {
			showBuildMessage("Press Q to deactivate building mode - BETA", g);
			showMode("Building mode", g);
		} else {
			showBuildMessage("Press Q to activate building mode - BETA", g);
			showMode("Attack mode", g);
		}

		if (Constants.showInfo) {
			showInformation(g);
		}
		
		if (IAGenerator.mainPlayer.muerto) {
			IAGenerator.mainPlayer.getCañon().resetBombas();
			IAGenerator.mainPlayer.putMenu(g, "Has muerto");
			b.setVisible(true);

		} else {
			b.setVisible(false);
			GameHandler.getVentana().requestFocus();
		}
		if (IAGenerator.mainPlayer.completed) {
			IAGenerator.mainPlayer.putMenu(g, "Has pasado el nivel. Felicidades");
			b.setVisible(true);

		} else if (!IAGenerator.mainPlayer.muerto) {

			b.setVisible(false);
			GameHandler.getVentana().requestFocus();
		} else {
			GameHandler.getVentana().building = false;
			GameHandler.getVentana().requestFocus();
		}
		if (System.currentTimeMillis() - changeMessageTime <= Constants.durationOfWarnings) {
			triggerMessage(g, mensaje);
		}
		super.paintChildren(g);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetElements() {
		restarting = true;
		GameHandler.getVentana().getPanelActual().getStars().removeAll(GameHandler.getVentana().getPanelActual().getStars());
		Engine.finishAnimation();
		////// si en restart se guarda lo anterior destruido
		//resetLadrillos();
		////// si en restart se guarda lo anterior destruido***
		Engine.explosion = null;
		Engine.fuegosArtificiales.removeAll(Engine.fuegosArtificiales);
		enemigos.removeAll(enemigos);
		Engine.fuegosArtificiales.removeAll(Engine.fuegosArtificiales);
		IAGenerator.mainPlayer.getMotors().resetProgressMotors();
		GameHandler.getVentana().resetRechargers();
		IAGenerator.mainPlayer.getCañon().resetBombas();
		getInfoBombsPanel().getPaneles().removeAll(getInfoBombsPanel().getPaneles());
		getInfoBombsPanel().removeAll();

		is = new ItemsStore();
		restarting = false;

	}

	public void resetLadrillos() {
		for (Ladrillo l : ladrillos) {
			this.remove(l.getHp());
			this.updateUI();
		}

		ladrillos.removeAll(ladrillos);
		ladrillosMapa.removeAll(ladrillosMapa);
		for (LadrilloConColision lc : ladrillosIniciales) {
			ladrillos.add(lc);
			ladrillosMapa.add(lc.getInitialVector());
		}

	}

	public void guardarPixelesEnArray() {
		statusMessage = "Reading map";
		boolean unicaSalida = false;
		System.out.println("Leyendo mapa");
		ArrayList<Vec2> colors = GeneratorTools.getTech(Constants.file);
		System.out.println("Instalando mapa");
		for (int i = 0; i < colors.size(); i++) {
			percentage = (int) ((i * 0.75f * 100) / (float) colors.size());
			Vec2 vec = colors.get(i);
			float dx = 0;
			float dy = 0;

			if (vec.c.getGreen() == 255 && vec.c.getBlue() == 0 && vec.c.getRed() == 0) {
				Color color = ColorUtils.createRandomColor();
				color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128);
				Enemigo e = new Enemigo(vec.x * Constants.sizeBlocks + dx, vec.y * Constants.sizeBlocks + dy, color);
				this.enemigos.add(e);
				this.enemigosMapa.put(e, vec);

				continue;
			} else if (vec.c.getGreen() == 255 && vec.c.getBlue() == 255 && vec.c.getRed() == 0) {
				enemigos.Enemigo e = new EnemigoEspecial(vec.x * Constants.sizeBlocks + dx,
						vec.y * Constants.sizeBlocks + dy, ColorUtils.createRandomColor());
				this.enemigos.add(e);
				this.enemigosMapa.put(e, vec);
				continue;
			} else if (vec.c.getGreen() == 0 && vec.c.getBlue() == 0 && vec.c.getRed() == 255) {
				if (!unicaSalida) {
					key = new SalidaKey(vec.x * Constants.sizeBlocks + dx, vec.y * Constants.sizeBlocks + dy, vec.c,
							vec);
					unicaSalida = true;
					Vec2 v = new Vec2(vec.x, vec.y, vec.c, true);
					ladrillos.add(key);
					ladrillosMapa.add(v);
					System.out.println("Salida detectada y aplicada");
				} else {
					System.out.println("Error temporal. Más de una salida. Salida no incluida");
				}
			} else if ((vec.c.getGreen() == 0 && vec.c.getBlue() == 0 && vec.c.getRed() == 0)
					|| (vec.c.getGreen() == 255 && vec.c.getBlue() == 255 && vec.c.getRed() == 255)) {
				float h = (float) (Math.random() );
				if (h < Constants.probabilityOfItems) {
					ih.addNewItem(dx + vec.x * Constants.sizeBlocks, dy + vec.y * Constants.sizeBlocks);
				}
			} else {

				if (Constants.DEBUG) {
					System.out.println("X: " + vec.x + " Y: " + vec.y + " Operacion en X: " + dx
							+ vec.x * Constants.sizeBlocks + " Operacion en Y: " + dy + vec.y * Constants.sizeBlocks);
				}
				LadrilloConColision lc = new LadrilloConColision((dx + vec.x * Constants.sizeBlocks),
						dy + vec.y * Constants.sizeBlocks, vec.c, vec);
				ladrillosIniciales.add(lc);
				ladrillos.add(lc);
				ladrillosMapa.add(vec);
			}
		}

	}

	public void inicializarPartida() {
		// items.addNewItem(vec.x* Constants.sizeBlocks+dx, vec.y*
		// Constants.sizeBlocks+dy);
		statusMessage = "Generando estrellas y jugador";
		System.out.println("Generando estrellas");
		for (int i = 0; i < numEstrellas; ++i) {
			this.estrellas.add(new Estrella(Math.random() * (double) Ventana.screenSize.width,
					Math.random() * (double) Ventana.screenSize.height, Color.WHITE));

		}
		percentage = 100;
		IAGenerator.createMainCharacter(0, 0, 50, 50, Player.SPHERE, Color.WHITE);

		statusMessage = "Completed";

	}

	public void changeMessage(String mensaje) {
		this.mensaje = mensaje;
		changeMessageTime = System.currentTimeMillis();
	}

	public void triggerMessage(Graphics g, String mensaje) {
		g.setFont(Constants.FONT);
		g.setColor(Constants.colorBomb);
		g.drawString(mensaje, 9 * GameHandler.getVentana().screenSize.width / 16, 100);
	}

	public void pintarMenu(Graphics g) {

		// g.setColor(Color.DARK_GRAY);
		// g.fillRect(0, Ventana.screenSize.height - 125, Ventana.screenSize.width,
		// 125);
		// g.setColor(Color.BLACK);

		map.pintarMapa(g);
	}

	public void showBuildMessage(String message, Graphics g) {
		g.setFont(Constants.FONT);
		g.setColor(new Color(153, 0, 0));
		g.drawString(message, GameHandler.getVentana().screenSize.width - 400, 250);
	}

	public void showMode(String mode, Graphics g) {
		g.setFont(Constants.FONT.deriveFont(22f));
		g.setColor(new Color(153, 153, 153));
		g.drawString(mode, 5 * GameHandler.getVentana().screenSize.width / 8, 50);
	}

	public void showInformation(Graphics g) {
		g.setFont(Constants.FONT.deriveFont(13f));
		g.setColor(new Color(153, 153, 153));

		g.drawString("P (" + (int) IAGenerator.mainPlayer.getX() + "," + (int) IAGenerator.mainPlayer.getY() + ")", 10,
				GameHandler.getVentana().screenSize.height - 20);
		g.drawString(
				"V (" + (int) ((IAGenerator.mainPlayer.getVx() * 100)) / 100f + ","
						+ (int) ((IAGenerator.mainPlayer.getVy() * 100)) / 100f + ")",
				10, GameHandler.getVentana().screenSize.height - 40);
		long s = ladrillos.size() + IAGenerator.characters.size() + estrellas.size()
				+ getItemsHandler().getItems().size() + ladrillosMapa.size() + enemigos.size() + enemigosMapa.size()
				+ getItemStore().getSlots().size();
		for (Player c : IAGenerator.characters) {
			if (c instanceof ArmedPlayer) {
				s += IAGenerator.mainPlayer.getCañon().getBalas().size();
				s += IAGenerator.mainPlayer.getCañon().getBombas().size();

			}
		}
		for (FuegoArtificial fa : Engine.fuegosArtificiales) {
			s += fa.particulas.size();
		}
		g.drawString("Simple objects to render: " + s, 10, GameHandler.getVentana().screenSize.height - 80);
		long d = ladrillos.size() * 6 + IAGenerator.characters.size() + estrellas.size()
				+ getItemsHandler().getItems().size() + ladrillosMapa.size() + enemigos.size() * 3 + enemigosMapa.size()
				+ getItemStore().getSlots().size();
		for (Player c : IAGenerator.characters) {
			if (c instanceof ArmedPlayer) {
				d += IAGenerator.mainPlayer.getCañon().getBalas().size();
				d += IAGenerator.mainPlayer.getCañon().getBombas().size();

			}
		}
		for (FuegoArtificial fa : Engine.fuegosArtificiales) {
			d += fa.particulas.size();
		}
		g.drawString("Complex objects to render: " + d, 10, GameHandler.getVentana().screenSize.height - 100);
		totalObjectsRendered += s;

		g.drawString("Total objects rendered: " + totalObjectsRendered, 10, GameHandler.getVentana().screenSize.height - 120);
	}

	public void refreshFps(Graphics g) {
		fps++;
		int actualTime = (int) System.currentTimeMillis();
		if ((actualTime - inicialTime) > contadorSegundos * 1000) {
			contadorSegundos++;
			fpsDefinitivos = fps;
			fps = 0;
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Space game. Made by Diego Berrocal", 400, 50);
		g.setFont(new Font("Arial", Font.PLAIN, 24));

		g.drawString(fpsDefinitivos + " FPS", 3 * Ventana.screenSize.width / 4, 50);
	}

	public void renderStars(Graphics g) {

		for (Estrella e : this.estrellas) {
			e.mover(-(IAGenerator.mainPlayer.getVx()) / 2.0f, -((IAGenerator.mainPlayer.getVy()) / 2.0f));
			e.dibujar(g);
		}
	}

	public PanelLife getPanelLife() {
		return pf;
	}

	public ArrayList<Estrella> getStars() {
		return estrellas;

	}

	public InfoBombs getInfoBombsPanel() {
		return ib;
	}

	public ItemsStore getItemStore() {
		return is;
	}

	public ItemsHandler getItemsHandler() {
		return ih;
	}

	public BuildPanel getBuildPanel() {
		return bp;
	}

	public int getxMouse() {
		return xMouse;
	}

	public int getyMouse() {
		return yMouse;
	}

	public void setxMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public void setyMouse(int yMouse) {
		this.yMouse = yMouse;
	}

}

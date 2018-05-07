package utils.imageUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.beans.MethodDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.Constants;
import juegoEspacio.Main;

public class ImageUtils {
	static int WidthFigura;
	static int HeightFigura;
	static int xFigura;
	static int yFigura;
	static ArrayList<Pixel> pixelesDetectados = new ArrayList<>();
	static ArrayList<Figura> figuras = new ArrayList<>();
	public static ArrayList<Figura> figurasFiltradas = new ArrayList<>();
	static int c = 0;;
	 static ImagePanel pp;
	static int sigMet = 0;
	static int contador = 0;
	public static javax.swing.JFrame f = new javax.swing.JFrame();
	static float checkedPixels;
	public ImageUtils() {

	}

	public BufferedImage getBufferedImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();

		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();
		return bimage;

	}

	public static byte[] ImageToByteArray(BufferedImage bi) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] imageInByte;
			imageInByte = baos.toByteArray();
			return imageInByte;
		} catch (IOException e) {
			System.out.println("Error writing the image from a byte[]image");
			e.printStackTrace();
			return null;
		}

	}

	public static Image ByteArrayToImage(byte[] imageBytes) {
		Image image;
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageBytes));
			return image;
		} catch (IOException e) {
			System.out.println("Error reading byte[] image");
			e.printStackTrace();
			return null;
		}

	}

	public static Image scale(Image i, int scaleX, int scaleY) {
		Image newimg = i.getScaledInstance(scaleX, scaleY, java.awt.Image.SCALE_SMOOTH);
		return newimg;
	}

	public static BufferedImage imageToBufferedImage(Image img) {
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;

	}

	public static BufferedImage getBeam(String path, int pos) {
		
		ImageIcon imageIcon = new ImageIcon(path);
		Image tmpImage = imageIcon.getImage();

		BufferedImage bb = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		bb.getGraphics().drawImage(tmpImage, 0, 0, null);

		tmpImage.flush();
		pp = new ImagePanel(bb.getWidth(), bb.getHeight());
		f.setTitle("Tratamiento de imagenes - Motor grafico - IA");
		f.setContentPane(pp);
		f.setSize(1800, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boolean newImage = false;
		int prevX = 0, prevY = 0;
		BufferedImage bff = null;
		int contador = 0;
		ArrayList<Pixel> pixeles = new ArrayList<>();
		ArrayList<BufferedImage> images = new ArrayList<>();

		for (int y = 0; y < bb.getHeight(); y++) {
			for (int x = 0; x < bb.getWidth(); x++) {
				int s = bb.getRGB(x, y);

				int r = (s) & 0xFF;
				int g = (s >> 8) & 0xFF;
				int b = (s >> 16) & 0xFF;
				int a = (s >> 24) & 0xFF;
				Color c = new Color(r, g, b, a);
				pixelesDetectados.add(new Pixel(x, y, Color.WHITE, 0));

				pp.paintPixel(x, y, c);
				// System.out.println("R" + c.getRed() + "G" + c.getGreen() + "B" + c.getBlue()
				// + "A" + c.getAlpha() + "X;"
				// + x + "Y:" + y);
				// System.out.println(c.getAlpha()+ "cc" + c.getTransparency());

				if (c.getAlpha() == 0) {
					pixeles.add(new Pixel(x, y, c, 0));

				} else {
					contador++;

					pixeles.add(new Pixel(x, y, c, 1));

				}

			}

		}
		// for(int i=0;i<pixeles.size();i++) {
		//
		// if((i%bb.getWidth())==0) {
		// System.out.println();
		//
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }else {
		// System.out.print(pixeles.get(i).getStatus()+" ");
		// }
		// }
		
		for (Pixel p : pixeles) {
			xFigura = p.getX();
			yFigura = p.getY();
			WidthFigura = 0;
			HeightFigura = 0;
			int poss = p.getY() * bb.getWidth() + p.getX();
			c++;
			if (p.getStatus() == 1 && pixelesDetectados.get(poss).getStatus() == 0) {
				Random random = new Random();
				float hue = random.nextFloat();
				// Saturation between 0.1 and 0.3
				float saturation = (random.nextInt(2000) + 1000) / 10000f;
				float luminance = 0.9f;
				Color color = Color.getHSBColor(hue, saturation, luminance);
				figuras.add(new Figura(xFigura, yFigura, WidthFigura, HeightFigura, true, color));

				
				// ImageUtils.metodoRecursivo(p, bb, pixeles, figuras.size() - 1);

				funcionRecursiva(p, bb, pixeles, figuras.size() - 1);
				// System.out.println("Veces"+c);
				
			}
			checkedPixels=c*100/(float)pixeles.size();

		}
		for(Figura figura:figuras) {
			if(figura.getHeight()!=0) {
			double proporcion=(figura.getWidth()/(double)figura.getHeight());
			if(proporcion>0.2d&&proporcion<4d && figura.getWidth()*figura.getHeight()>50){
				figura.setI((Image) (bb.getSubimage(figura.getX(), figura.getY(), figura.getWidth(), figura.getHeight())));
				figurasFiltradas.add(figura);
				figura.setC(Color.RED);
			}
			}
			
		}
		f.setVisible(false);
	
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);

		f.setVisible(true);
	
		System.out.println("Total real figuras: " + figurasFiltradas.size());

		System.out.println("Total figuras: " + figuras.size());
		return bb;
	}

	public static boolean yaDetectado() {
		for (Pixel p : pixelesDetectados) {
			if (p.getStatus() == 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	public static void funcionRecursiva(Pixel p, BufferedImage bb, ArrayList<Pixel> pixeles, int posFigura) {
		int pos = p.getY() * bb.getWidth() + p.getX();
		evaluatePosition(p, pixeles, pos, bb.getWidth(), bb.getHeight(), posFigura, bb);

	}

	public static void evaluatePosition(Pixel p, ArrayList<Pixel> pixeles, int pos, int width, int height,
			int posFigura, BufferedImage bb) {

		contador++;
		
		pixelesDetectados.set(pos, new Pixel(p.getX(), p.getY(), Color.WHITE, 1));
		if (Constants.seeImagetreatmentReal) {
		//	System.out.println(contador);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int posxMayor = p.getY() * width + p.getX() + 1;
		if (posxMayor <= pixeles.size()) {
			if (pixeles.get(posxMayor).getStatus() == 1 && pixelesDetectados.get(posxMayor).getStatus() == 0) {
				pixelesDetectados.set(posxMayor, new Pixel(p.getX(), p.getY(), Color.WHITE, 1));
				pp.editPixel(p.getX(), p.getY(), pos);
				if (pixeles.get(posxMayor).getX() < width) {
					if (WidthFigura < pixeles.get(posxMayor).getX() - xFigura) {
						WidthFigura = pixeles.get(posxMayor).getX() - xFigura;
						editFiguras(posFigura);
					}

					funcionRecursiva(pixeles.get(posxMayor), bb, pixeles, posFigura);
				}

			}
		}
		int posyMayor = (p.getY() + 1) * width + p.getX();
		if (posyMayor <= pixeles.size()) {
			if (pixeles.get(posyMayor).getStatus() == 1 && pixelesDetectados.get(posyMayor).getStatus() == 0) {
				pixelesDetectados.set(posyMayor, new Pixel(p.getX(), p.getY(), Color.WHITE, 1));
				pp.editPixel(p.getX(), p.getY(), pos);
				if (pixeles.get(posyMayor).getX() < height) {
					if (HeightFigura < pixeles.get(posyMayor).getY() - yFigura) {
						HeightFigura = pixeles.get(posyMayor).getY() - yFigura;
						editFiguras(posFigura);
					}

					funcionRecursiva(pixeles.get(posyMayor), bb, pixeles, posFigura);
				}

			}
		}
		int posxMenor = p.getY() * width + p.getX() - 1;
//		if (posxMenor >= 0) {
//			if (pixeles.get(posxMenor).getStatus() == 1 && pixelesDetectados.get(posxMenor).getStatus() == 0) {
//				pixelesDetectados.set(posxMenor, new Pixel(p.getX(), p.getY(), Color.WHITE, 1));
//				pp.editPixel(p.getX(), p.getY(), pos);
//				if (pixeles.get(posxMenor).getX() >= 0) {
//					if (p.getX() < (xFigura)) {
//						xFigura = p.getX();
//
//						WidthFigura += 1;
//						editFiguras(posFigura);
//					}
//
//					funcionRecursiva(pixeles.get(posxMenor), bb, pixeles, posFigura);
//				}
//
//			}
//		}
//		int posyMenor = (p.getY() - 1) * width + p.getX();
//		if (posyMenor >= 0) {
//			if (pixeles.get(posyMenor).getStatus() == 1 && pixelesDetectados.get(posyMenor).getStatus() == 0) {
//				pixelesDetectados.set(posyMenor, new Pixel(p.getX(), p.getY(), Color.WHITE, 1));
//				pp.editPixel(p.getX(), p.getY(), pos);
//				if (pixeles.get(posyMenor).getY() >= 0) {
//					if (p.getY() < (yFigura)) {
//						yFigura = p.getY();
//
//						HeightFigura += 1;
//						editFiguras(posFigura);
//					}
//
//					funcionRecursiva(pixeles.get(posyMenor), bb, pixeles, posFigura);
//				}
//
//			}
//		}
		contador--;

	}

	public static void editFiguras(int posFigura) {
		synchronized (pp) {
			figuras.set(posFigura, new Figura((int) xFigura, (int) yFigura, WidthFigura, HeightFigura, false,
					figuras.get(posFigura).getC()));
		}

	}
}

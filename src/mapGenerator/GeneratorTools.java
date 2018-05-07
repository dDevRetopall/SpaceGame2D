package mapGenerator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import constants.Constants;



public class GeneratorTools {
	 public static ArrayList<Vec2> getTech( String fileName) {
	        BufferedImage imagen;
	        ArrayList<Vec2> result = new ArrayList<Vec2>();
	        File archivo = new File(fileName);
	        try {
	            imagen = ImageIO.read(archivo);
	        }
	        catch (Exception e) {
	            throw new RuntimeException(e.getMessage() + "   " + archivo.getAbsolutePath());
	        }
	        if (imagen == null) {
	            return result;
	        }
	        GeneratorTools.procesar(imagen, result);
	        return result;
	    }

	    private static void procesar(BufferedImage imagen, ArrayList<Vec2> result) {
	        int ofsetX = 0;
	        int ofsetY = 0;
	        Constants.widthMap=imagen.getWidth();
	        Constants.heightMap=imagen.getHeight();
	        for (int x = 0; x < imagen.getWidth(); ++x) {
	            for (int y = 0; y < imagen.getHeight(); ++y) {
	             
	                Vec2 v = new Vec2(x + ofsetX, y + ofsetY,false);
	                v.c = new Color(imagen.getRGB(x, y));
	                result.add(v);
	            }
	        }
	    }
}

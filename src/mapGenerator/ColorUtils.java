package mapGenerator;


import java.awt.Color;
import java.util.Random;

public class ColorUtils {
	 public static Color createRandomColor() {
	    	Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
			Color cc = Color.getHSBColor(hue, saturation, luminance);
			return cc;
	    }
}

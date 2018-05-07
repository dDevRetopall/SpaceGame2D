package utils.dataUtils;

import constants.Constants;

public class Probabilidad {
	public static int getIdByProbability(float p1, float p2, float p3, float p4, float p5, float p6,float p7) {
		float total = p1 + p2 + p3 + p4 + p5 + p6+p7;
		float r = (float) (Math.random() * total);
		if (Constants.DEBUG) {
			System.out.println("Probability: " + r);
		}
		if (r < p1) {
			return 1;
		} else if (r > p1 && r < p1 + p2) {
			return 2;

		} else if (r > p2 && r < p1 + p2 + p3) {
			return 3;

		} else if (r > p3 && r < p1 + p2 + p3 + p4) {
			return 4;

		} else if (r > p4 && r < p1 + p2 + p3 + p4 + p5) {
			return 5;
		} else if (r > p5 && r < p1 + p2 + p3 + p4 + p5+p6) {
			return 6;
		} else if (r > p6 && r < total) {
			return 7;
		} else {
			return -1;
		}

	}

	public static int getIdByProbability(float[] p) {
		
		float total = 0;
		for (int i = 0; i < p.length; i++) {
			total += p[i];
	
		}
		
		float r = (float) (Math.random() * total);

		for (int i = 1; i <= p.length; i++) {
			float suma=0;
			for (int j = 0; j < i; j++) {
				suma+=p[j];
			
			}
			float t=suma-p[i-1];
			if(Constants.DEBUG) {
			System.out.println("["+t+","+suma+"]");
			}
			if(r>(suma-p[i-1])&&r<suma) {
		
				return (i-1);
				
			}
		}
		return -1;

	}
}

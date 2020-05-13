package piserial;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		final double NUM_PUNTOS = 1000000000;
		double enElCirculo = 0;
		Random rnd = new Random();
		
		long inicio = System.currentTimeMillis();
		for(int i = 0; i < NUM_PUNTOS; i++) {
			double x = rnd.nextDouble();
			double y = rnd.nextDouble();						
			double h = Math.hypot(x, y);
			if(h <= 1) {//radio del círculo = 1
				enElCirculo++;
			}
		}
		double pi = 4 * (enElCirculo / NUM_PUNTOS);
		long tiempoMs = System.currentTimeMillis() - inicio;
		
		System.out.println("PI: " + pi);
		System.out.println("Tiempo (ms): " + tiempoMs);
	}

}

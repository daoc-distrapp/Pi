package piparalelo;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		final int NUM_HILOS = 4;
		final double NUM_PUNTOS = 10000000;
		double enElCirculo = 0;
		CalcPuntos[] hilos = new CalcPuntos[NUM_HILOS];
		CountDownLatch cdl = new CountDownLatch(NUM_HILOS);
		
		long inicio = System.currentTimeMillis();
		for(int i = 0; i < NUM_HILOS; i++) {
			hilos[i] = new CalcPuntos(NUM_PUNTOS / NUM_HILOS, cdl);
			hilos[i].start();
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < NUM_HILOS; i++) {
			enElCirculo += hilos[i].getEnElCirculo();
		}
		double pi = 4 * (enElCirculo / NUM_PUNTOS);
		long tiempoMs = System.currentTimeMillis() - inicio;
		
		System.out.println("PI: " + pi);
		System.out.println("Tiempo (ms): " + tiempoMs);
	}

}

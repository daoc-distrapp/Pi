package piparalelo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		final int NUM_HILOS = 1;
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

	private static class CalcPuntos extends Thread {
		Random rnd = new Random();
		CountDownLatch cdl;
		double numPuntos;
		double enElCirculo = 0;
		
		CalcPuntos(double numPuntos, CountDownLatch cdl) {
			this.numPuntos = numPuntos;
			this.cdl = cdl;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < numPuntos; i++) {
				double x = rnd.nextDouble();
				double y = rnd.nextDouble();						
				double h = Math.hypot(x, y);
				if(h <= 1) {//radio del círculo = 1
					enElCirculo++;
				}
			}
			cdl.countDown();
		}
		
		public double getEnElCirculo() {
			return enElCirculo;
		}
		
	}
	
}

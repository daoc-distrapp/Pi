package piparalelo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CalcPuntos extends Thread {
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
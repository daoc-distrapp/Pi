
package piexec;

import java.util.Random;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class TaskPi implements Runnable {
	Random rnd = new Random();
	double numPuntos;
	double enElCirculo = 0;
	
	TaskPi(double numPuntos) {
		this.numPuntos = numPuntos;
	}

	public void run() {
		for(int i = 0; i < numPuntos; i++) {
			double x = rnd.nextDouble();
			double y = rnd.nextDouble();						
			double h = Math.hypot(x, y);
			if(h <= 1) {//radio del círculo = 1
				enElCirculo++;
			}
		}	
		double pi = 4 * (enElCirculo / numPuntos);	
		System.out.println("PI: " + pi);
	}

}

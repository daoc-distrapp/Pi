
package piexecomp;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class TaskPi implements Callable<Double> {
	Random rnd = new Random();
	double numPuntos;
	double enElCirculo = 0;
	
	TaskPi(double numPuntos) {
		this.numPuntos = numPuntos;
	}

	public Double call() {
		for(int i = 0; i < numPuntos; i++) {
			double x = rnd.nextDouble();
			double y = rnd.nextDouble();						
			double h = Math.hypot(x, y);
			if(h <= 1) {//radio del círculo = 1
				enElCirculo++;
			}
		}	
		return enElCirculo;
	}

}

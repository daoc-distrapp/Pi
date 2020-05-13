
package piforkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * https://github.com/dordonez-ute-apdist/Pi.git
 * 
 * @author dordonez@ute.edu.ec
 */
public class Main {
    
    public static void main(String[] args) {
    	final double NUM_PUNTOS = 10000000;
    	
        ForkJoinPool fjPool = new ForkJoinPool();
        double enElCirculo = fjPool.invoke(new TaskPi(NUM_PUNTOS));
        
		double pi = 4 * (enElCirculo / NUM_PUNTOS);	
		System.out.println("PI: " + pi);
    }
     
}

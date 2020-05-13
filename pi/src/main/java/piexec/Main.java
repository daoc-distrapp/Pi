
package piexec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://github.com/dordonez-ute-apdist/Pi.git
 * 
 * @author dordonez@ute.edu.ec
 */
public class Main {

	/*
	 * Usar solamente un ExecutorService no nos permite recuperar los resultados
	 * de cada hilo. En este caso cada hilo calcula un PI independiente de los otros hilos
	 */
    public static void main(String[] args) {
    	final int NUM_PUNTOS = 1000000;
        ExecutorService pool = Executors.newFixedThreadPool(5);
        
        for(int i = 0; i < 50; i++) {           
            pool.execute(new TaskPi(NUM_PUNTOS));
        }
        
        pool.shutdown();
    }
    
}

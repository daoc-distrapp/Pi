
package piexecomp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
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
    	final int NUM_PUNTOS = 10000000;
    	final int NUM_TAREAS = 50;
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<Double> ecs = new ExecutorCompletionService<>(pool);
        
        for(int i = 0; i < NUM_TAREAS; i++) {           
            ecs.submit(new TaskPi(NUM_PUNTOS / NUM_TAREAS));
        }
        
        pool.shutdown();
        
        double enElCirculo = 0;
        for(int i = 0; i < NUM_TAREAS; i++) {       
            try {
            	enElCirculo += ecs.take().get();        
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
		double pi = 4 * (enElCirculo / NUM_PUNTOS);	
		System.out.println("PI: " + pi);

    }
    
}

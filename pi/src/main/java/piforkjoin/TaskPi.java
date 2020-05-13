
package piforkjoin;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class TaskPi extends RecursiveTask<Double> {
	Random rnd = new Random();
	double numPuntos;
    double enElCirculo = 0;
    
    public TaskPi(double numPuntos) {
        this.numPuntos = numPuntos;
    }
    
    @Override
    protected Double compute() {
        if(numPuntos <= 1000000) {//tarea manejable; calcula resultado
      	
    		for(int i = 0; i < numPuntos; i++) {
    			double x = rnd.nextDouble();
    			double y = rnd.nextDouble();						
    			double h = Math.hypot(x, y);
    			if(h <= 1) {//radio del círculo = 1
    				enElCirculo++;
    			}
    		}	
    		return enElCirculo;
            
        } else {//tarea muy grande; la divide en dos
        	
            TaskPi left = new TaskPi(numPuntos/2);//map
            left.fork();
            
            TaskPi right = new TaskPi(numPuntos/2);//map
            right.fork();
            
            enElCirculo = left.join() + right.join();//reduce
  
            return enElCirculo;
            
        }
    }

}

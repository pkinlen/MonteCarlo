
public class mcCalc {
	
    public static void main(String[] args) {
    	// create an instance of the simulator
    	doMonteCarlo();
    }
    /////////////////////////////////////////////////////
    private static void doMonteCarlo(){    	
    	
    	long       numSims = 1000000L;
    	
    	long       seed    = -1L;  // when the seed is negative, the timer will be 
    	                           // used to initialize the random number generator.
    	
    	// Simulator  sim  = new SurnameOnIsland();
    	
    	Simulator     sim  = new CollidingBullets(); 
    	
    	Calculator calc    = new Calculator(sim, numSims, seed);
    	   	
    	System.out.println("Average MC result:        " + Double.toString( calc.getAvg()));
    	System.out.println("with standard deviation:  " + Double.toString( calc.getStdDev()));    	
    }    
    ////////////////////////////////////////////////////////////////
    public static void reportError(String msg){
        System.out.println(msg); // Display the string.
    }
    
}    


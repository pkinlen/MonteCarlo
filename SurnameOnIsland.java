import java.util.Random;


public class SurnameOnIsland implements Simulator{
     private double m_probs[];
     private long   m_numKids[];
     private long   m_initialPop;
     private long   m_numGenerations;
     private long   m_initialNumWithSurname;

     public SurnameOnIsland() {
    	 
     	m_probs   = new double[] { 0.2, 0.2, 0.2, 0.2, 0.2 };
     	m_numKids = new long[]   {  0L,  1L,  2L,  3L,  4L };
     	
        m_initialPop            = 4000L;
     	m_numGenerations        = 100L;
     	m_initialNumWithSurname = 40L;

     	System.out.println("Constructing the SurnameOnIsland calculator, to estimate the survival prob.");
     	System.out.println("Initial pop:              "  + Long.toString(m_initialPop));
     	System.out.println("Initial num with surname: "  + Long.toString(m_initialNumWithSurname));
     	System.out.println("Num generations:          "  + Long.toString(m_numGenerations));
     }
     
     public double runOneSim(Random randGen){
    	 // for now this is set-up to deal with stable populations.
    	 
    	 long numPeopleWithSurname = m_initialNumWithSurname;
    	 long restOfPopulation     = m_initialPop -  m_initialNumWithSurname;
    	     	 
    	 for( long gen = 0; gen < m_numGenerations; gen++){    		 
    		 numPeopleWithSurname = getNumInNextGenWithSurname(numPeopleWithSurname, randGen);
    		 // restOfPopulation     = getNumInNextGenWithSurname(restOfPopulation, randGen);

    	     // totalPopulation = restOfPopulation + numPeopleWithSurname;
    	 }
    	 return (numPeopleWithSurname > 0 ? 1.0D : 0.0D);
    	 
     }
     
     public long half( long n, Random randGen){
    	 if ( n % 2 == 0)
    		 return n / 2;
    	 
    	 // when odd, we round down half the time, round down half the time
    	 else if ( randGen.nextBoolean())
    		 return (n-1) / 2;
    	 else 
    		 return 1 + (n-1) / 2 ;
    		 
     }
     /////////////////////////////////////////////////////////////////////////
     private long getNumInNextGenWithSurname(long prevNumPeopleWithSurname, Random randGen){
    	 long prevNumMen = half(prevNumPeopleWithSurname, randGen);
    	 
    	 long numKids = 0;
    	 
    	 for ( long n = 0; n < prevNumMen; n++){
    		 numKids += getNumChildrenOfOneMan(randGen);
    	 }
    	
    	 // System.out.println("Num kids " + Long.toString(numKids));
    	 
    	 return numKids;
     }
     /////////////////////////////////////////////////////////////////////////////
     private long getNumChildrenOfOneMan(Random randGen){

    	 double rnd = randGen.nextDouble();
    	 
    	 for ( int i = 0; i < m_probs.length; i++){
    		 if ( rnd <= m_probs[i]) {
    			 
    			 /*
    			 System.out.println("getting num kids" 
    		              + ", i="       + Integer.toString(i)
    					  + ", rnd="     + Double.toString(rnd) 
    					  + ", numKids=" + Long.toString(m_numKids[i]));
    			 */
    			 return m_numKids[i];
    			 
    		 } else 
    			 rnd -= m_probs[i];
    	 }
    	 
    	 System.out.println("There has been an error, shouldn't get here.");
    	 return -1;
     }
}

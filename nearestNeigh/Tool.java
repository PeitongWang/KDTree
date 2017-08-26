package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

public class Tool{

public static double variance(double[] data){
       
	      double variance=0;  
	       
	      double average=0; 
	        
	      double sum1=0;
	      
	      double sum2=0;  
	        
	      for(int i=0;i<data.length;i++){  
	            
	      sum1+=data[i];  
	        
	      }  
	        
	      average=sum1/data.length;  
	        
	      for(int i=0;i<data.length;i++){  
	            
	      sum2+=(data[i]-average)*(data[i]-average);  
	        
	      }  
	        
	      variance=sum2/data.length;  
	        
	      return variance;  
		  
	 }
	
	

      public static Point getMidPoint(ArrayList<Point> pointsArr){
		
    	 Point midPoint=pointsArr.get((int)(pointsArr.size()*0.55));
    	  
    	    return midPoint;
    	  
    	    }
	
	
	
	
	
	
}

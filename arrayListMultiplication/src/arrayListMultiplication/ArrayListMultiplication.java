package arrayListMultiplication;
import java.util.*;



public class ArrayListMultiplication {
	
	List <Integer> arrayList = new ArrayList<>() ;
	
	public ArrayListMultiplication(String str) {
		
		int str_len = str.length() - 1;
		
		for(int i = str_len; i >= 0; i--) {
			char c = str.charAt(i);
			
			arrayList.add((Integer) (Integer) Character.getNumericValue(c));
		}
	}
	
	
	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        
	        // Append digits from most significant (at index length-1) to least significant.
	        for (int i = this.arrayList.size() - 1; i >= 0; i--) {
	            sb.append(this.arrayList.get(i));
	        }
	        return sb.toString();
	    }

	    /**
	     * A static print method that prints the HugeInteger.
	     */
	    public static void print(ArrayListMultiplication i) {
	        if (i == null) {
	            System.out.println("null");
	        } else {
	            System.out.println(i.toString());
	        }
	    }
	    
	    public void mult(ArrayListMultiplication other){
	    	
	    	List<Integer> this_copy = this.arrayList;
	    	List<Integer>  other_copy = this.arrayList;
	    	
	    	for(int i = 0; i< this_copy.size() ; i++) {
	    		System.out.println("I Value: " + i);
	    		List<Integer>  tem_result = new ArrayList<>();
	    		
	    		for(int j = 0; j < other_copy.size(); j++) {
	    			int mult_res = this_copy.get(i) * other_copy.get(j);
	    			String result_str = Integer.toString( mult_res);
	    			System.out.println("String Value: " + result_str);
	    			int str_len = result_str.length() - 1;
	    			
	    			// YOU ARE HERE
	    			for(int k = str_len; k >= 0; k--) {
	    				char c = result_str.charAt(k);
	    				
	    				tem_result.add((Integer) Character.getNumericValue(c));
	    			}
	    		}
	    		
//	    		this.arrayList.clear();
//	    		this.arrayList.addAll(tem_result);
	    		
	    	}
	    	
	    }


}

package hugeInteger;

public class HugeIntegerMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HugeInteger first = new HugeInteger("420");
		HugeInteger two = new HugeInteger("2940") ;
		HugeInteger four = new HugeInteger("-456") ;
		
		HugeInteger three = new HugeInteger("-3"); 
		
		System.out.println(first);
		
		three.assign(two);
		
		System.out.println(three);
		
		// Test Equality
//		System.out.println("Test Equals");
//		System.out.println(three.equals(two));
//		System.out.println(three.equals(first));
//		System.out.println(three.equals(four));
//		
//		//Test Greater Than
//		System.out.println("Test greater than");
//		System.out.println(three.greaterThan(two));
//		System.out.println(three.greaterThan(first));
//		System.out.println(three.greaterThan(four));
//		
//		//Test Absolute value
//		System.out.println("Test abs");
//		System.out.println(four);
//		System.out.println(four.abs());
//		System.out.println(four);
		
		
		//Test Addition value
//		System.out.println("Addition abs");
//		try {
//			three.add(first);
//			System.out.println(three);
//		}catch(Exception e) {
//			System.out.println("Exception thrown");
//		};
//		
//		//Test Addition
//		System.out.println("Test multiplication");
//		try {
//			two.divide(first);
//		}catch(Exception e) {
//			System.out.println("Exception thrown");
//		};
//		
//		System.out.println(two);
		
		try {
			HugeInteger.multPerformance(two, first);
		}catch(Exception e) {
			System.out.println("Exception thrown");
		};

	}

}

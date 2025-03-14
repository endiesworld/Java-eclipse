package complexity;

public class Complexity {
	
	public static void methodSquare(int n) {
		int opsCounter = 1;
		
		for(int i = 0; i <n ; i++) {
			
			for(int j = 0; j < n; j++) {
				System.out.println("MethodSquare Operation "+opsCounter); 
				opsCounter++ ;
			}
		}
	}
	
	
	public static void methodCube(int n) {
		int opsCounter = 1;
		
		for(int i = 0; i <n ; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					System.out.println("MethodCube Operation "+opsCounter); 
					opsCounter++ ;
				}
			}
		}
	}
	
	
	public static void methodLog(int n) {
		int opsCounter = 1;
		for(int j = 1; j < n; j *= 2) {
			System.out.println("MethodLog Operation "+opsCounter); 
			opsCounter++ ;
		}
	}
	
	
	public static void methodNLogN(int n) {
		int opsCounter = 1;
		
		for(int i = 0; i <n ; i++) {
			
			for(int j = 1; j < n; j *= 2) {
				System.out.println("MethodNLogN Operation "+opsCounter); 
				opsCounter++ ;
			}
		}
	}
	
	
	public static void methodLogLogN(int n) {
		int opsCounter = 1;
		
		for(int j = 1; j < n; ) {
			System.out.println("MmethodLogLogN Operation "+opsCounter); 
			 n = (int) Math.sqrt(n);
			opsCounter++ ;
		}
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
////		 methodSquare(8);
////		 methodCube(3);
//		 methodLog(8);
////		 methodNLogN(8);
//		 methodLogLogN(8);
//	}

}

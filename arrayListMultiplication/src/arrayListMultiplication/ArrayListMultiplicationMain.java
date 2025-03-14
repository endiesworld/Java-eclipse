package arrayListMultiplication;

public class ArrayListMultiplicationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayListMultiplication one = new ArrayListMultiplication("5");
		ArrayListMultiplication two = new ArrayListMultiplication("5");
		
		System.out.println(one);
		System.out.println(two);
		two.mult(one);
		System.out.println(two);

	}

}

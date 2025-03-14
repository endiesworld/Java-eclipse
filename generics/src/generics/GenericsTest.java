package generics;

public class GenericsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Generics = A concept where you can write a class, interface, or method
		//      that is compatible with different data types.
		//     <T> type parameter (placeholder that gets replaced with a real type)
		//     <String> type argument (specifies the type)
		
		BoxGeneric<String> box = new BoxGeneric<>();
		box.setItem("banana");
		System.out.println(box.getItem());
		
		ProductGeneric<String, Double> product1 = new ProductGeneric<>("apple", 0.50);
		ProductGeneric<String, Integer> product2 = new ProductGeneric<>("ticket", 15);
		System.out.println("The items and price in the cart are ");
		System.out.println(product1.getItem() + "Price: "+ product1.getPrice());
		System.out.println(product2.getItem() + "Price: "+ product2.getPrice());
		System.out.println("Total Price: "+ (product1.getPrice() + product2.getPrice()));
		

	}

}

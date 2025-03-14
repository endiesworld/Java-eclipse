package generics;

public class ProductGeneric<T, U> {
	
	   T item;
	   U price;

	   public ProductGeneric(T item, U price){
	       this.item = item;
	       this.price = price;
	   }

	   public T getItem(){
	       return this.item;
	   }

	   public U getPrice(){
	       return this.price;
	   }

}

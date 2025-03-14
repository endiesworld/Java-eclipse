package generics;

public class BoxGeneric<T> {
	T item;
	
	public void setItem(T item){
		this.item = item;
	}
	
	public T getItem(){
		return item;
	}

}

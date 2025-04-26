package inventoryItemSortingDemo;

class InventoryItem {
	   public String name;
	   public double price;
	   public int numberInStock;
	   
	   public InventoryItem(String itemName, double itemPrice, int numberOfItemsInStock) {
	      name = itemName;
	      price = itemPrice;
	      numberInStock = numberOfItemsInStock;
	   }
	   
	   @Override
	   public String toString() {
	      return String.format("%s - $%.2f (%d in stock)", name, price, numberInStock);
	   }
	}

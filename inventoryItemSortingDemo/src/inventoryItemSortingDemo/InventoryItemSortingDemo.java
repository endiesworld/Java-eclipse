package inventoryItemSortingDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InventoryItemSortingDemo {
   public static void main(String[] args) {
      ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
      items.add(new InventoryItem("Toothpaste", 5.00, 250));
      items.add(new InventoryItem("Toothbrush", 7.00, 500));
      items.add(new InventoryItem("Vitamins", 5.00, 500));
      items.add(new InventoryItem("Gum", 1.50, 100));
      items.add(new InventoryItem("Candy", 1.50, 200));
      items.add(new InventoryItem("Strawberry smoothie", 5.00, 20));
      items.add(new InventoryItem("Potato chips", 3.00, 40));
      items.add(new InventoryItem("Mints", 2.50, 50));
      items.add(new InventoryItem("Energy bar", 5.00, 30));
      items.add(new InventoryItem("Eggs", 2.50, 40));

      System.out.println("Unsorted list:");
      for (InventoryItem item : items) {
         System.out.println(item.toString());
      }
      System.out.println();
      
      // Sort with each comparator
      ArrayList<Comparator<InventoryItem>> comparators;
      comparators = new ArrayList<Comparator<InventoryItem>>();
      comparators.add(new InventoryItemNameComparer(true));
      comparators.add(new InventoryItemNameComparer(false));
      comparators.add(new InventoryItemPriceComparer(true));
      comparators.add(new InventoryItemPriceComparer(false));
      comparators.add(new InventoryItemNumberInStockComparer(true));
      comparators.add(new InventoryItemNumberInStockComparer(false));
      comparators.add(new ReversePriceThenNameComparer());
      comparators.add(new MyCustomComparer());
      String[] sortTypes = { "item name", "item name (descending)", 
         "item price", "item price (descending)", "number in stock",
         "number in stock (descending)",
         "item price (descending), then name (ascending)",
         "MyCustomComparer"
      };
      int i = 0;
      for (Comparator<InventoryItem> comparator : comparators) {
         Collections.sort(items, comparator);
         System.out.printf("List sorted by %s:\n", sortTypes[i++]);
         for (InventoryItem item : items) {
            System.out.println(item.toString());
         }
         System.out.println();
      }
   }
}


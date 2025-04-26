package inventoryItemSortingDemo;

import java.util.Comparator;

class ReversePriceThenNameComparer implements Comparator<InventoryItem> {
   public int compare(InventoryItem item1, InventoryItem item2) {
      // Primary sort is descending by price
      if (item1.price > item2.price) {
         return -1;
      }
      else if (item1.price < item2.price) {
         return 1;
      }
      
      // If prices are equal, secondary sort is ascending by name
      return item1.name.compareTo(item2.name);
   }
}


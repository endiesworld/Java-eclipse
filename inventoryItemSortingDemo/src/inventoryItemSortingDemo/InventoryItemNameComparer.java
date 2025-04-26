package inventoryItemSortingDemo;

import java.util.Comparator;

class InventoryItemNameComparer implements Comparator<InventoryItem> {
   private boolean isAscending;
   
   InventoryItemNameComparer(boolean compareForAscending) {
      isAscending = compareForAscending;
   }
   
   public int compare(InventoryItem item1, InventoryItem item2) {
      if (isAscending) {
         return item1.name.compareTo(item2.name);
      }
      return item2.name.compareTo(item1.name);
   }
}
package inventoryItemSortingDemo;

import java.util.Comparator;

class InventoryItemPriceComparer implements Comparator<InventoryItem> {
   private boolean isAscending;
   
   InventoryItemPriceComparer(boolean compareForAscending) {
      isAscending = compareForAscending;
   }

   public int compare(InventoryItem item1, InventoryItem item2) {
      if (item1.price > item2.price) {
         return isAscending ? 1 : -1;
      }
      else if (item1.price < item2.price) {
         return isAscending ? -1 : 1;
      }
      return 0;
   }
}


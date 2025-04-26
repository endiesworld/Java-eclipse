package inventoryItemSortingDemo;

import java.util.Comparator;

class InventoryItemNumberInStockComparer implements Comparator<InventoryItem> {
   private boolean isAscending;
   
   InventoryItemNumberInStockComparer(boolean compareForAscending) {
      isAscending = compareForAscending;
   }

   public int compare(InventoryItem item1, InventoryItem item2) {
      if (isAscending) {
         return item1.numberInStock - item2.numberInStock;
      }
      return item2.numberInStock - item1.numberInStock;
   }
}

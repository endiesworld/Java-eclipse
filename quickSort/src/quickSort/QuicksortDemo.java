package quickSort;

import java.util.Arrays;

public class QuicksortDemo {
   private static int partition(int[] numbers, int startIndex, int endIndex) {
      // Select the middle value as the pivot.
      int midpoint = startIndex + (endIndex - startIndex) / 2;
      int pivot = numbers[midpoint];
   
      // "low" and "high" start at the ends of the array segment
      // and move towards each other.
      int low = startIndex;
      int high = endIndex;
   
      boolean done = false;
      while (!done) {
         // Increment low while numbers[low] < pivot
         while (numbers[low] < pivot) {
            low = low + 1;
         }
      
         // Decrement high while pivot < numbers[high]
         while (pivot < numbers[high]) {
            high = high - 1;
         }
      
         // If low and high have crossed each other, the loop
         // is done. If not, the elements are swapped, low is
         // incremented and high is decremented.
         if (low >= high) {
            done = true;
         }
         else {
            int temp = numbers[low];
            numbers[low] = numbers[high];
            numbers[high] = temp;
            low++;
            high--;
         }
      }

      // "high" is the last index in the left segment.
      return high;
   }
   
   private static void quicksort(int[] numbers, int startIndex, int endIndex) {
      // Only attempt to sort the array segment if there are
      // at least 2 elements
      if (endIndex <= startIndex) {
         return;
      }
          
      // Partition the array segment
      int high = partition(numbers, startIndex, endIndex);

      // Recursively sort the left segment
      quicksort(numbers, startIndex, high);

      // Recursively sort the right segment
      quicksort(numbers, high + 1, endIndex);
   }

   public static void main(String[] args) {
      // Create an array of numbers to sort
      int[] numbers = { 12, 18, 3, 7, 32, 14, 91, 16, 8, 57 };
      
      // Display the unsorted contents of the array
      System.out.println("UNSORTED: " + Arrays.toString(numbers));
      
      // Call the quicksort method
      quicksort(numbers, 0, numbers.length - 1);

      // Display the sorted contents of the array
      System.out.println("SORTED: " + Arrays.toString(numbers));
   }
}

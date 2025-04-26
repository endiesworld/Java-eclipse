package shellSort;
import java.util.Arrays;

public class ShellSort {
	private static int insertionSortInterleaved(int[] numbers, int startIndex, int gap) {
	      int swaps = 0;
	      for (int i = startIndex + gap; i < numbers.length; i += gap) {
	         int j = i;
	         while (j - gap >= startIndex && numbers[j] < numbers[j - gap]) {
	            swaps++;
	            // Swap numbers[j] and numbers [j - 1]
	            int temp = numbers[j];
	            numbers[j] = numbers[j - gap];
	            numbers[j - gap] = temp;
	            j -= gap;
	         }
	      }
	      return swaps;
	   }
	   
	   private static int shellSort(int[] numbers, int[] gapValues) {
	      int totalSwaps = 0;
	      for (int g = 0; g < gapValues.length; g++) {
	         int swapsForGap = 0;
	         for (int i = 0; i < gapValues[g]; i++) {
	            swapsForGap += insertionSortInterleaved(numbers, i, gapValues[g]);
	         }
	         System.out.printf("%s (after %d swap%s with gap=%d)\n",
	            Arrays.toString(numbers), swapsForGap, 
	            (swapsForGap == 1 ? "" : "s"), gapValues[g]);
	         totalSwaps += swapsForGap;
	      }
	      return totalSwaps;
	   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = { 55, 99, 87, 54, 31, 17, 28, 18 };
	      int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
	      
	      // Display the contents of the array
	      System.out.println("---- Shell sort ----");
	      System.out.println("UNSORTED: " + Arrays.toString(numbers));
	      
	      // Call the shellSort method
	      int[] gapValues = { 4, 2, 1 };
	      int totalSwaps = shellSort(numbers, gapValues);

	      // Display sorted array and the total number of swaps
	      System.out.println("SORTED: " + Arrays.toString(numbers));
	      System.out.println("Total swaps: " + totalSwaps);
	      
	      // Sort the same array with insertion sort
	      System.out.println();
	      System.out.println("---- Regular insertion sort ----");
	      System.out.println("UNSORTED: " + Arrays.toString(numbersCopy));
	      totalSwaps = insertionSortInterleaved(numbersCopy, 0, 1);
	      System.out.println("SORTED: " + Arrays.toString(numbersCopy));
	      System.out.println("Total swaps: " + totalSwaps);

	}

}

package insertionSort;
import java.util.Arrays;

public class InsertionSort {
	
	private static void insertionSort(int[] myArray) {
		
		for(int i=1; i<myArray.length; ++i) {
			int j = i;
			
			while(j > 0 && myArray[j] < myArray[j -1]) {
				int temp = myArray[j -1];
				myArray[j-1] = myArray[j];
				myArray[j] = temp;
				j--;
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int[] numbers = { 10, 2, 78, 4, 45, 32, 7, 11 };
	      
	      // Display the contents of the array
	      System.out.println("UNSORTED: " + Arrays.toString(numbers));
	      
	      // Call the insertionSort method
	      insertionSort(numbers);
	      
	      // Display the sorted contents of the array
	      System.out.println("SORTED: " + Arrays.toString(numbers));
		
	}

}

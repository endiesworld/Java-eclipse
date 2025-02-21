package selectionSortAlgo;

import java.util.Arrays;

public class SelectionSort {
	
	public static void  selectionSort(int[] arr) {
		
		int smallestIdx ;
		
		for(int i= 0; i<arr.length; i++) {
			
			smallestIdx = i;
			
			for(int j = i+1; j <arr.length; j++) {
				if(arr[j] < arr[smallestIdx]) {
					smallestIdx = j;
				}
			}
			
			int store = arr[i] ;
			arr[i] = arr[smallestIdx];
			arr[smallestIdx] = store;
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create an array of numbers to sort
	      int[] numbers = { 10, 2, 78, 4, 45, 32, 7, 11 };
	      
	      System.out.println("Unsorted array: " + Arrays.toString(numbers));
	      
	      selectionSort(numbers);
	      
	      System.out.println("Sorted array is: " + Arrays.toString(numbers));
		

	}

}

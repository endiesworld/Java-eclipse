package mergeSort;

import java.util.Arrays;

public class MergeSort {
	
	public static void merge(int[] arr, int i, int j, int k) {
		
		int temp_arr_size = k - i + 1;
		
		int left_start = i;
		int right_start = j + 1;
		
		int[] temp_arr = new int[temp_arr_size] ;
		int temp_pos = 0 ;
		
		while(left_start <= j && right_start <= k) {
			if(arr[left_start] <= arr[right_start]) {
				temp_arr[temp_pos] = arr[left_start];
				left_start++;
				temp_pos++;
			}
			else {
				temp_arr[temp_pos] = arr[right_start];
				right_start++;
				temp_pos++;
			}
			
		}
		
		while(left_start <= j) {
			temp_arr[temp_pos] = arr[left_start];
			left_start++;
			temp_pos++;
		}
		
		while(right_start <= k) {
			temp_arr[temp_pos] = arr[right_start];
			right_start++;
			temp_pos++;
		}
		
		
		for(int n = 0; n < temp_arr_size; n++) {
			arr[i + n] = temp_arr[n];
		}
		
	}
	
	
	public static void sort(int[] arr, int i, int k) {
		
		int j = 0;
		
		if( i < k) {
			j = ( i + k) / 2 ;
			
			sort(arr, i, j);
			sort(arr, j+1, k);
			merge(arr, i, j, k);
		}
			
		
	}
	
	
	public static void mergeSort(int[] arr) {
		
		int i = 0;
		int k = arr.length -1;
		
		sort(arr, i, k);
		
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr = {61, 76, 19, 4, 94, 32, 27, 83, 58}; 
		
		System.out.println("UNSORTED ARRAY: " + Arrays.toString(arr));
		
		mergeSort(arr);
		
		System.out.println("UNSORTED ARRAY: " + Arrays.toString(arr));

	}

}

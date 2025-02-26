package arrayList;

public class ArrayBasedList {
	
	   // Private members
	   private int[] arrayData;
	   private int arrayListLength;

	   // The default constructor initializes the list with a capacity of 4
	   public ArrayBasedList() {
	      this(4);
	   }
	   
	   public ArrayBasedList(int capacity) {
	      this.arrayData = new int[capacity];
	      this.arrayListLength = 0;
	   }
	   
	   public void append(int newItem) {
		   // Resize if the array is full
		   if (arrayData.length == arrayListLength) {
		      resize(arrayListLength * 2);
		   }

		   // Insert the new item at index arrayListLength
		   arrayData[arrayListLength] = newItem;

		   // Increment the array's length
		   ++arrayListLength;
		}
		   
		public void resize(int newAllocationSize) {
		   // Create a new array with the indicated size
		   int[] newArray = new int[newAllocationSize];

		   // Copy items in current array into the new array
		   for (int i = 0; i < arrayListLength; ++i) {
		      newArray[i] = arrayData[i];
		   }

		   // Assign the arrayData member with the new array
		   arrayData = newArray;
		}
		
		public void prepend(int data) {
			
			if( this.arrayData.length == this.arrayListLength) {
				resize(arrayListLength * 2);
			}
			
			for(int i = arrayListLength; i > 0; i--) {
				arrayData[i] = arrayData[i-1];
			}
			
		  // Insert the new item at index 0
		   arrayData[0] = data;
		        
		   // Update the array list's length
		   ++arrayListLength;
		}
		
		public void insertAfter(int index, int data) {
			
			if( this.arrayData.length == this.arrayListLength) {
				resize(arrayListLength * 2);
			}
			
			// Shift all the array items to the right,
		   // starting from the last item and moving down to
		   // the item just past the given index
		   for (int i = arrayListLength; i > index + 1; --i) {
		      arrayData[i] = arrayData[i - 1];
		   }

		   // Insert the new item at the index just past the
		   // given index
		   arrayData[index + 1] = data;
		        
		   // Update the array's length
		   ++arrayListLength;
		}
		
		public int search(int item) {
		   // Iterate through the entire array
		   for (int i = 0; i < arrayListLength; ++i) {
		      // If the current item matches the search
		      // item, return the current index immediately.
		      if (arrayData[i] == item) {
		         return i;
		      }
		   }

		   // If the above loop finishes without returning,
		   // then the search item was not found.
		   return -1;
		}
		
		public void removeAt(int index) {
			   // Make sure the index is valid for the current array
		   if (index >= 0 && index < arrayListLength) {
		      // Shift down all items after the given index
		      for (int i = index; i < arrayListLength - 1; ++i) {
		         arrayData[i] = arrayData[i + 1];
		      }

		      // Update the array's length
		      --arrayListLength;
		   }
		}
		
		   
	   public void print() {
	      for (int i = 0; i < arrayListLength; ++i) {
	         System.out.print(arrayData[i] + " ");
	      }
	   }
	   
	   public void printInfo() {
	      System.out.print("Allocation size: " + arrayData.length);
	      System.out.print(", length: " + arrayListLength);
	   }

}

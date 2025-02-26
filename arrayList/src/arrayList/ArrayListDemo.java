package arrayList;
import java.util.Scanner;

public class ArrayListDemo {
	   public static void main(String[] args) {
	      int[] numbers = { 3, 2, 84, 18, 91, 6, 19, 12 };
	      
	      // Initialize a new ArrayList and add numbers
	      ArrayBasedList myList = new ArrayBasedList();
	      for (int number : numbers)
	         myList.append(number);

	      // Show the array before the operation
	      System.out.println("-- Array before operation --");
	      myList.printInfo();
	      System.out.println();
	      myList.print();
	      System.out.println();
	      
	      // Read an instruction
	      Scanner scanner = new Scanner(System.in);
	      String instructionLine = scanner.nextLine();
	      String[] instruction = instructionLine.split(" ");
	      String method = instruction[0];
	      
	      int item, index;
	      if (method.equals("append")) {
	         item = Integer.parseInt(instruction[1]);
	         myList.append(item);
	      }
	      else if (method.equals("insertAfter")) {
	         index = Integer.parseInt(instruction[1]);
	         item = Integer.parseInt(instruction[2]);
	         myList.insertAfter(index, item);
	      }
	      else if (method.equals("prepend")) {
	         item = Integer.parseInt(instruction[1]);
	         myList.prepend(item);
	      }
	      else if (method.equals("removeAt")) {
	         index = Integer.parseInt(instruction[1]);
	         myList.removeAt(index);
	      }
	      else if (method.equals("search")) {
	         item = Integer.parseInt(instruction[1]);
	         System.out.println("Search result: " + myList.search(item));
	      }
	      else {
	         System.out.println("Unknown method: " + method);
	      }
	      
	      System.out.println();
	      System.out.println("-- Array after operation --");
	      myList.printInfo();
	      System.out.println();
	      myList.print();
	      System.out.println();
	   }
	}
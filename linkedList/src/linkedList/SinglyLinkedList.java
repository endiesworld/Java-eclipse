package linkedList;

public class SinglyLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList numList = new LinkedList();
	      Node nodeA = new Node(66);
	      Node nodeB = new Node(99);
	      Node nodeC = new Node(44);
	      Node nodeD = new Node(95);
	      Node nodeE = new Node(42);
	      Node nodeF = new Node(17);

	      numList.append(nodeB);   // Add 99
	      numList.append(nodeC);   // Add 44, make the tail
	      numList.append(nodeE);   // Add 42, make the tail

	      numList.prepend(nodeA);  // Add 66, make the head

	      numList.insertAfter(nodeC, nodeD);  // Insert 95 after 44
	      numList.insertAfter(nodeE, nodeF);  // Insert 17 after tail (42)

	      // Output list
	      System.out.print("List after adding nodes: ");
	      numList.printList();

	      // Remove the tail node, then the head node
	      numList.removeAfter(nodeE);
	      numList.removeAfter(null);

	      // Output final list
	      System.out.print("List after removing nodes: ");
	      numList.printList();
	   }
}

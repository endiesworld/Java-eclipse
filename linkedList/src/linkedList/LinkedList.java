package linkedList;

public class LinkedList {
	
	private Node head;
	private Node tail;
	
	public LinkedList() {
		head = null;
	    tail = null;
		
	}
	
	public void append(Node newNode) {
		if(this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		}else {
			this.tail.next = newNode;
			this.tail = newNode;
		}
	}
	
	public void prepend(Node newNode) {
		 if (head == null) {
		      head = newNode;
		      tail = newNode;
		   }else {
				newNode.next = this.head;
				this.head = newNode ;
			}
	}
	
	public void insertAfter(Node currentNode, Node newNode) {
		
		if (this.head == null) {
		      this.head = newNode;
		      this.tail = newNode;
		   }
		   else if (currentNode == this.tail) {
		      this.tail.next = newNode;
		      this.tail = newNode;
		   }
		   else {
		      newNode.next = currentNode.next;
		      currentNode.next = newNode;
		   }
		
	}
	
	public void removeAfter(Node currentNode) {
		
		   if (currentNode == null && this.head != null) {
			      // Special case: remove head
			      Node succeedingNode = this.head.next;
			      this.head = succeedingNode;
			      if (succeedingNode == null) {
			          // Last item was removed
			          this.tail = null;
			      }
		   }
		   else if (currentNode.next != null) {
		      Node succeedingNode = currentNode.next.next;
		      currentNode.next = succeedingNode;
		      if (succeedingNode == null) {
		         // Remove tail
		         this.tail = currentNode;
		      }
		   }
	
	}
	
	public void printList() {
		
		Node currentNode = this.head;
		
		while(currentNode != null) {
			System.out.print(currentNode.data + ", ");
			currentNode = currentNode.next;
		}
		System.out.println();
	}

}

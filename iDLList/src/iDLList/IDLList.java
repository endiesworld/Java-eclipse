package iDLList;
import java.util.*;

public class IDLList<E> {
		
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices; 
	
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		indices = new ArrayList<>();
	}
	
	//adds elem at position index (counting from wherever head is). It uses the index for fast access.
	public boolean add(int index, E elem) {
		if(index > this.size) {
			throw new IllegalArgumentException("Index greater than iDLList size");
		}
		Node nodeElem = new Node(elem) ;
		Node oldElem = this.indices.get(index);
		Node oldElemPrev = oldElem.prev;
		
		nodeElem.prev = oldElemPrev;
		oldElemPrev.next = nodeElem;
		nodeElem.next = oldElem;
		oldElem.prev = nodeElem;
		
		this.indices.add(index, nodeElem);
		this.size += 1;
		
		return true;
	}
	
	//adds elem at the head (i.e. it becomes the first element of the list).
	public boolean add(E elem) {
		Node nodeElem = new Node(elem) ;
		
		if(this.head == null) {
			this.head = nodeElem;
			this.tail = nodeElem;
			indices.add(nodeElem);
		}
		
		else {
			nodeElem.next = this.head;
			this.head.prev = nodeElem;
			this.head = nodeElem;
			this.indices.add(0, nodeElem);
		}
			
		this.size += 1;
		return true;
	}
	
	//adds elem as the new last element of the list (i.e. at the tail).
	public boolean append(E elem) {
		Node nodeElem = new Node(elem) ;
		
		if(this.head == null) {
			this.head = nodeElem;
			this.tail = nodeElem;
			indices.add(nodeElem);
		}
		else {
			this.tail.next = nodeElem;
			nodeElem.prev = this.tail;
			this.tail = nodeElem;
			this.indices.add(nodeElem);
		}
			
		this.size += 1;
		return true;
	}
	
	// returns the object at position index from the head. It uses the index for fast access. Indexing starts from 0, thus get(0) returns the head element of the list.
	public E get (int index)  {
		if(index >= this.size) {
			throw new IllegalArgumentException("Index greater than iDLList size");
		}
		
		return this.indices.get(index).data;
		
	};
	
	//that returns the object at the head.
	public E getHead () {
		if (this.head == null) throw new IllegalStateException("List is empty");
		return this.head.data;
	}
	
	//returns the object at the tail.
	public E getLast () {
		 if (this.tail == null) throw new IllegalStateException("List is empty");
		return this.tail.data;
	}
	
	// returns the list size.
	public int size() {
		return this.size;
	}
	
	//removes and returns the element at the head
	public E remove() {
		if(this.head == null) {
			throw new IllegalArgumentException("DLL is Empty");
		}
		
		Node headElem = this.head;
		E data = this.head.data;
		
		if(this.head ==  this.tail) {
			this.head = null ;
			this.tail = null;
		}
		else {
			Node newHead = headElem.next;
			headElem.next = null;
			newHead.prev = null;
			this.head = newHead ;
		}
		
		this.indices.removeFirst();
		this.size -= 1;
		
		return data;
	}
	
	// removes and returns the element at the tail
	public E removeLast(){
		if(this.tail == null) {
			throw new IllegalArgumentException("DLL is Empty");
		}
		Node<E> tailElem = this.tail;
		E data = tailElem.data;
		
		if(this.head ==  this.tail) {
			this.head = null ;
			this.tail = null;
		}
		
		else {
			Node<E> newTail = tailElem.prev;
			tailElem.prev = null;
			newTail.next = null;
			this.tail = newTail;
		}
//		
		this.indices.removeLast();
		this.size -= 1;
		return data;
	}
	
	// removes and returns the element at the index index. Use the index for fast access.
	public E removeAt(int index){
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Index greater than iDLList size");
		}
		Node<E> removeElem = this.indices.get(index);
		E data = removeElem.data;
		
		if(removeElem == this.head) {
			data = this.remove();
		}
		else if(removeElem == this.tail){
			data = this.removeLast();
		}
		else {
			
			removeElem = this.indices.remove(index);
			
			Node<E> elemPrev = removeElem.prev;
			Node<E> elemNext = removeElem.next;
			elemNext.prev = elemPrev;
			elemPrev.next = elemNext;
			
			removeElem.prev = null;
			removeElem.next = null;
			this.size -= 1;
		}
		
		return data ;
	}
	
	//removes the first occurrence of elem in the list and returns true. Return false if elem was not in the list.
	public boolean remove (E elem) {
		for (int i = 0; i < size; i++) {
            if (indices.get(i).data.equals(elem)) {
                removeAt(i);
                return true;
            }
        }
        return false;
	}
	
	//presents a string representation of the list.
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" );
        // Append digits from most significant (at index length-1) to least significant.
        for (int i = 0; i < this.size ; i++) {
        		
        	if(i != this.size -1 )
        		sb.append( this.indices.get(i).data + ",");
        	else
        		sb.append(this.indices.get(i).data + "]");
        }
        return sb.toString();
    }
	
	public static class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		
		public Node (E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		public Node (E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	};

}

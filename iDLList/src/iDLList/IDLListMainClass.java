package iDLList;

import iDLList.IDLList.Node;

public class IDLListMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDLList<Integer> list = new IDLList<>();
        
        list.add(10);
        list.add(20);
        list.append(30);
        list.append(40);
        list.add(1, 15);
        list.append(50);
        
        
        
        System.out.println("List after additions: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Head: " + list.getHead());
        System.out.println("Tail: " + list.getLast());
        System.out.println("Get Element at index 2: " + list.get(2));
        
        System.out.println("Remove head element : " + list.remove());
        System.out.println("List after removing head: " + list);
        list.removeLast();
        System.out.println("List after removing last: " + list);
        list.removeAt(1);
        System.out.println("List after removing at index 1: " + list);
        list.remove(15);
        System.out.println("List after removing element 15: " + list);
        System.out.println("Size: " + list.size()); 

	}

}

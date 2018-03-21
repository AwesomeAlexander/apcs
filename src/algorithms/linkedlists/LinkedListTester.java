package algorithms.linkedlists;

public class LinkedListTester {

	public static void main(String[] args) {
		LinkedListOfStrings myLinkedList = new LinkedListOfStrings();
		
		// SHOULD be adding through the public interface with add()
		// However, since we haven't implemented yet, be bad and add by 
		// accessing member variables directly.
		myLinkedList.head = new Node("Abe"); 
		myLinkedList.head.next = new Node("Bart");
		myLinkedList.head.next.next = new Node("Carol");
		myLinkedList.head.next.next.next = new Node("Dawn");	
		myLinkedList.head.next.next.next.next = new Node("Abel");
		myLinkedList.head.next.next.next.next.next = new Node("The Abercrombie Guy");	
		myLinkedList.head.next.next.next.next.next.next = new Node("The Bad Guy");	
		myLinkedList.head.next.next.next.next.next.next.next = new Node("The Abercrombie Guy's friend");	
		
		// check .toString()
		System.out.println(myLinkedList); 
		
		// check iterative and recursive count nodes
		System.out.println(myLinkedList.countNodesWithString("Abe"));
		System.out.println(myLinkedList.recursivelyCountNodesWithString(myLinkedList.head, "Abe"));
		
		// check iterative and recursive print reversed (HW)
		myLinkedList.recursivelyPrintReversed(myLinkedList.head);
		System.out.println();
		myLinkedList.recursivelyPrintReversed(myLinkedList.head.next.next); 
		
		System.out.println();
		myLinkedList.printReversed(myLinkedList.head);
		System.out.println();
		myLinkedList.printReversed(myLinkedList.head.next.next);
	}
	
	
}
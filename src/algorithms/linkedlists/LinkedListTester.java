package algorithms.linkedlists;

public class LinkedListTester {

	public static void main(String[] args) {
		LinkedListOfStrings myLinkedList = new LinkedListOfStrings();
		
		// SHOULD be adding through the public interface with add()
		// However, since we haven't implemented yet, be bad and add by 
		// accessing member variables directly.
		// myLinkedList.head = new Node("Abe"); 
		// myLinkedList.head.next = new Node("Bart");
		// myLinkedList.head.next.next = new Node("Carol");
		// myLinkedList.head.next.next.next = new Node("Dawn");	
		// myLinkedList.head.next.next.next.next = new Node("Abel");
		// myLinkedList.head.next.next.next.next.next = new Node("The Abercrombie Guy");	
		// myLinkedList.head.next.next.next.next.next.next = new Node("The Bad Guy");	
		// myLinkedList.head.next.next.next.next.next.next.next = new Node("The Abercrombie Guy's friend");	
		myLinkedList.head = new Node<String>("[HEAD]");
		myLinkedList.head
			.setNext(new Node<String>("Abe"))
			.setNext(new Node<String>("Bart"))
			.setNext(new Node<String>("Carol"))
			.setNext(new Node<String>("Dawn"))
			.setNext(new Node<String>("Abel"))
			.setNext(new Node<String>("The Abercrombie Guy"))
			.setNext(new Node<String>("The Bad Guy"))
			.setNext(new Node<String>("The Abercrombie Guy's friend"))
			.setNext(new Node<String>("[TAIL]"))
			;


		// check .toString()
		System.out.println(myLinkedList); 

		// check iterative and recursive count nodes
		System.out.println(myLinkedList.countNodesWithString("Abe"));
		System.out.println(myLinkedList.recursivelyCountNodesWithString(myLinkedList.head, "Abe"));
		
		// check iterative and recursive print reversed (HW)
		myLinkedList.recursivelyPrintReversed(myLinkedList.head);
		System.out.println();
		myLinkedList.recursivelyPrintReversed(myLinkedList.head.getNext().getNext()); 
		
		System.out.println();
		myLinkedList.printReversed(myLinkedList.head);
		System.out.println();
		myLinkedList.printReversed(myLinkedList.head.getNext().getNext());
	}
	
	
}
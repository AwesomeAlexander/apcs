package algorithms.linkedlists;

public class LinkedListTester {

	public static void main(String[] args) {
		LinkedListOfStrings myLinkedList;
		
		myLinkedList = new LinkedListOfStrings("[HEAD]","Abe","Bart","Carol","Dawn","Abel","The Abercrombie Guy","The Bad Guy","The Abercrombie Guy's friend","[TAIL]");
		// myLinkedList.head = new Node<String>("[HEAD]");
		// myLinkedList.head
		// 	.setNext(new Node<String>("Abe"))
		// 	.setNext(new Node<String>("Bart"))
		// 	.setNext(new Node<String>("Carol"))
		// 	.setNext(new Node<String>("Dawn"))
		// 	.setNext(new Node<String>("Abel"))
		// 	.setNext(new Node<String>("The Abercrombie Guy"))
		// 	.setNext(new Node<String>("The Bad Guy"))
		// 	.setNext(new Node<String>("The Abercrombie Guy's friend"))
		// 	.setNext(new Node<String>("[TAIL]"))
		// 	;


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
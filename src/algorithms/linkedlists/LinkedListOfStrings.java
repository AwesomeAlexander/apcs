package algorithms.linkedlists;

public class LinkedListOfStrings {
	// What is our underlying data structure...not a Node[]!  A linked list!
	// Well, what does a LinkedList need? 
	Node<String> head;
	int nodeCount = 0;

	// iteratively traverse the Linked List, printing out the String + " --> "
	// unless it's the last node, in which case print out the String + " --> null"
	public String toString() {
		String out = "Head: ";
		for (Node<String> runner = this.head; runner != null ;out+=runner.value+" -> ",runner = runner.getNext()) ;
		return out+="null";
	}
	
	// iteratively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int countNodesWithString(String str) {
		int count = 0;
		for (Node<String> runner = this.head; runner != null ;runner = runner.getNext()) if (runner.value.equals(str)) count++;
		return count;
	}
	
	// recursively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int recursivelyCountNodesWithString(Node<String> head, String str) {
		if (head == null) return 0;
		return (head.value.equals(str) ? 1 : 0) + recursivelyCountNodesWithString(head.getNext());
	}
	
	// Prints the nodes in reverse, iteratively
	public void printReversed(Node<String> head) {


	}
	
	// Prints the nodes in reverse, recursively
	public void recursivelyPrintReversed(Node<String> head) {
		if (head == null) return;
		recursivelyPrintReversed(head.getNext());
		System.out.println(head.value);
	}
	
	
	
	//********* Some of the LinkedList operations specified by Interface List ********
	// (not all of them, that's why we aren't implementing the actual interface)
	
	
	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call. 
	// (Returns false if this collection does not permit duplicates and already contains the specified element.) 
	public boolean add(Node<String> n) {
		return false;
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public void add(int index, Node<String> n) {
		
	}
	
	// Removes all of the elements from this list.
	public void clear() {
		
	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(Node<String> n) {
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> get(int index) {
		return null;
	}
	
	// Removes the element at the specified position in this list.
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> remove(int index) {
		return null;
	}
	
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	public boolean remove(Node n) {
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node set(int index, Node n) {
		return null;
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		return nodeCount;
	}
	

	
}
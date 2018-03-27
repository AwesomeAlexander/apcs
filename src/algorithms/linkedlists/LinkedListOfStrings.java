package algorithms.linkedlists;

import utils.Node;

public class LinkedListOfStrings {

	Node<String> head;
	int nodeCount;

	public LinkedListOfStrings(String...nodes) {
		this.resetTo(nodes);
	}

	public resetTo(String[] nodes) {
		if (nodes.length < 1) return;
		Node<String> runner = this.head = nodes[0];
		for (nodeCount=0;nodeCount<nodes.length;nodeCount++) runner = runner.setNext(new Node<String>(nodes[nodeCount]));
		nodeCount--; // TODO: test
	}

	@Override
	public String toString() {
		String out = "LinkedList: ";
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
		return (head.value.equals(str) ? 1 : 0) + recursivelyCountNodesWithString(head.getNext(),str);
	}
	
	// Prints the nodes in reverse, iteratively
	public void printReversed(Node<String> head) {
		Node<String> runner,rev = null,temp = null;

		// Constructing Reverse
		for (runner = this.head; runner != null ;runner = runner.getNext()) {
			temp = new Node<String>(runner.value);
			temp.setNext(rev);
			rev = temp;
		}

		// Printing reverse
		for (runner = rev; runner != null ;runner = runner.getNext()) System.out.println(runner.value);
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
		Node<String> runner;
		for (runner = rev; runner.getNext() != null ;runner = runner.getNext()) if (n.value.equals(runner.value)) return false;
		runner.setNext(n);
		return true;
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public void add(int index, Node<String> n) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index >= this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		for (Node<String> runner = this.head,int i=0; runner.getNext() != null ;runner = runner.getNext(),i++) if (i==index) {
			n.setNext(runner.getNext());
			runner.setNext(n);
			return;
		}
	}
	
	// Removes all of the elements from this list.
	public void clear() {
		this.head = null;
	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(Node<String> n) {
		for (Node<String> runner = this.head; runner.getNext() != null ;runner = runner.getNext()) if (n.value.equals(runner.value)) return true;
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> get(int index) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index >= this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		for (Node<String> runner = this.head,int i=0; runner.getNext() != null ;runner = runner.getNext(),i++) if (i==index) return runner;
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
	public boolean remove(Node<String> n) {
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node set(int index, Node<String> n) {
		return null;
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		return nodeCount;
	}
	

	
}
package algorithms.linkedlists;

import utils.Node;

/**
 * @author Alexander Ng
 */

public class LinkedListOfStrings {

	Node<String> head;
	int nodeCount;


	public LinkedListOfStrings(String...nodes) {
		this.resetTo(nodes);
	}

	public void resetTo(String[] nodes) {
		if (nodes.length < 1) return;
		Node<String> runner = this.head = new Node<String>(nodes[0]);
		for (nodeCount=1;nodeCount<nodes.length;nodeCount++) runner = runner.setNext(new Node<String>(nodes[nodeCount]));
	}

	@Override
	public String toString() {
		String out = "LinkedList: ";
		for (Node<String> runner = this.head; runner != null ;runner = runner.getNext()) out+=runner.value+" -> ";
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
		for (runner = head; runner != null ;runner = runner.getNext()) {
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
		Node<String> runner = this.head;
		if (runner == null) {this.head = n;nodeCount = 1;return true;}
		for (; runner.getNext() != null ;runner = runner.getNext()) if (n.value.equals(runner.value)) return false;
		runner.setNext(n);
		nodeCount++;
		return true;
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public void add(int index, Node<String> n) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index > this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		if (index == 0) {n.setNext(head);this.head = n;nodeCount++;return;}
		// if (index == this.size()) for (Node<String> runner=this.head;runner!=null;runner=runner.getNext()) if (runner.getNext()==null) {runner.setNext(n);nodeCount++;return;}
		Node<String> runner = this.head;
		for (int i=0; runner != null ;runner = runner.getNext(),i++) if (i==index-1) {
			n.setNext(runner.getNext());
			runner.setNext(n);
			nodeCount++;
			return;
		}
	}
	
	// Removes all of the elements from this list.
	public void clear() {
		this.head = null;
		nodeCount = 0;
	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(Node<String> n) {
		for (Node<String> runner = this.head; runner != null ;runner = runner.getNext()) if (n.value.equals(runner.value)) return true;
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> get(int index) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index >= this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		Node<String> runner = this.head;
		for (int i=0; runner != null ;runner = runner.getNext(),i++) if (i==index) return runner;
		return null;
	}
	
	// Removes the element at the specified position in this list.
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> remove(int index) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index >= this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		Node<String> runner = this.head,back = runner;
		if (index == 0) {this.head = this.head.getNext();nodeCount--;return runner;}
		for (int i=0; i != index ;runner = (back=runner).getNext(),i++) ;
		back.setNext(runner.getNext());
		nodeCount--;
		return runner;
	}
	
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	public boolean remove(Node<String> n) {
		if (n.value.equals(this.head.value)) {this.head = this.head.getNext();nodeCount--;return true;} 
		for (Node<String> runner = this.head, prev = runner; runner != null ;prev = runner, runner = runner.getNext())
			if (n.value.equals(runner.value)) {
				prev.setNext(runner.getNext());
				nodeCount--;
				return true;
			}
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node<String> set(int index, Node<String> n) {
		if (index < 0) throw new IndexOutOfBoundsException("Index must be positive.");
		if (index >= this.size()) throw new IndexOutOfBoundsException("Index is greater than list length.");
		Node<String> runner = this.head, prev = runner;
		if (index == 0) {(this.head = n).setNext(runner.getNext()); return runner;}
		for (int i=0; runner.getNext() != null; i++) {
			if (i==index) {
				prev.setNext(n);
				n.setNext(runner.getNext());
				return runner;
			} else {
				prev = runner;
				runner = runner.getNext(); 
			}
		}
		return null;
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		return nodeCount;
	}	
}
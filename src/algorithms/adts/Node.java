package algorithms.adts;

/**
 * Node
 */
public class Node<T> {
	T value;
	private Node<T> next;

	public Node(T value) {
		this.value = value;
	}

	public Node<T> setNext(Node<T> next) {
		return this.next = next;
	}

	public Node<T> getNext() {
		return this.next;
	}
}
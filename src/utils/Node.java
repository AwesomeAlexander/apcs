package utils;

/**
 * Node
 */
public class Node<T> {
	public T value;
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
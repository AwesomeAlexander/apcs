package utils;

/**
 * Node
 */
public class Node<E> {
	E value;
	private Node<E> next;

	public Node(E value) {
		this.value = value;
	}

	public Node<E> setNext(Node<E> next) {
		return this.next = next;
	}

	public Node<E> getNext() {
		return this.next;
	}
}
package algorithms;

import utils.Node;

/**
 * SelfQueue
 */
public class SelfQueue<E> implements Queue<E> {
	Node<E> tail,head;

	public SelfQueue() {
		this.head = this.tail = null;
	}

	void enqueue(E e) {
		if (this.tail == null) this.head = this.tail = new Node<E>(e);
		else this.tail = this.tail.setNext(new Node<E>(e));
	}

	E dequeue() {
		if (this.head = null || this.head.getNext() == null) return null;
		return this.head = this.head.getNext();
	}

	E peek() {
		if (this.head = null || this.head.getNext() == null) return null;
		return this.head;
	}
}
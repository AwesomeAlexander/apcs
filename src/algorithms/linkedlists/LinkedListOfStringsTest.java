package algorithms.linkedlists;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class LinkedListOfStringsTest {
	
	LinkedListOfStrings testLinkedList;
	
	@Before
	public void setUp() throws Exception {
		testLinkedList = new LinkedListOfStrings();
		addNodes();
	}

	public addNodes() {
		testLinkedList.resetTo(new String[] {"A0","A1","A2","A3","A4","A5","A6","A7","A8","A9"});
	}
	
	public void clearNodes() {
		testLinkedList.head = null;
		testLinkedList.nodeCount = 0;
	}
	
	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call. 
	// (Returns false if this collection does not permit duplicates and already contains the specified element.) 
	// ***Basically always return true is fine -- we will allow duplicates!
	@Test
	public void testAddToEndOfList() {
		//	***Add to end of EMPTY list: test size, contents, and next is null
		clearNodes();
		assertTrue("add() should return true", testLinkedList.add(new Node("Aaron")));
		assertEquals("Size is incorrect after adding a Node to empty list", 1, testLinkedList.size());
		assertEquals("Node not saved properly after adding a Node to empty list", "Aaron", testLinkedList.head.name);
		assertEquals("Pointer to next should be null after adding a Node to empty list", null, testLinkedList.head.next);
		
		// ***Add to end of POPULATED list: test size, contents, and next is null
		addNodes(); // also clears previous Nodes
		testLinkedList.add(new Node("A10"));
		assertEquals("Size is incorrect when adding a Node to end of populated list", 11, testLinkedList.size());
		Node runner = testLinkedList.head;
		try {
			for(int i = 0; i < 11; i++, runner = runner.next) // test the contents of A0-A9, plus newly added A10
				assertEquals("Node " + i + " was altered somehow after adding a Node to the end of populated list of length 10", "A" + i, runner.name);
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after adding a Node to the end of a populated list");
		}
		// *** Test that the next pointer is null for A10..since we traversed A0-A10, runner points to A10's next (should be null)
		assertEquals("The newly-added Node's next pointer should be null after adding a Node to end of populated list", null, runner);		
	}

	
	// Inserts the specified element at the specified position in this list.
	// Shifts the element currently at that position (if any) and any subsequent elements to the right
	// Throws IndexOutOfBoundsException - if the index is out of range 
	@Test
	public void testAddToSpecifiedIndex() { 
		// ***Test that adding an index too high or too low throws an Exception
		try {
			testLinkedList.add(11, new Node("Out-Of-Bounds Carol"));
			fail("add: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed test
		try {
			testLinkedList.add(-1, new Node("Out-Of-Bounds Carlos"));
			fail("add: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed test
		
		// ***Test adding to FRONT: check size, traverse list make sure all elements present
		addNodes();
		testLinkedList.add(0, new Node("Frank in the front"));
		assertEquals("Size is incorrect after adding a Node to the front of a populated list", 11, testLinkedList.size());
		Node runner = testLinkedList.head;
		assertEquals("New Node not added properly to the front of populated list", "Frank in the front", runner.name);
		try {
			runner = runner.next;
			for(int i = 0; i < 10; i++, runner = runner.next) { // test the contents of A0-A9 after confirming Frank in the Front was added
				assertEquals("Node " + i + " was altered somehow after adding a Node to the front of populated list of length 10", "A" + i, runner.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after adding a Node to the front of a populated list");
		}

		// ***Test adding to the MIDDLE: check size, traverse list make sure all elements present
		addNodes();
		testLinkedList.add(4, new Node("Mary in the Middle"));
		assertEquals("Size is incorrect after adding a Node to the middle of a populated list", 11, testLinkedList.size());
		Node runner2 = testLinkedList.head;
		try {
			for(int i = 0; i < 4; i++, runner2 = runner2.next) { // test the contents of A0-A3 before checking Mary in the Middle
				assertEquals("Node " + i + " was altered somehow after adding a Node to the middle of a populated list of length 10", "A" + i, runner2.name);
			} 
			assertEquals("New Node not added properly to the middle of a populated list", "Mary in the Middle", runner2.name);
			runner2 = runner2.next;
			for(int i = 5; i < 10; i++, runner2 = runner2.next) { // test the contents of A4-A9 after checking Mary in the Middle (since Mary was inserted, A4 is in 5, A5 is in 6 etc, so -1)
				assertEquals("Node " + i + " was altered somehow after adding a Node to the middle of a populated list of length 10", "A" + (i-1), runner2.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after adding a Node to the middle of a populated list");
		}
		
		// ***Test adding to the END: check size, traverse list make sure all elements present
		addNodes();
		try { // with size = 10, still should be able to add to index 10, b/c unlike 'get' and 'set' this is 'add' -- like adding to the end of the list
			testLinkedList.add(10, new Node("Erin at the End")); 
		}
		catch(IndexOutOfBoundsException e) {
			fail("IndexOutOfBoundsException while attempting to add a Node to the end of a populated list");
		}
		assertEquals("Size is incorrect after adding a Node to the end of a populated list", 11, testLinkedList.size());
		Node runner3 = testLinkedList.head;
		try {
			for(int i = 0; i < 10; i++, runner3 = runner3.next) { // test the contents of A0-A9 before checking Erin at the End
				assertEquals("Node " + i + " was altered somehow after adding a Node to the end of a populated list of length 10", "A" + i, runner3.name);
			} 
			assertEquals("New Node not added properly to the end of a populated list", "Erin at the End", runner3.name);
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after adding a Node to the end of a populated list");
		}
		assertNull("New Node added to the end of a populated list should point to null", runner3.next);

	}
	
	// Removes all of the elements from this list.
	@Test
	public void testClear() {
		testLinkedList.clear();
		assertNull("Clear didn't set head to null", testLinkedList.head);
		assertEquals("Clear doesn't reset size", 0 , testLinkedList.nodeCount);
	}

	// Returns true if this list contains the specified element.
	@Test
	public void testContains() {
		// *** Test valid index at beginning, middle, end of list.  Test newly added node.  
		// *** Test invalid index (only matches part, or doesn't match at all)
		assertTrue("Contains should return true when the String name is present in the list", testLinkedList.contains(new Node("A0")));
		assertTrue("Contains should return true when the String name is present in the list", testLinkedList.contains(new Node("A4")));
		assertTrue("Contains should return true when the String name is present in the list", testLinkedList.contains(new Node("A9")));
		testLinkedList.add(new Node("A10"));
		assertTrue("Contains should return true when the String name is present in the list", testLinkedList.contains(new Node("A10")));
		assertFalse("Contains should only return true for perfect String matches", testLinkedList.contains(new Node("A88")));
		assertFalse("Contains shouldn't return true if the String name isn't present in the list", testLinkedList.contains(new Node("B5")));
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range 
	@Test
	public void testGet() {
		// *** Test that getting an index too high or too low throws an Exception
		try {
			testLinkedList.get(10);
			fail("get: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed 
		try {
			testLinkedList.get(-1);
			fail("get: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed
		
		// *** Test getting Valid index
		assertEquals("Getting a node didn't return the node properly!", "A0",testLinkedList.get(0).name);
		assertEquals("Getting a node didn't return the node properly!", "A9", testLinkedList.get(9).name);
		testLinkedList.add(new Node("A10"));
		assertEquals("Getting an node after adding to the end of the list didn't return the node properly!", "A10", testLinkedList.get(10).name);		
	}
	
	
	// Removes the element at the specified position in this list.
	// Shifts any subsequent elements to the left (subtracts one from their indices)
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range 
	@Test
	public void testRemoveWithIndex() {
		// *** Test that removing an index too high or too low throws an Exception
		try {
			testLinkedList.remove(10);
			fail("remove: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed 
		try {
			testLinkedList.remove(-1);
			fail("remove: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed

		// ***Test removing from the FRONT: check size, check removed Node returned, traverse list make sure all elements present
		addNodes();
		Node returnedNode = testLinkedList.remove(0);
		assertEquals("Remove should return the removed node", "A0", returnedNode.name);
		testHelperRemoveNodeFromFront();
		
		// ***Test removing from the MIDDLE: check size, check removed Node returned, traverse list make sure all elements present
		addNodes();
		returnedNode = testLinkedList.remove(5);
		assertEquals("Remove should return the removed node", "A5",returnedNode.name );
		testHelperRemoveNodeFromMiddle();
		
		// ***Test removing from the END: check size, check removed Node returned, traverse list make sure all elements present
		addNodes();
		returnedNode = testLinkedList.remove(9);
		assertEquals("Remove should return the removed node", "A9",returnedNode.name );
		testHelperRemoveNodeFromEnd();
	}
	

	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Shifts any subsequent elements to the left (subtracts one from their indices)
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	@Test
	public void testRemoveNode() {
		// ***Test removing valid nodes from front, middle, end
		addNodes();
		assertTrue(testLinkedList.remove(new Node("A0")));
		testHelperRemoveNodeFromFront();
		
		addNodes();
		assertTrue(testLinkedList.remove(new Node("A5")));
		testHelperRemoveNodeFromMiddle();
		
		addNodes();
		assertTrue(testLinkedList.remove(new Node("A9")));
		testHelperRemoveNodeFromEnd();
		
		// ***Test removing invalid node -- check returned false, check size, check Nodes unchanged
		addNodes();
		assertFalse(testLinkedList.remove(new Node("A11")));
		testHelperRemoveInvalidNode();
	}
	
	public void testHelperRemoveNodeFromFront() {
		assertEquals("Size is incorrect after removing a Node from the front of a populated list", 9, testLinkedList.size());
		Node runner = testLinkedList.head;
		assertEquals("Attempting to remove node from front of populated list...new head is incorrect", "A1", runner.name);
		try {
			for(int i = 1; i < 10; i++, runner = runner.next) { // test the contents of A0-A9 after confirming Frank in the Front was added
				assertEquals("Node " + i + " was altered somehow after removing a node from the front of populated list of length 10", "A" + i, runner.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after removing a node from the front of a populated list");
		}
	}
	
	public void testHelperRemoveNodeFromMiddle() {
		assertEquals("Size is incorrect after removing a Node from the middle of a populated list", 9, testLinkedList.size());
		Node runner2 = testLinkedList.head;
		try {
			for(int i = 0; i < 5; i++, runner2 = runner2.next) { // test the contents of A0-A4 after removing A5
				assertEquals("Node " + i + " was altered somehow after removing a node from the middle of populated list of length 10", "A" + i, runner2.name);
			} 
			for(int i = 5; i < 9; i++, runner2 = runner2.next) { // test the contents of A6 (index 5) - A9 (index 8) after removing A5
				assertEquals("Node " + i + " was altered somehow after removing a node from the middle of populated list of length 10", "A" + (i+1), runner2.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after removing a node from the middle of a populated list");
		}
	}
	
	public void testHelperRemoveNodeFromEnd() {
		Node runner3 = testLinkedList.head;
		assertEquals("Size is incorrect after removing a Node from the end of a populated list", 9, testLinkedList.size());
		try {
			for(int i = 0; i < 9; i++, runner3 = runner3.next) { // test the contents of A0-A8 after removing A9
				assertEquals("Node " + i + " was altered somehow after removing a node from the end of populated list of length 10", "A" + i, runner3.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after removing a node from the end of a populated list");
		}
		assertNull("After removing node from the end of a populated list, the new last node should point to null", runner3);
	}
	
	public void testHelperRemoveInvalidNode() {
		assertEquals("Size is incorrect after attempting to remove an invalid Node -- size shouldn't change", 10, testLinkedList.size());
		Node runner = testLinkedList.head;
		try {
			for(int i = 0; i < 10; i++, runner = runner.next) { // test the contents of A0-A9 after failing to remove a node
				assertEquals("Node " + i + " was altered somehow after attempting to remove an invalid node", "A" + i, runner.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after removing a node from the front of a populated list");
		}
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Returns the element previously at the specified position 
	// Throws IndexOutOfBoundsException - if the index is out of range
	@Test
	public void testSet() {
		try {
			testLinkedList.set(10, new Node("Blah"));
			fail("set: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed 
		try {
			testLinkedList.set(-1, new Node("Blah"));
			fail("set: Invalid index should have triggered IndexOutOfBoundsException");
		} 	
		catch (IndexOutOfBoundsException e) {}//passed
		
		// *** Test new item set in front of list, test old item returned.  test list same, except for replacement
		addNodes();
		Node returnedNode = testLinkedList.set(0, new Node("B0"));
		assertEquals("Set should return the node that was previously there", "A0", returnedNode.name );
		Node runner = testLinkedList.head;
		try {
			for(int i = 0; i < 10; i++, runner = runner.next) { // test the contents of **B0**,A1,A2,A3..A9 after setting B0
				if(i == 0)
					assertEquals("Node " + i + " was altered somehow after attempting to setting a node", "B" + i, runner.name);
				else
					assertEquals("Node " + i + " was altered somehow after attempting to setting a node", "A" + i, runner.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after setting a node");
		}	
		
		// *** Test new item set in middle of list, test old item returned.  test list same, except for replacement
		addNodes();
		returnedNode = testLinkedList.set(3, new Node("B3"));
		assertEquals("Set should return the node that was previously there", "A3", returnedNode.name );
		runner = testLinkedList.head;
		try {
			for(int i = 0; i < 10; i++, runner = runner.next) { // test the contents of A0,A1,A2,**B3**,A4,A5..A9 after setting B3
				if(i == 3)
					assertEquals("Node " + i + " was altered somehow after attempting to setting a node", "B" + i, runner.name);
				else
					assertEquals("Node " + i + " was altered somehow after attempting to setting a node", "A" + i, runner.name);
			} 
		}
		catch(NullPointerException e) {
			fail("NullPointerException while attempting to traverse the LinkedList after setting a node");
		}	
	}
}

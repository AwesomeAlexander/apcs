package algorithms.chap9webcat2;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;
import utils.BinaryTree;


public class PersonDatabase extends BinaryTree<Person> {

	/**
	 * This TreeNode<Person> is the root of a tree of Person
	 * objects that is sorted by last
	 * name and then first name (ignoring case).
	 * This tree allows duplicate names (as long as
	 * the birth dates are different).
	 */
	private TreeNode<Person> rootOfNameTree;
	
	/**
	 * This TreeNode<Person> is the root of a tree 
	 * of Person objects that is sorted by birth
	 * date. This tree allows duplicate 
	 * birth dates (as long as the names are
	 * different).
	 */
	private TreeNode<Person> rootOfBirthDateTree;

	/**
	 * The number of nodes in the tree.
	 * Both trees should have the same
	 * number of nodes.
	 */
	private int size;
	
	
	/**
	 * Returns number of Persons in
	 * the database
	 * @return number of Persons
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add person to the database unless 
	 * a Person object that is equal already
	 * exists. If a TreeNode<Person> is created, it 
	 * should be added to both the name tree
	 * and the birth date tree.
	 * @param p  a person
	 * @return true if person is added, false otherwise
	 */
	public boolean put(Person p) {
		//hint: create two private put methods
		// for each of the trees and call
		// them here
	}

	/**
	 * Returns a list of all Person objects in the database with the given name.
	 * This method should search in name tree.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(String firstName, String lastName) {

	}

	/**
	 * Returns a list of all Person objects in the database with the given birth
	 * date. This method should search in the birth date tree.
	 * 
	 * @param birthDay
	 * @param birthMonth
	 * @param birthYear
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(int birthDay, int birthMonth, int birthYear) {

	}
	
	
	//***** For testing purposes
	public TreeNode<Person> getNameRoot() {
		return rootOfNameTree;
	}
	
	public TreeNode<Person> getBDayRoot() {
		return rootOfBirthDateTree;
	}
	

}
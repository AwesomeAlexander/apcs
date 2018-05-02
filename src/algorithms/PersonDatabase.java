package algorithms;

import java.util.ArrayList;
import java.util.List;

import utils.BinaryTree;

/**
 * @author Alexander Ng
 */
public class PersonDatabase {

	// Main for testing purposes
	public static void main(String[] args) {
		PersonDatabase pdb = new PersonDatabase();
		
		// Placing Objects - You can edit these to vary test results
		pdb.put(new Person("F1","L1",1,1,2000));
		pdb.put(new Person("F1","L2",1,3,2000));
		pdb.put(new Person("F1","L3",1,1,2000));

		pdb.put(new Person("F2","L2",1,1,2000));
		pdb.put(new Person("F2","L2",1,2,2000));
		pdb.put(new Person("F2","L2",1,3,2000));
		pdb.put(new Person("F2","L2",1,4,2000));

		pdb.put(new Person("F3","L1",1,1,2000));
		pdb.put(new Person("F3","L2",1,1,2000));

		pdb.put(new Person("F3","L1",1,4,2000));

		pdb.put(new Person("F4","L1",1,1,2000));
		pdb.put(new Person("F4","L1",1,5,2000));
		
		// Testing duplicates - should not put this one
		// Should print 'false', indicating it isn't added
		System.out.println(pdb.put(new Person("F2","L2",1,1,2000)));

		// Prints results of <PersonDatabase>.find()
		System.out.println(pdb.find(1,1,2000)); // Should yield 6 elements
		System.out.println(pdb.find("F2","L2")); // Should yield 4 elements
	}

	

	/**
	 * This TreeNode<Person> is the root of a tree of Person
	 * objects that is sorted by last
	 * name and then first name (ignoring case).
	 * This tree allows duplicate names (as long as
	 * the birth dates are different).
	 */
	// private TreeNode<Person> rootOfNameTree;
	BinaryTree<Person> nameTree = new BinaryTree<Person>();
	
	/**
	 * This TreeNode<Person> is the root of a tree 
	 * of Person objects that is sorted by birth
	 * date. This tree allows duplicate 
	 * birth dates (as long as the names are
	 * different).
	 */
	// private TreeNode<Person> rootOfBirthDateTree;
	BinaryTree<Person> dateTree = new BinaryTree<Person>();

	/**
	 * The number of nodes in the tree.
	 * Both trees should have the same
	 * number of nodes.
	 */
	// private int size;
	
	
	/**
	 * Returns number of Persons in
	 * the database
	 * @return number of Persons
	 */
	public int size() {
		if (nameTree.size()!=dateTree.size()) System.out.println("YOU DUN DID A BAD!!!");
		return nameTree.size();
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
		return nameTree.insert(p,Person.compareByName,false)
			&& dateTree.insert(p,Person.compareByBirth,false);
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
		return nameTree.getListOf(new ArrayList<Person>(), (Person p) -> {
			return p.firstName.equals(firstName) && p.lastName.equals(lastName);
		});
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
		return dateTree.getListOf(new ArrayList<Person>(), (Person p) -> {
			return p.birthDay==birthDay && p.birthMonth==birthMonth && p.birthYear==birthYear;
		});
	}

}
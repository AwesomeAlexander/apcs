package algorithms;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PersonDatabaseTest {
	
	private static Person[] personArray;
	private static int size = 40000;
	
	@BeforeClass
	public static void setup() {
		personArray = new Person[size];
		personArray[0] = new Person("John", "Smith", 29, 3, 1980);
		personArray[1] = new Person("John", "Smith", 30, 4, 2001);
		personArray[2] = new Person("Jin", "Xiolang", 30, 11, 1571);
		personArray[3] = new Person("Banni","Singh", 30, 11, 1571);
		personArray[4] = new Person("Jin", "Xiolang", 30, 4, 2001);
		personArray[5] = new Person("Azalea", "Malloy", 30, 4, 2001);
		personArray[6] = new Person("Rene", "Salvry", 30, 4, 2001);
		personArray[7] = new Person("Jane", "Brimmer", 30, 4, 2001);
		personArray[8] = new Person("John", "Smith", 30, 4, 2000);
		personArray[9] = new Person("John", "Smith", 30, 3, 2001);
		personArray[10] = new Person("John", "Smith", 29, 4, 2001);

		for (int i = 11; i < size; i++) {
			int birthYear = 1500 + ((i / 28) / 12);
			int birthMonth = 1 + (i / 28) % 12;
			int birthDay = 1 + i % 28;
			personArray[i] = new Person("F" + (i % 1000), "L" + i, birthDay, birthMonth, birthYear);
			
		}
		
		//shuffle so that binary search tree is fast
		for (int i = 11; i < size; i++) {
			int index = (int)(Math.random() * (size - 11)) + 11;
			Person temp = personArray[i];
			personArray[i] = personArray[index];
			personArray[index] = temp;
		}
	}
	
	@Test
	public void testPutNoDuplicatesAllowed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 12; i++) {
			db.put(personArray[i]);
		}
		
		assertFalse(db.put(personArray[4]));
		assertFalse(db.put(personArray[3]));
		assertFalse(db.put(personArray[7]));
		if (db.size() < 12) {
			fail("The database didn't keep update size properly");
		}
		if (db.size() > 12) {
			fail("The database allowed a duplicate person to be entered");
		}
	}
	
	@Test
	public void testPutFirst11NameTreeNode<Person>sCorrect() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 11; i++) {
			db.put(personArray[i]);
		}
		
		if (db.size() != 11) {
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
		
		TreeNode<Person> root = db.getNameRoot();
		// Check root properly set
		assertEquals(root.item.lastName, "Smith");
		assertEquals(root.item.birthYear, 1980);
		
		// Check right side of tree
		TreeNode<Person> jin = root.right.left; 
		assertEquals(jin.item.lastName, "Xiolang");
		assertEquals(jin.item.birthYear, 2001);
		
		// Check left side of tree
		TreeNode<Person> rene = root.left.left.left.right;
		assertEquals(rene.item.lastName, "Salvry");
		
		TreeNode<Person> john = root.left.left.right;
		assertEquals(john.item.lastName, "Smith");
		assertEquals(john.item.birthYear, 2000);
		
	}
	
	@Test
	public void testPutFirst11BirthdayTreeNode<Person>sCorrect() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 11; i++) {
			db.put(personArray[i]);
		}
		
		if (db.size() != 11) {
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
		
		TreeNode<Person> root = db.getBDayRoot();
		// Check root properly set
		assertEquals(root.item.birthYear, 1980);
		assertEquals(root.item.lastName, "Smith");
		
		// Check right side of tree
		TreeNode<Person> jane = root.right.left.left.left.left; 
		assertEquals(jane.item.birthYear, 2001);
		assertEquals(jane.item.lastName, "Brimmer");
		
		// Check left side of tree
		TreeNode<Person> john = root.right.left.left.left.left.left.right.right;
		assertEquals(john.item.birthYear, 2001);
		assertEquals(john.item.birthDay, 29);
		assertEquals(john.item.lastName, "Smith");
	}
	
	@Test
	public void testPutTimeAndSizeCorrect() {
		long startTime = System.currentTimeMillis();
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Put time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to put everyone in the database." 
					+ " It needs to take less than 1 second");
		}		
		if (db.size() != size) {
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
	}
	
	@Test
	public void testFindWithNameRetrievesAllJohnSmiths() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
	
		List<Person> list = db.find("John", "Smith");
		Integer[] indicesOfJohnSmith = {0, 1, 8, 9, 10};
		for (Integer index : indicesOfJohnSmith) {
			boolean contains = false;
			for (Person p : list) {
				if (p == personArray[index]) {
					contains = true;
					break;
				}
			}
			if ( ! contains) {
				fail("Find by name didn't find all of the people" 
						+ " with the name John Smith");
			}
		}
		
		
		list = db.find("Marilyn", "Monroe");
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by name who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByName = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.firstName, p.lastName);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByName = false;
					break;
				}
			}
		}
		if (! canFindAllByName) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by name method.");
		}
	}

	@Test
	public void testFindWithNameSpeed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person p = personArray[i];
			db.find(p.firstName, p.lastName);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Find by name time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to find everyone by name." 
					+ " It needs to take less than 1 second");
		}		
	}
	
	@Test
	public void testFindWithNameCanFindEveryPerson() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		
		List<Person> list = db.find("Marilyn", "Monroe");
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by name who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByName = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.firstName, p.lastName);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByName = false;
					break;
				}
			}
		}
		if (! canFindAllByName) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by name method.");
		}	
	}
	
	@Test
	public void testFindWithDateRetrievesAll2001_4_30() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		
		List<Person> list = db.find(30, 4, 2001);
		Integer[] indicesOf30042001 = {1, 4, 5, 6, 7};
		for (Integer index : indicesOf30042001) {
			boolean contains = false;
			for (Person p : list) {
				if (p == personArray[index]) {
					contains = true;
					break;
				}
			}
			if ( ! contains) {
				fail("Find by date didn't find all of the people" 
						+ " with the birthday 30/4/2001");
			}
		}
	}
	
	@Test
	public void testFindWithDateSpeed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person p = personArray[i];
			db.find(p.birthDay, p.birthMonth, p.birthYear);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Find by date time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to find everyone by birthdate." 
					+ " It needs to take less than 1 second");
		}
	}
	
	@Test
	public void testFindWithDateCanFindEveryPerson() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
	
		List<Person> list = db.find(5, 5, 1400);
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by birthdate who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByDate = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.birthDay, p.birthMonth, p.birthYear);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByDate = false;
					break;
				}
			}
		}
		if (! canFindAllByDate) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by birthdate method.");
		}	
	}
}

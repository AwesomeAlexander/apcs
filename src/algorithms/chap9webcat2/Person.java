package algorithms.chap9webcat2;

import java.lang.Comparable; // is dis right?
import utils.CompareTwo;

public class Person implements Comparable<Person> {
	
	public String firstName;
	public String lastName;
	public int birthDay;
	public int birthMonth;
	public int birthYear;
	public Person father;
	public Person mother;

	
	public Person(String firstName, String lastName, 
			int birthDay, int birthMonth, int birthYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
	}
	
	@Override
	public int compareTo(Person o) {
		if (!this.firstName.equals(o.firstName)) return this.firstName.compareTo(o.firstName);
		if (!this.lastName.equals(o.lastName)) return this.lastName.compareTo(o.lastName);
		if (this.birthDay!=o.birthDay) return this.birthDay-o.birthDay;
		if (this.birthMonth!=o.birthMonth) return this.birthMonth-o.birthMonth;
		if (this.birthYear!=o.birthYear) return this.birthYear-o.birthYear;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if ( ! (obj instanceof Person))
			return false;
		else {
			Person p = (Person) obj;
			return (this.firstName.equals(p.firstName)
					&& this.lastName.equals(p.lastName)
					&& this.birthDay == p.birthDay
					&& this.birthMonth == p.birthMonth
					&& this.birthYear == p.birthYear);
		}
	}
	
	public String toString() {
		return lastName + ", " + firstName + " : "
				+ birthDay + "/" + birthMonth + "/" + birthYear;
	}
	
	public static CompareTwo<Person> compareByName = new CompareTwo<Person>() {
		public int compare(Person a,Person b) {
			// Compare based on Name, sorting secondarily by birth
			if (!a.lastName.equals(b.lastName)) return a.lastName.compareTo(b.lastName);
			if (!a.firstName.equals(b.firstName)) return a.firstName.compareTo(b.firstName);
			if (a.birthYear!=b.birthYear) return a.birthYear-b.birthYear;
			if (a.birthMonth!=b.birthMonth) return a.birthMonth-b.birthMonth;
			if (a.birthDay!=b.birthDay) return a.birthDay-b.birthDay;
			return 0;

			// Just sorting by Names, not considering birth dates
			// return (a.lastName+a.firstName).compareTo(b.lastName+b.lastName);
		}
	};

	public static CompareTwo<Person> compareByBirth = new CompareTwo<Person>() {
		public int compare(Person a,Person b) {
			// Compare based on Birth, sorting secondarily by name
			if (a.birthYear!=b.birthYear) return a.birthYear-b.birthYear;
			if (a.birthMonth!=b.birthMonth) return a.birthMonth-b.birthMonth;
			if (a.birthDay!=b.birthDay) return a.birthDay-b.birthDay;
			if (!a.lastName.equals(b.lastName)) return a.lastName.compareTo(b.lastName);
			if (!a.firstName.equals(b.firstName)) return a.firstName.compareTo(b.firstName);
			return 0;

			// Just sorting by Dates, not considering names
			// jk idk how
		}
	};

}
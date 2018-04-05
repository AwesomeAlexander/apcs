package algorithms.chap9webcat2;

import java.lang.Comparable; // is dis right?

public class Person implements Comparable {
	
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
		if (!this.birthDay.equals(o.birthDay)) return this.birthDay.compareTo(o.birthDay);
		if (!this.birthMonth.equals(o.birthMonth)) return this.birthMonth.compareTo(o.birthMonth);
		if (!this.birthYear.equals(o.birthYear)) return this.birthYear.compareTo(o.birthYear);
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
	
}
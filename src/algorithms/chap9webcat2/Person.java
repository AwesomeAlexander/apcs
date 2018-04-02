package algorithms.chap9webcat2;

public class Person {
	
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
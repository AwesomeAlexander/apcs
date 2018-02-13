package algorithms;

import java.util.Arrays;

// This class is for you to test your Sequential Search and Binary Search Implementation
public class SingleSearchTester {

	public static void main(String[] args){
		// You can also define your own test arrays 
		// They will be sorted (although test1 is alrady sorted to help you visualize the correct answer)
		int[] test1 = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] test2 = new int[]{67,35,51,45,40,50,37,55,20};
		int[] test3 = ArrayImporter.readArrayFile("largeArray.txt"); // for testing, after sorting, '1760909555' will be at index 81918
		
		 // ***Enter in your own number to search for and array to search in***
		int numToSearchFor = 1528428838;
		int[] arrayToSearchIn = test3; // arrayToSearchIn will point to the array you choose
		
		
		//  Remember, Binary Search requires a sorted list...
		System.out.print("***Sorting the lists...");
		Arrays.sort(arrayToSearchIn); // sort() doesn't return int[], array simply gets modified in place
		System.out.println("...sorted.\n");
		
		System.out.println("***Searching the list for a single item (Binary Search)***");
		int index = SearchLibrary.binarySearch(numToSearchFor, arrayToSearchIn, true); // for default, should return index '2'
		
		String message = "";
		if (index >= 0) // returns -1 if not found
			message = "Found " + numToSearchFor + " at index " + index;	
		else 
			message += "Couldn't find " + numToSearchFor;
		
		if(arrayToSearchIn.length < 50) // only print out array if it's small...
			message += " in " + java.util.Arrays.toString(arrayToSearchIn);
		else								// otherwise just print out the size
			message += " in the array of length " + arrayToSearchIn.length;
		System.out.println(message);
		System.out.println("\n");
		
		
		System.out.println("***Searching the list for a single item (Sequential Search)***");
		int index2 = SearchLibrary.sequentialSearch(numToSearchFor, arrayToSearchIn, true); // for default, should return index '2'
		String message2 = "";
		if (index2 >= 0)  // returns -1 if not found
			message2 = "Found " + numToSearchFor + " at index " + index2;
		else 
			message2 += "Couldn't find " + numToSearchFor;
		
		if(arrayToSearchIn.length < 50) // only print out array if it's small...
			message2 += " in " + java.util.Arrays.toString(arrayToSearchIn);
		else								// otherwise just print out the size
			message2 += " in the array of length " + arrayToSearchIn.length;
		System.out.println(message2);
		
	}
	
}

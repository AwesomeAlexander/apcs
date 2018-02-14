package algorithms;

import java.util.Arrays;

// This class tests your search algorithms in a more real-world setting, and gives 
// a comparison of search times
public class MultipleSearchTester {
	
	public static void main(String[] args) {
		int[] amazonBookIDs = ArrayImporter.readArrayFile("largeArray.txt");
		int[] booksWeWantToCheck = ArrayImporter.readArrayFile("smallArray.txt");
		
		System.out.println("I've got a list of " + booksWeWantToCheck.length + " book IDs to check");
		System.out.println("We are checking each book ID against our inventory of " + amazonBookIDs.length + " books currently at Amazon.");

		//  Remember, Binary Search requires a sorted list...
		System.out.print("***Sorting the list...");
		Arrays.sort(amazonBookIDs); // sort() doesn't return int[], array simply gets modified in place
		System.out.println("...sorted.");
		
		System.out.println("***Searching the list for multiple items (Binary Search)***");
		long startTime = System.currentTimeMillis();
		int foundCount1 = 0;
		
		for (int j = 0; j < booksWeWantToCheck.length; j++) {
			int index = SearchLibrary.binarySearch(booksWeWantToCheck[j], amazonBookIDs, false);
			if (index >= 0) { // returns -1 if not found
				foundCount1++;
				System.out.println("Found Book ID: " + booksWeWantToCheck[j]
						+ " at index " + index + " of amazonBookIDs");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("******* Search Complete.  Found total of " + foundCount1 + " entries.*******");
		System.out.println("*******Took " + (endTime - startTime) + "ms to check using Binary Search*******");
	
		System.out.println("\n");
		
		System.out.println("***Searching the list for multiple items (Sequential Search)***");
		long startTime2 = System.currentTimeMillis();
		int foundCount2 = 0; 
		for (int j = 0; j < booksWeWantToCheck.length; j++) {
			int index2 = SearchLibrary.sequentialSearch(booksWeWantToCheck[j], amazonBookIDs, false);
			if (index2 >= 0) { // returns -1 if not found
				foundCount2++;
				System.out.println("Found Book ID: " + booksWeWantToCheck[j]
						+ " at index " + index2 + " of amazonBookIDs");
			}
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println("******* Search Complete.  Found total of " + foundCount2 + " entries.*******");
		System.out.println("*******Took " + (endTime2 - startTime2) + "ms to check using Sequential Search*******");
	
	}

}

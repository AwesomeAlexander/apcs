package algorithms;

public class SearchLibrary {

	/*
	 * Search for 'num' in the given list using the Sequential Search algorithm.
	 * If Verbose Mode is 'on', prints out the total number of checks
	 * This is to allow you to see the difference in the number of checks when
	 * doing a Single Search Test. (Multiple Search Test would generate too much output).
	 * Returns -1 if the number isn't found.
	 * If found, return the index the number was found at
	 */
	public static int sequentialSearch(int num, int[] list, boolean verboseModeOn) {
		for (int i=0;i<list.length;i++) if (list[i] == num) {
			if(verboseModeOn) System.out.println("Total number of checks: " + i);
			return i;
		}
		return -1;
	}
	
	/*
	 * Search for 'num' in the given list using the Binary Search algorithm.
	 * If Verbose Mode is 'on', prints out the total number of checks
	 * This is to allow you to see the difference in the number of checks when
	 * doing a Single Search Test. (Multiple Search Test would generate too much output).
	 * Returns -1 if the number isn't found.
	 * If found, return the index the number was found at
	 */
	public static int binarySearch(int num, int[] list, boolean verboseModeOn) {
		return binarySearch(num, list, 0, list.length);
		// Couldn't implement verboseModeOn without making
		// checkCounter class level and static, due to recursion
	}
	private static int binarySearch(int num,int[] list,int min,int max) {
		if (min >= max) return -1;
		int mid = (max+min)/2;
		if (list[mid] == num) return mid;
		return num > list[mid] ? binarySearch(num, list, mid+1, max) : binarySearch(num, list, min, mid);
		// 50, 75, 87, 93
	}
	
	
}

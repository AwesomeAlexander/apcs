
import java.util.Arrays;

public class SortLibrary {

	public static void main(String[] args) {
		// Test arrays you can use to check your sorts.
		// They represent common arrangements: random, already sorted, reversed, mostly sorted
		int[] random = new int[]{33,94,9,40,77,82,47,15,51,64,76,28,2,85,11};
		int[] alreadySorted = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] reversed = new int[]{94,85,82,77,76,64,51,47,40,33,28,15,11,9,2};
		int[] mostlySorted = new int[]{2,85,11,15,28,33,47,40,51,64,76,77,82,9,94};
		int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt"); 
		int[] myCustomTest = new int[]{15, 23, 42, 4, 8, 16, 1};		
		
		// ***Enter your array to sort here
		int[] arrayToSort = random; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		
		// ***Enter which sort you want to test
		bubbleSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
		Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison
		
		if(arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		
	}
	
	// void normally would be OK.  Don't need to return int[] or anything.
	// However, I want you to keep track of and return the number of swaps.
	public static int bubbleSort(int[] nums) {

	}
	
	// void is OK.  'unsorted' simply receives a copy of reference to the unsorted
	// array 'arrayToSort' when method is called.  When your method finishes, 
	// 'arrayToSort' will point to the sorted array
	public static void insertionSort(int[] unsorted) {

	}

	
	
}

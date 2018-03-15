// package algorithms;

import java.util.Arrays;

public class SortLibrary {

	public static void main(String[] args) {
		// Test arrays you can use to check your sorts.
		// They represent common arrangements: random, already sorted, reversed, mostly sorted
		int[] random = new int[]{33,94,9,40,77,82,47,15,51,64,76,28,2,85,11};
		int[] alreadySorted = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] reversed = new int[]{94,85,82,77,76,64,51,47,40,33,28,15,11,9,2};
		int[] mostlySorted = new int[]{2,85,11,15,28,33,47,40,51,64,76,77,82,9,94};
		//int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt"); 
		int[] myCustomTest = new int[]{15, 23, 42, 4, 8, 16, 1};	
		
		
		// ***Enter your array to sort here
		int[] arrayToSort = random; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		
		// ***Enter which sort you want to test
		quickSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
		Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison

		if(arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		
	}
	
	// √
	public static int bubbleSort(int[] nums) {
		int ccount = 0;boolean swappedsome = true; // for efficiency
		for (int i=nums.length;i>=0 && swappedsome;i--) {
			swappedsome = false;
			for (int j=1;j<i;j++) {
				if (nums[j] < nums[j-1]) {
					swappedsome = true;
					swapInArr(nums,j,j-1);
					ccount++;
				}
			}
		}
		return ccount;
	}

	// √
	public static void insertionSort(int[] nums) {
		for (int i=1;i<nums.length;i++) {
			int num = nums[i], j = i;
			while (--j >= 0 && num < nums[j]) nums[j+1] = nums[j];
			nums[j+1] = num;
		}
	}

	// √
	public static void selectionSort(int[] nums) {
		int minInd;
		for (int i=0;i<nums.length;i++) {
			minInd = i;
			for (int j=i;j<nums.length;j++) { // Find Minimum
				if (nums[minInd] > nums[j]) minInd = j;
			}
			swapInArr(nums, i, minInd);
		}
	}

	// √
	public static void mergeSort(int[] nums) {
		int[] temp = mergeSort(Arrays.copyOfRange(nums, 0, nums.length/2),Arrays.copyOfRange(nums, nums.length/2,nums.length));
		for (int i=0;i<nums.length;i++) nums[i] = temp[i];
	}
	private static int[] mergeSort(int[] num1,int[] num2) {
		if (num1.length > 1) num1 = mergeSort(Arrays.copyOfRange(num1, 0, num1.length/2),Arrays.copyOfRange(num1, num1.length/2,num1.length)); // split
		if (num2.length > 1) num2 = mergeSort(Arrays.copyOfRange(num2, 0, num2.length/2),Arrays.copyOfRange(num2, num2.length/2,num2.length)); // split

		int[] merged = new int[num1.length+num2.length];
		for (int i=0,j=0,c=0;c<merged.length;) {
			// Puts smallest and increments index
			merged[c++] = (num1[i] < num2[j] ? num1[i++] : num2[j++]);

			// Catch if one array ends
			if (i >= num1.length) while (j < num2.length) merged[c++] = num2[j++];
			if (j >= num2.length) while (i < num1.length) merged[c++] = num1[i++];
		}
		return merged;
	}

	public static void quickSort(int[] nums) {quickSort(nums,(int)(Math.random()*(nums.length-0)+0),0,nums.length-1);}
	public static void quickSort(int[] nums,int pivotInd,int low,int high) {
		if (low >= high || (low < 0 || high > nums.length)) return;

		int pivot = nums[pivotInd];
		System.out.println("Called with low: "+low+", high: "+high+", and Pivot: nums["+pivotInd+"] = "+pivot);

		// Iterating through partition, sorting to lower and higher partitions
		for (int i=low,j=high;i <= j;) {
			while (nums[i] < pivot && i<=j) i++;
			while (nums[j] > pivot && j>=i) j--;

			if (nums[i] == pivot) pivotInd = i;
			if (nums[j] == pivot) pivotInd = j;

			swapInArr(nums,i,j);
		}

		quickSort(nums,(int)(Math.random()*(high-low+1)+low),low,pivotInd-1);
		quickSort(nums,(int)(Math.random()*(high-low+1)+low),pivotInd+1,high);
	}

	public static void humanSort(int[] nums) {

		int[] out = new int[nums.length];

		// Find Min & Max
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i=0;i<nums.length;i++) {
			min = Math.min(nums[i],min);
			max = Math.max(nums[i],max);
		}

		// Sorting
		for (int i=0;i<nums.length;i++) {
			out[/*function here*/ 0] = nums[i];
		}

		// (Optional) Copying over to nums, void function
		for (int i=0;i<nums.length;i++) nums[i] = out[i];
		// return nums; // Other option if function is return instead of modifying input
	}
	
	private static void swapInArr(int[] arr, int indA, int indB) {
		if (indA == indB) return;
		int temp = arr[indA];
		arr[indA] = arr[indB];
		arr[indB] = temp;
	}
}
package algorithms;

// Code from "Data Structures and Problem Solving Using Java"
// by Mark Allen Weiss (2nd edition), 2001.   
public final class MaxSubsequenceSumTester
{
    //seqStart and seqEnd are member variables
    //so that you can print out the indices of the 
	//start/end of the maxSubsequence
    static private int seqStart = 0;
    static private int seqEnd = -1;

    // comment out sysouts on lines 22, 26, 29 after you understand how it works
    public static int maxSubsequenceSum1( int [ ] a )
    {
        int maxSum = 0;
        long startTime = System.currentTimeMillis();
        for( int i = 0; i < a.length; i++ )
            for( int j = i; j < a.length; j++ ){
                int thisSum = 0;

                for( int k = i; k <= j; k++ ) {
                		//System.out.println("i: " + i + " j: " + j + " k: " + k);
                    thisSum += a[ k ];
                }
        			//System.out.println("**Total Sum from i: " + i + " to j: " + j + " --> " + thisSum);
                if( thisSum > maxSum )
                {
                    maxSum   = thisSum;
        				//System.out.println("****Found a new Max Sum of " + thisSum + "! (i: " + i + " j: " + j + ")");
                    seqStart = i;
                    seqEnd   = j;
                }
            }
		long stopTime = System.currentTimeMillis(); 
		long timeForCubicMaxSubSum = (stopTime - startTime);
		System.out.println("Time for Cubic Max Sub Sum " + timeForCubicMaxSubSum + " ms");
		
        return maxSum;
    }

    /**
     * Quadratic maximum contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubsequenceSum2( int [ ] a )
    {
        int maxSum = 0;
        long startTime = System.currentTimeMillis();
        
        for( int i = 0; i < a.length; i++ ) {
			if (a[i] < 0) continue;
			int thisSum = 0;
			for (int j=i;j<a.length;thisSum+=a[j++])
				if (thisSum > maxSum) {
					maxSum = thisSum;
					seqStart = i;
					seqEnd = j-1;
				}
        }
        
		long stopTime = System.currentTimeMillis(); 
		long timeForQuadraticMaxSubSum = (stopTime - startTime);
		System.out.println("Time for Quadratic Max Sub Sum " + timeForQuadraticMaxSubSum + " ms");
        
		return maxSum;
    }

    /**
     * Linear-time maximum contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubsequenceSum3( int [ ] a )
    {
        int maxSum = 0;
        int thisSum = 0;
        
        long startTime = System.currentTimeMillis();
        
        for( int i = 0, j = 0; j < a.length; j++ ) {
			// Add up the next element at j,
			// then if sum is negative,
			// reset to index after j, (and set j & i to that index)
			if ((thisSum+=a[j]) < 0) thisSum = a[i = ++j];

			if (thisSum > maxSum) {
				maxSum = thisSum;
				seqStart = i;
				seqEnd = j;
			}
        }

		long stopTime = System.currentTimeMillis(); 
		long timeForLinearMaxSubSum = (stopTime - startTime);
		System.out.println("Time for Linear Max Sub Sum " + timeForLinearMaxSubSum + " ms");

        return maxSum;
    }

   

    /**
     * Simple test program.
     */
    public static void main( String [ ] args )
    {
        //int[] shortArray = { 7, -4, 5, 1, -12, 8, 10, -1, 22, -5 };
        int[] longArray = getRandArray(5000); // modify the parameter for diff. size
        
        int[] arrayToTest = longArray; // change this to test a longer array
        
        int maxSum;
        
        System.out.println("***Testing different Max Subsequence Sum Finding Algorithms***");
        System.out.println("Array used is: " + java.util.Arrays.toString(arrayToTest));

        //*** O(n^3) -- cubic time test ***
        maxSum = maxSubsequenceSum1( arrayToTest );
        System.out.println( "Testing O(n^3) algorithm: Max sum is " + maxSum + "; it goes"
                       + " from " + seqStart + " to " + seqEnd + "\n" );
        
        //*** O(n^2) -- quadratic time test ***
        seqStart = 0; seqEnd = 0; // reset global variables for next test
        maxSum = maxSubsequenceSum2( arrayToTest );
        System.out.println( "Testing O(n^2) algorithm: Max sum is " + maxSum + "; it goes"
                       + " from " + seqStart + " to " + seqEnd + "\n"  );
        
        //*** O(n) -- linear time test ***
        seqStart = 0; seqEnd = 0; // reset global variables for next test
        maxSum = maxSubsequenceSum3( arrayToTest );
        System.out.println( "Testing O(n) algorithm: Max sum is " + maxSum + "; it goes"
                       + " from " + seqStart + " to " + seqEnd + "\n"  );
    }
    
    private static int[] getRandArray(int size) {
    		int[] array = new int[size];
    		for(int i = 0; i < array.length; i++)
    			array[i] = (int)(Math.random()*50 - 25);
    		return array;
    }
}
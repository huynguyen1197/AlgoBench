
public class RadixSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting

    // A utility function to get maximum value in arr[] 
    public static int getMax(int arr[], int n) 
    { 
        int mx = arr[0]; 
        for (int i = 1; i < n; i++) {
        	comparisons++;
            if (arr[i] > mx) 
                mx = arr[i];
        }
        return mx; 
    }
    
    // find minimum value
    static int getMin(int arr[], int n) 
    { 
        int min = arr[0]; 
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) 
                min = arr[i]; 
        }
        return min; 
    }

  
    // A function to do counting sort of arr[] according to 
    // the digit represented by exp. 
    public static void countSort(int arr[], int n, int exp) 
    { 
        int output[] = new int[n]; // output array 
        int i; 
        int count[] = new int[10]; 
  
        // Store count of occurrences in count[] 
        for (i = 0; i < n; i++) 
            count[ (arr[i]/exp)%10 ]++; 
  
        // Change count[i] so that count[i] now contains 
        // actual position of this digit in output[] 
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            movements++;
        }
  
        // Build the output array 
        for (i = n - 1; i >= 0; i--) 
        { 
        	movements++;
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i]; 
            count[ (arr[i]/exp)%10 ]--; 
        } 
  
        // Copy the output array to arr[], so that arr[] now 
        // contains sorted numbers according to curent digit 
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
            movements++;
        }
    } 
  
    // The main function to that sorts arr[] of size n using 
    // Radix Sort 
    public static void radixSort(int arr[]) 
    { 
    	long start = System.nanoTime();
    	// find the offset to make elements of the list positive
    	int min = getMin(arr, arr.length);
    	
    	if (min < 0) {
    		for (int i = 0; i < arr.length; i++)
    			arr[i] -= min;
    	}
        // Find the maximum number to know number of digits 
        int m = getMax(arr, arr.length); 
  
        // Do counting sort for every digit. Note that instead 
        // of passing digit number, exp is passed. exp is 10^i 
        // where i is current digit number 
        for (int exp = 1; m/exp > 0; exp *= 10) 
            countSort(arr, arr.length, exp);
        
        // remove offset
        if (min < 0) {
        	for (int i = 0; i < arr.length; i++)
        		arr[i] += min;
        }
        long end = System.nanoTime();
        totalTime = end - start;
    } 
    
	/** Method to get the number of comparisons */
	public static long getComparisons() {
		return comparisons;
	}
	
	/** Method to get the number of movements */
	public static long getMovements() {
		return movements;
	}
	
	/** Method to get the total time spent for sorting */
	public static long getTotalTime() {
		return totalTime;
	}
	
	/**  Method to reset all the fields to zero */
	public static void reset() {
		comparisons = 0;
		movements = 0;
		totalTime = 0;
	}
  	
	/** The main method to test */
	public static void main(String[] args) {
		int[] list = {100,2000,-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4,1000, 5, 53};
		//for (int i = 0; i < 100000; i++)
			//list[i] = rd.nextInt(50000)-25000;
		reset();
		radixSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i] + " ");
		System.out.println(totalTime);
	}

}
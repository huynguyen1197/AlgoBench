
public class QuickSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting

	public static void quickSort(int[] list) {
		long start = System.nanoTime();
		quickSort(list, 0, list.length - 1);
		long end = System.nanoTime();
		totalTime = end - start;
	}
	
	public static void quickSort(int[] list, int first, int last) {
		
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
		
	}
	
	/** Partition the array list[first..last] */
	public static int partition(int[] list,int first, int last) {
		
		int current = (first+last)/2;
		int pivot = list[current]; // Choose the first element as the pivot
		list[current] = list[first];
		list[first] = pivot;
		movements++;
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search
		
		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] <= pivot) {
				comparisons++; // one comparison made in while loop condition
				low++;
			}
			comparisons++; // last comparison to terminate while loop
			// Search backward from right
			while (low <= high && list[high] > pivot) {
				comparisons++; // one comparison made in while loop condition
				high--;
			}
			comparisons++; // last comparison to terminate while loop
			
			// Swap two elements in the list
			if (high > low) {
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
				movements++;
			}
		}
		
		while (high > first && list[high] >= pivot) {
			comparisons++; // one comparison made in while loop condition
			high--;
		}
		comparisons++; // last comparison to terminate while loop
		// Swap pivot with list[high]
		comparisons++;
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			movements++;
			return high;
		}
		else {
			return first;
		}
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
		//	list[i] = 100000 - i;
		reset();
		quickSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i] + " ");
		System.out.println("total time is: " + totalTime);
	}

}


public class MergeSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting

	/** The method for sorting the numbers */
	public static void mergeSort(int[] list) {
		
		long start = System.nanoTime();
		if (list.length > 1) {
			
			// Merge sort the first half
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);
			
			//Merge sort the second  half
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2,
					secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);
			
			// Merge firstHalft with secondHalft into list
			merge(firstHalf, secondHalf, list);
		}
		long end = System.nanoTime();
		totalTime = end - start;
		
	}
	
	/** Merge two sorted lists */
	public static void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp
		
		while (current1 < list1.length && current2 < list2.length) {
			comparisons++;
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
				movements++;
			}
			else {
				temp[current3++] = list2[current2++];
				movements++;
			}
		}
		
		while (current1 < list1.length) {
			temp[current3++] = list1[current1++];
			movements++;
		}
		
		while (current2 < list2.length) {
			temp[current3++] = list2[current2++];
			movements++;
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
		mergeSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i] + " ");
		System.out.println(totalTime);
	}
}

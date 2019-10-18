
public class SelectionSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting
	
	/** The method for sorting the numbers */
	public static void selectionSort(int[] list) {
				
		long start = System.nanoTime();
		for (int i = 0; i < list.length - 1; i++) {
			
			// Find the minimum in the list[i..list.length-1]
			int currentMin = list[i];
			int currentMinIndex = i;
			
			for (int j = i + 1; j < list.length; j++) {
				
				comparisons++; // 1 comparisons done
				if (currentMin > list[j]) {
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			
			//Swap list[i] with list[currentMinIndex] if necessary
			if (currentMinIndex != i) {
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
				movements++;
			}
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
		int[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4,1000, 5, 53};
		selectionSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}

}

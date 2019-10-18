
public class InsertionSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting
	
	/** The method for sorting the numbers */
	public static void insertionSort(int[] list) {
				
		long start = System.nanoTime(); //take the start time

		for (int i = 1; i < list.length; i++) {
			/** Insert list[i] into a sorted sublist list[0..i-1] so that
			 	list[0..i] is sorted*/
			int currentElement = list[i];
			int k;
			for (k = i - 1; k >= 0; k--) {
				
				comparisons++; // one comparison done
				if (list[k] > currentElement) {
					list[k+1] = list[k];
					movements++; // one movement done
				}
				else
					break;
			}
			
			// Insert the current element into list[k+1]
			list[k+1] = currentElement;
			if ((k+1) != i)
				movements++; // one movement done if the currentElement is not yet in the correct position
		}
		
		long end = System.nanoTime(); // take the end time
		totalTime = end - start; // calculate the total time
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
		int[] list = {-44, -5, -3, 3, 3, 2000, 3000, 1, -4, 0, 1, 2, 4,1000, 5, 53};
		reset();
		insertionSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.println(list[i] + " ");
		System.out.println(totalTime);
	}
}

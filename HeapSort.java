
public class HeapSort {
	
	private static long comparisons = 0; // number of comparisons
	private static long movements = 0; // number of movements
	private static long totalTime = 0; // total time spent for sorting

	/** Heap sort method */
	public static <E extends Comparable<E>> void heapSort(E[] list) {
		
		long start = System.nanoTime();
		// Create a Heap of integers
		Heap<E> heap = new Heap<>();
		
		// Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);
		
		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
		long end = System.nanoTime();
		totalTime = end - start;
		comparisons = heap.getComparisons();
		movements = heap.getMovements();
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

	/** A test method */
	public static void main(String[] args) {
		Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
		heapSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
}



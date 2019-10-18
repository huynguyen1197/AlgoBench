import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

/** Frame Class that extends JFrame class */
public class AlgoBenchmark extends JFrame {

	// Buttons on the Algorithm Panel (Left Panel)
	private JButton insertion = new JButton("Insertion Sort");
	private JButton selection = new JButton("Selection Sort");
	private JButton quick = new JButton("Quick Sort");
	private JButton merge = new JButton("Merge Sort");
	private JButton heap = new JButton("Heap Sort");
	private JButton radix = new JButton("Radix Sort");

	// TextField on the winning algorithm
	private JTextField winAl = new JTextField(20);

	// RadioButtons on the List Properties
	private JRadioButton radioOrder = new JRadioButton("InOrder", false);
	private JRadioButton radioReverse = new JRadioButton("ReverseOrder", false);
	private JRadioButton radioAlmostOrder = new JRadioButton("AlmostOrdered", false);
	private JRadioButton radioAlmostRandom = new JRadioButton("AlmostRandom", false);
	private JRadioButton radioRandom = new JRadioButton("Random", true);
	private ButtonGroup group = new ButtonGroup(); // group for mutually exclusive

	// Slide, TextField, and create button to set size of the array
	private JSlider slide = new JSlider(0, 100000, 50000);
	private JTextField textSlide = new JTextField();
	private JButton createButton = new JButton("Create The List");

	// Text fields on results panel
	private JTextField N = new JTextField();
	private JTextField DataType = new JTextField();
	private JTextField Sort = new JTextField();
	private JTextField Compare = new JTextField();
	private JTextField moves = new JTextField();
	private JTextField time = new JTextField();
	
	// The array to be worked on
	private int[] arrRandom;
	private int[] arrSorted;
	private int[] arrReverse;
	private int[] arrAlmostSort;
	private int[] arrAlmostRandom;
	
	// Random object to generate random array
	Random rd = new Random();

	// Constructor
	public AlgoBenchmark() {
		
		super("AlgoBenchmark"); // call the JFrame's constructor
		setSize(600, 500); // set Size of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the closebutton's function
		
		// set look and feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error setting the look and feel!"
					+ "\nPress OK or close this window to use default look and feel");
		}
		
		/** Main Panel */
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel(), BorderLayout.WEST);
		mainPanel.add(eastPanel(), BorderLayout.CENTER);
		add(mainPanel);
		setVisible(true);
		
		// setResizable(false); //Uncomment this if do not desire to change the window size
	}

	/** Left main panel */
	private JPanel westPanel() {
		
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200, 500));
		west.setLayout(new GridLayout(6, 1));
		west.add(insertion); insertion.addActionListener(new buttonsListener());
		west.add(selection); selection.addActionListener(new buttonsListener());
		west.add(quick); quick.addActionListener(new buttonsListener());
		west.add(merge); merge.addActionListener(new buttonsListener());
		west.add(heap); heap.addActionListener(new buttonsListener());
		west.add(radix); radix.addActionListener(new buttonsListener());
		TitledBorder border = BorderFactory.createTitledBorder("Algorithms");
		border.setTitleJustification(TitledBorder.CENTER);
		Border margin = new EmptyBorder(10, 10, 10, 10);
		west.setBorder(new CompoundBorder(border, margin));

		return west;
	}

	/** Right main panel */
	private JPanel eastPanel() {

		JPanel east = new JPanel();
		east.setLayout(new BorderLayout());
		east.add(winPanel(), BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 1));
		center.add(listPanel());
		center.add(resultPanel());
		east.add(center, BorderLayout.CENTER);
		return east;
	}

	/** Winning Algorithm panel */
	private JPanel winPanel() {
		JPanel win = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("Winning Algorithm");
		border.setTitleJustification(TitledBorder.CENTER);
		win.setBorder(border);
		winAl.setHorizontalAlignment(JTextField.CENTER);
		winAl.setEditable(false);
		winAl.setHorizontalAlignment(JTextField.CENTER);
		win.add(winAl);

		return win;
	}

	/** List Properties panel */
	private JPanel listPanel() {

		JPanel list = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("List Properties");
		border.setTitleJustification(TitledBorder.CENTER);
		Border margin = new EmptyBorder(0, 20, 0, 10);
		list.setBorder(new CompoundBorder(border, margin));
		list.setLayout(new BorderLayout());

		/** Buttons for lists */
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(3, 2));
		group.add(radioOrder);
		group.add(radioReverse);
		group.add(radioAlmostOrder);
		group.add(radioAlmostRandom);
		group.add(radioRandom);
		buttons.add(radioOrder);
		buttons.add(radioReverse);
		buttons.add(radioAlmostOrder);
		buttons.add(radioAlmostRandom);
		buttons.add(radioRandom);

		/** Sliders for number of nodes and create button */
		JPanel sliPanel = new JPanel();
		sliPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		slide.setPreferredSize(new Dimension(200, 20));
		textSlide.setPreferredSize(new Dimension(90, 30));
		// textSlide.setEditable(false);

		slide.addChangeListener(new SliderListener()); // add change listener
		textSlide.addKeyListener(new KeyAdapter());
		textSlide.setText("" + slide.getValue());
		textSlide.setHorizontalAlignment(JTextField.CENTER);

		/** Create button */
		JPanel create = new JPanel();
		createButton.setPreferredSize(new Dimension(320, 30));
		createButton.addActionListener(new buttonsListener());
		create.add(createButton);
		sliPanel.add(slide);
		sliPanel.add(new JLabel("Size"));
		sliPanel.add(textSlide);
		sliPanel.add(create);

		list.add(buttons, BorderLayout.NORTH);
		list.add(sliPanel, BorderLayout.CENTER);
		return list;
	}

	/** Experimental Result panel */
	private JPanel resultPanel() {
		JPanel result = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("Experimental Results");
		border.setTitleJustification(TitledBorder.CENTER);
		Border margin = new EmptyBorder(10, 30, 10, 10);
		result.setBorder(new CompoundBorder(border, margin));
		result.setLayout(new BorderLayout());

		/** Labels */
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(6, 1));
		labels.add(new JLabel("N:", JLabel.RIGHT));
		labels.add(new JLabel("DataType:", JLabel.RIGHT));
		labels.add(new JLabel("Sort:", JLabel.RIGHT));
		labels.add(new JLabel("Comparisons:", JLabel.RIGHT));
		labels.add(new JLabel("Movements:", JLabel.RIGHT));
		labels.add(new JLabel("Total time (in nanosecond):", JLabel.RIGHT));

		/** text field */
		JPanel texts = new JPanel();
		texts.setLayout(new GridLayout(6, 1));
		texts.setBorder(new EmptyBorder(0, 5, 0, 22));

		N.setEditable(false);
		DataType.setEditable(false);
		Sort.setEditable(false);
		Compare.setEditable(false);
		moves.setEditable(false);
		time.setEditable(false);

		//N.setHorizontalAlignment(JTextField.CENTER);
		//DataType.setHorizontalAlignment(JTextField.CENTER);
		//Sort.setHorizontalAlignment(JTextField.CENTER);
		//Compare.setHorizontalAlignment(JTextField.CENTER);
		//moves.setHorizontalAlignment(JTextField.CENTER);
		//time.setHorizontalAlignment(JTextField.CENTER);

		texts.add(N);
		texts.add(DataType);
		texts.add(Sort);
		texts.add(Compare);
		texts.add(moves);
		texts.add(time);

		result.add(labels, BorderLayout.WEST);
		result.add(texts, BorderLayout.CENTER);
		return result;
	}

	/** Event Handler for Slider */
	private class SliderListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			textSlide.setText("" + slide.getValue());
		}

	}

	/** Event Handler for buttons */
	private class buttonsListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// The Create-List button
			if (e.getSource() == createButton) {
				if (!textSlide.getText().matches("\\d+"))
					JOptionPane.showMessageDialog(null, "Please set a positive integer size (from 1 to 100000)");
				else if (Integer.parseInt(textSlide.getText()) <= 0)
					JOptionPane.showMessageDialog(null, "Please set a positive integer size (from 1 to 100000)");
				else {
					// Order list
					if (radioOrder.isSelected()) {
						arrSorted = new int[slide.getValue()];
						for (int i = 0; i < arrSorted.length; i++) {
							arrSorted[i] = i;
							//System.out.println(arrSorted[i]);
						}
						
						N.setText(textSlide.getText());
						DataType.setText("InOrder");
						Sort.setText("");
						Compare.setText("");
						moves.setText("");
						time.setText("");
						
						int[] copy;
						
						InsertionSort.reset();
						copy = Arrays.copyOf(arrSorted, arrSorted.length);
						InsertionSort.insertionSort(copy);
												
						SelectionSort.reset();
						copy = Arrays.copyOf(arrSorted, arrSorted.length);
						SelectionSort.selectionSort(copy);
						
						QuickSort.reset();
						copy = Arrays.copyOf(arrSorted, arrSorted.length);
						QuickSort.quickSort(copy);
						
						MergeSort.reset();
						copy = Arrays.copyOf(arrSorted, arrSorted.length);
						MergeSort.mergeSort(copy);
						
						Integer[] copy1 = new Integer[arrSorted.length]; //array of Integer objects for heap sort
						//copy the array into Integer array
						for (int i = 0; i < arrSorted.length; i++)
							copy1[i] = Integer.valueOf(arrSorted[i]);
						
						HeapSort.reset();
						HeapSort.heapSort(copy1);

						RadixSort.reset();
						copy = Arrays.copyOf(arrSorted, arrSorted.length);
						RadixSort.radixSort(copy);
				
						//Find the winning algorithm
						long[] times = new long[6];
						times[0] = InsertionSort.getTotalTime();
						times[1] = SelectionSort.getTotalTime();
						times[2] = QuickSort.getTotalTime();
						times[3] = MergeSort.getTotalTime();
						times[4] = HeapSort.getTotalTime();
						times[5] = RadixSort.getTotalTime();
						
						long min = getMin(times,6);
						
						if (min == InsertionSort.getTotalTime())
							winAl.setText("Insertion Sort");
						else if (min == SelectionSort.getTotalTime())
							winAl.setText("Selection Sort");
						else if (min == QuickSort.getTotalTime())
							winAl.setText("Quick Sort");
						else if (min == MergeSort.getTotalTime())
							winAl.setText("Merge Sort");
						else if (min == HeapSort.getTotalTime())
							winAl.setText("Heap Sort");
						else if (min == RadixSort.getTotalTime())
							winAl.setText("Radix Sort");
						
									
						JOptionPane.showMessageDialog(null, "The list has been created and sorted by all algorithms\n"
														+ "The fastest algorithm is introduced on top panel\n"
															+ "Click on algorithm-buttons on the left panel to see individual results");

					}
					
					// Reverse list
					else if (radioReverse.isSelected()) {
						arrReverse = new int[slide.getValue()];
						for (int i = 0; i < arrReverse.length; i++) {
							arrReverse[i] = arrReverse.length - i;
							//System.out.println(arrReverse[i]);
						}
						
						N.setText(textSlide.getText());
						DataType.setText("ReverseOrder");
						Sort.setText("");
						Compare.setText("");
						moves.setText("");
						time.setText("");
						
						int[] copy;
						
						InsertionSort.reset();
						copy = Arrays.copyOf(arrReverse, arrReverse.length);
						InsertionSort.insertionSort(copy);
												
						SelectionSort.reset();
						copy = Arrays.copyOf(arrReverse, arrReverse.length);
						SelectionSort.selectionSort(copy);
						
						QuickSort.reset();
						copy = Arrays.copyOf(arrReverse, arrReverse.length);
						QuickSort.quickSort(copy);
						
						MergeSort.reset();
						copy = Arrays.copyOf(arrReverse, arrReverse.length);
						MergeSort.mergeSort(copy);
						
						Integer[] copy1 = new Integer[arrReverse.length]; //array of Integer objects for heap sort
						//copy the array into Integer array
						for (int i = 0; i < arrReverse.length; i++)
							copy1[i] = Integer.valueOf(arrReverse[i]);
						
						HeapSort.reset();
						HeapSort.heapSort(copy1);
						
						RadixSort.reset();
						copy = Arrays.copyOf(arrReverse, arrReverse.length);
						RadixSort.radixSort(copy);
						
						//Find the winning algorithm
						long[] times = new long[6];
						times[0] = InsertionSort.getTotalTime();
						times[1] = SelectionSort.getTotalTime();
						times[2] = QuickSort.getTotalTime();
						times[3] = MergeSort.getTotalTime();
						times[4] = HeapSort.getTotalTime();
						times[5] = RadixSort.getTotalTime();
						
						long min = getMin(times,6);
						
						if (min == InsertionSort.getTotalTime())
							winAl.setText("Insertion Sort");
						else if (min == SelectionSort.getTotalTime())
							winAl.setText("Selection Sort");
						else if (min == QuickSort.getTotalTime())
							winAl.setText("Quick Sort");
						else if (min == MergeSort.getTotalTime())
							winAl.setText("Merge Sort");
						else if (min == HeapSort.getTotalTime())
							winAl.setText("Heap Sort");
						else if (min == RadixSort.getTotalTime())
							winAl.setText("Radix Sort");
						
						JOptionPane.showMessageDialog(null, "The list has been created and sorted by all algorithms\n"
								+ "The fastest algorithm is introduced on top panel\n"
									+ "Click on algorithm-buttons on the left panel to see individual results");

					}
					
					// AlmostOrder list
					else if (radioAlmostOrder.isSelected()) {
						arrAlmostSort = new int[slide.getValue()];
						for (int i = 0; i < arrAlmostSort.length; i++) {
							if (i <= arrAlmostSort.length*0.8) 
								arrAlmostSort[i] = i;
							else
								arrAlmostSort[i] = rd.nextInt(200000) - 100000;
							//System.out.println(arrAlmostSort[i]);
						}
						
						N.setText(textSlide.getText());
						DataType.setText("AlmostOrdered");
						Sort.setText("");
						Compare.setText("");
						moves.setText("");
						time.setText("");
						
						int[] copy;
						
						InsertionSort.reset();
						copy = Arrays.copyOf(arrAlmostSort, arrAlmostSort.length);
						InsertionSort.insertionSort(copy);
												
						SelectionSort.reset();
						copy = Arrays.copyOf(arrAlmostSort, arrAlmostSort.length);
						SelectionSort.selectionSort(copy);
						
						QuickSort.reset();
						copy = Arrays.copyOf(arrAlmostSort, arrAlmostSort.length);
						QuickSort.quickSort(copy);
						
						MergeSort.reset();
						copy = Arrays.copyOf(arrAlmostSort, arrAlmostSort.length);
						MergeSort.mergeSort(copy);
						
						Integer[] copy1 = new Integer[arrAlmostSort.length]; //array of Integer objects for heap sort
						//copy the array into Integer array
						for (int i = 0; i < arrAlmostSort.length; i++)
							copy1[i] = Integer.valueOf(arrAlmostSort[i]);
						
						HeapSort.reset();
						HeapSort.heapSort(copy1);
						
						RadixSort.reset();
						copy = Arrays.copyOf(arrAlmostSort, arrAlmostSort.length);
						RadixSort.radixSort(copy);
						
						//Find the winning algorithm
						long[] times = new long[6];
						times[0] = InsertionSort.getTotalTime();
						times[1] = SelectionSort.getTotalTime();
						times[2] = QuickSort.getTotalTime();
						times[3] = MergeSort.getTotalTime();
						times[4] = HeapSort.getTotalTime();
						times[5] = RadixSort.getTotalTime();
						
						long min = getMin(times,6);
						
						if (min == InsertionSort.getTotalTime())
							winAl.setText("Insertion Sort");
						else if (min == SelectionSort.getTotalTime())
							winAl.setText("Selection Sort");
						else if (min == QuickSort.getTotalTime())
							winAl.setText("Quick Sort");
						else if (min == MergeSort.getTotalTime())
							winAl.setText("Merge Sort");
						else if (min == HeapSort.getTotalTime())
							winAl.setText("Heap Sort");
						else if (min == RadixSort.getTotalTime())
							winAl.setText("Radix Sort");
						
						JOptionPane.showMessageDialog(null, "The list has been created and sorted by all algorithms\n"
								+ "The fastest algorithm is introduced on top panel\n"
									+ "Click on algorithm-buttons on the left panel to see individual results");

					}
					
					// Almost Random list
					else if (radioAlmostRandom.isSelected()) {
						arrAlmostRandom = new int[slide.getValue()];
						for (int i = 0; i < arrAlmostRandom.length; i++) {
							if (i <= arrAlmostRandom.length*0.2)
								arrAlmostRandom[i] = i;
							else
								arrAlmostRandom[i] = rd.nextInt(200000) - 100000;
							//System.out.println(arrAlmostRandom[i]);
						}
						
						N.setText(textSlide.getText());
						DataType.setText("AlmostRandom");
						Sort.setText("");
						Compare.setText("");
						moves.setText("");
						time.setText("");
						
						int[] copy;
						
						InsertionSort.reset();
						copy = Arrays.copyOf(arrAlmostRandom, arrAlmostRandom.length);
						InsertionSort.insertionSort(copy);
												
						SelectionSort.reset();
						copy = Arrays.copyOf(arrAlmostRandom, arrAlmostRandom.length);
						SelectionSort.selectionSort(copy);
						
						QuickSort.reset();
						copy = Arrays.copyOf(arrAlmostRandom, arrAlmostRandom.length);
						QuickSort.quickSort(copy);
						
						MergeSort.reset();
						copy = Arrays.copyOf(arrAlmostRandom, arrAlmostRandom.length);
						MergeSort.mergeSort(copy);
						
						Integer[] copy1 = new Integer[arrAlmostRandom.length]; //array of Integer objects for heap sort
						//copy the array into Integer array
						for (int i = 0; i < arrAlmostRandom.length; i++)
							copy1[i] = Integer.valueOf(arrAlmostRandom[i]);
						
						HeapSort.reset();
						HeapSort.heapSort(copy1);
						
						RadixSort.reset();
						copy = Arrays.copyOf(arrAlmostRandom, arrAlmostRandom.length);
						RadixSort.radixSort(copy);
						
						//Find the winning algorithm
						long[] times = new long[6];
						times[0] = InsertionSort.getTotalTime();
						times[1] = SelectionSort.getTotalTime();
						times[2] = QuickSort.getTotalTime();
						times[3] = MergeSort.getTotalTime();
						times[4] = HeapSort.getTotalTime();
						times[5] = RadixSort.getTotalTime();
						
						long min = getMin(times,6);
						
						if (min == InsertionSort.getTotalTime())
							winAl.setText("Insertion Sort");
						else if (min == SelectionSort.getTotalTime())
							winAl.setText("Selection Sort");
						else if (min == QuickSort.getTotalTime())
							winAl.setText("Quick Sort");
						else if (min == MergeSort.getTotalTime())
							winAl.setText("Merge Sort");
						else if (min == HeapSort.getTotalTime())
							winAl.setText("Heap Sort");
						else if (min == RadixSort.getTotalTime())
							winAl.setText("Radix Sort");
						
						JOptionPane.showMessageDialog(null, "The list has been created and sorted by all algorithms\n"
								+ "The fastest algorithm is introduced on top panel\n"
									+ "Click on algorithm-buttons on the left panel to see individual results");

					}
					
					// Random list
					else {
						
						arrRandom = new int[slide.getValue()];
						for (int i = 0; i < arrRandom.length; i++) {
							arrRandom[i] = rd.nextInt(200000) - 100000;
							//System.out.println(arrRandom[i]);
						}
						
						N.setText(textSlide.getText());
						DataType.setText("Random");
						Sort.setText("");
						Compare.setText("");
						moves.setText("");
						time.setText("");
						
						int[] copy;
						
						InsertionSort.reset();
						copy = Arrays.copyOf(arrRandom, arrRandom.length);
						InsertionSort.insertionSort(copy);
												
						SelectionSort.reset();
						copy = Arrays.copyOf(arrRandom, arrRandom.length);
						SelectionSort.selectionSort(copy);
						
						QuickSort.reset();
						copy = Arrays.copyOf(arrRandom, arrRandom.length);
						QuickSort.quickSort(copy);
						
						for (int i = 0; i < copy.length; i++) {
							System.out.println(copy[i]);
						}
						
						MergeSort.reset();
						copy = Arrays.copyOf(arrRandom, arrRandom.length);
						MergeSort.mergeSort(copy);
						
						Integer[] copy1 = new Integer[arrRandom.length]; //array of Integer objects for heap sort
						//copy the array into Integer array
						for (int i = 0; i < arrRandom.length; i++)
							copy1[i] = Integer.valueOf(arrRandom[i]);
						
						HeapSort.reset();
						HeapSort.heapSort(copy1);
						
						RadixSort.reset();
						copy = Arrays.copyOf(arrRandom, arrRandom.length);
						RadixSort.radixSort(copy);
						
						//Find the winning algorithm
						long[] times = new long[6];
						times[0] = InsertionSort.getTotalTime();
						times[1] = SelectionSort.getTotalTime();
						times[2] = QuickSort.getTotalTime();
						times[3] = MergeSort.getTotalTime();
						times[4] = HeapSort.getTotalTime();
						times[5] = RadixSort.getTotalTime();
						
						long min = getMin(times,6);
						
						if (min == InsertionSort.getTotalTime())
							winAl.setText("Insertion Sort");
						else if (min == SelectionSort.getTotalTime())
							winAl.setText("Selection Sort");
						else if (min == QuickSort.getTotalTime())
							winAl.setText("Quick Sort");
						else if (min == MergeSort.getTotalTime())
							winAl.setText("Merge Sort");
						else if (min == HeapSort.getTotalTime())
							winAl.setText("Heap Sort");
						else if (min == RadixSort.getTotalTime())
							winAl.setText("Radix Sort");
						
						JOptionPane.showMessageDialog(null, "The list has been created and sorted by all algorithms\n"
								+ "The fastest algorithm is introduced on top panel\n"
									+ "Click on algorithm-buttons on the left panel to see individual results");
	
						
					}
				}
			}

			/** Buttons to choose algorithm to show results */
			
			// click to get result for insertion sort
			if (e.getSource() == insertion) {
				Sort.setText("Insertion");
				Compare.setText("" + InsertionSort.getComparisons());
				moves.setText("" + InsertionSort.getMovements());
				time.setText("" + InsertionSort.getTotalTime());
			}
			
			// click to get result for selection sort
			if (e.getSource() == selection) {
				Sort.setText("Selection");
				Compare.setText("" + SelectionSort.getComparisons());
				moves.setText("" + SelectionSort.getMovements());
				time.setText("" + SelectionSort.getTotalTime());
			}
			
			// click to get result for quick sort
			if (e.getSource() == quick) {
				Sort.setText("Quick");
				Compare.setText("" + QuickSort.getComparisons());
				moves.setText("" + QuickSort.getMovements());
				time.setText("" + QuickSort.getTotalTime());
			}

			// click to get result for merge sort
			if (e.getSource() == merge) {
				Sort.setText("Merge");
				Compare.setText("" + MergeSort.getComparisons());
				moves.setText("" + MergeSort.getMovements());
				time.setText("" + MergeSort.getTotalTime());
			}
			
			// click to get result for heap sort
			if (e.getSource() == heap) {
				Sort.setText("Heap");
				Compare.setText("" + HeapSort.getComparisons());
				moves.setText("" + HeapSort.getMovements());
				time.setText("" + HeapSort.getTotalTime());
			}
			
			// click to get result for radix sort
			if (e.getSource() == radix) {
				Sort.setText("Radix");
				Compare.setText("" + RadixSort.getComparisons());
				moves.setText("" + RadixSort.getMovements());
				time.setText("" + RadixSort.getTotalTime());
			}

		}
	}

	/** Event Handler for textField */
	private class KeyAdapter implements KeyListener {
		
		@Override
		public void keyReleased(KeyEvent e) {
			String typed = textSlide.getText();
			if (!typed.matches("\\d+")) {
				return;
			}
			int value = Integer.parseInt(typed);
			if (value > 100000)
				textSlide.setText("100000");
			slide.setValue(value);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
    // find minimum value
    public static long getMin(long arr[], int n) 
    { 
        long min = arr[0]; 
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) 
                min = arr[i]; 
        }
        return min; 
    }

	/** Main method */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AlgoBenchmark();
			}
		});

	}
}

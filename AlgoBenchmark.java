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

/** Frame */
public class AlgoBenchmark extends JFrame {
	
	private JButton insertion = new JButton("Insertion Sort");
	private JButton selection = new JButton("Selection Sort");
	private JButton quick = new JButton("Quick Sort");
	private JButton merge = new JButton("Merge Sort");
	private JButton heap = new JButton("Heap Sort");
	private JButton radix = new JButton("Radix Sort");
	private JTextField winAl = new JTextField(20);
	private JRadioButton radio1 = new JRadioButton("InOrder", false);
	private JRadioButton radio2 = new JRadioButton("ReverseOrder", false);
	private JRadioButton radio3 = new JRadioButton("AlmostOrder", false);
	private JRadioButton radio4 = new JRadioButton("Random", true);
	private JSlider slide = new JSlider(0,100000,50000);
	private JTextField textSlide = new JTextField();
	private JButton createButton = new JButton("Create The List");
	private JTextField N = new JTextField();
	private JTextField DataType = new JTextField();
	private JTextField Sort = new JTextField();
	private JTextField Compare = new JTextField();
	private JTextField moves = new JTextField();
	private JTextField time = new JTextField();
	
	//Constructor
	public AlgoBenchmark() {
		super("AlgoBenchmark");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e) {
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
		setResizable(false);
	}
	
	/** Left main panel */
	private JPanel westPanel() {
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200,500));
		west.setLayout(new GridLayout(6,1));
		west.add(insertion);
		west.add(selection);
		west.add(quick);
		west.add(merge);
		west.add(heap);
		west.add(radix);
		TitledBorder border = BorderFactory.createTitledBorder("Algorithms");
		border.setTitleJustification(TitledBorder.CENTER);
		Border margin = new EmptyBorder(10,10,10,10);
		west.setBorder(new CompoundBorder(border, margin));
		
	
		return west;
	}
	
	/** Right main panel */
	private JPanel eastPanel() {
		
		JPanel east = new JPanel();
		east.setLayout(new BorderLayout());
		east.add(winPanel(), BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,1));
		center.add(listPanel()); center.add(resultPanel());
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
		Border margin = new EmptyBorder(0,20,0,10);
		list.setBorder(new CompoundBorder(border, margin));
		list.setLayout(new BorderLayout());
		
		/** Buttons for lists */
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,2));
		ButtonGroup group = new ButtonGroup();
		group.add(radio1); group.add(radio2); group.add(radio3); group.add(radio4);
		buttons.add(radio1); buttons.add(radio2); buttons.add(radio3); buttons.add(radio4);
		
		/** Sliders for number of nodes and create button */
		JPanel sliPanel = new JPanel();
		sliPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		slide.setPreferredSize(new Dimension(200,20));
		textSlide.setPreferredSize(new Dimension(90,30));
		textSlide.setEditable(false);
		slide.addChangeListener(new SliderListener());
		textSlide.setText(""+slide.getValue());
	    textSlide.setHorizontalAlignment(JTextField.CENTER);
		
		/** Create button */
		JPanel create = new JPanel();
		createButton.setPreferredSize(new Dimension(320,30));
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
		Border margin = new EmptyBorder(10,30,10,10);
		result.setBorder(new CompoundBorder(border, margin));
		result.setLayout(new BorderLayout());
		
		/** Labels */
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(6,1));
		labels.add(new JLabel("N:", JLabel.RIGHT));
		labels.add(new JLabel("DataType:", JLabel.RIGHT));
		labels.add(new JLabel("Sort:", JLabel.RIGHT));
		labels.add(new JLabel("Comparisons:", JLabel.RIGHT));
		labels.add(new JLabel("Movements:", JLabel.RIGHT));
		labels.add(new JLabel("Total time:", JLabel.RIGHT));
		
		/** text field */
		JPanel texts = new JPanel();
		texts.setLayout(new GridLayout(6,1));
		texts.setBorder(new EmptyBorder(0,5,0,22));
		
		N.setEditable(false);
		DataType.setEditable(false);
		Sort.setEditable(false);
		Compare.setEditable(false);
		moves.setEditable(false);
		time.setEditable(false);
		
		N.setHorizontalAlignment(JTextField.CENTER);
		DataType.setHorizontalAlignment(JTextField.CENTER);
		Sort.setHorizontalAlignment(JTextField.CENTER);
		Compare.setHorizontalAlignment(JTextField.CENTER);
		moves.setHorizontalAlignment(JTextField.CENTER);
		time.setHorizontalAlignment(JTextField.CENTER);

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
	
	private class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			textSlide.setText(""+slide.getValue());
		}
		
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

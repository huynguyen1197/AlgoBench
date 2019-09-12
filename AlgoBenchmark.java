import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class AlgoBenchmark extends JFrame {
	public AlgoBenchmark() {
		super("AlgoBenchmark");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error setting the look and feel!"
											  + "\nPress OK or close this window to use default look and feel");
		}
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel(), BorderLayout.WEST);
		mainPanel.add(eastPanel(), BorderLayout.CENTER);
		add(mainPanel);
		setVisible(true);
	}
	
	private JPanel westPanel() {
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(6,1));
		JButton insertion = new JButton("Insertion Sort");
		JButton selection = new JButton("Selection Sort");
		JButton quick = new JButton("Quick Sort");
		JButton merge = new JButton("Merge Sort");
		JButton heap = new JButton("Heap Sort");
		JButton radix = new JButton("Radix Sort");
		west.add(insertion);
		west.add(selection);
		west.add(quick);
		west.add(merge);
		west.add(heap);
		west.add(radix);
		TitledBorder border = BorderFactory.createTitledBorder("Algorithms");
		border.setTitleJustification(TitledBorder.CENTER);
		west.setBorder(border);
		
	
		return west;
	}

	private JPanel eastPanel() {
		JPanel east = new JPanel();
		east.setLayout(new BorderLayout());
		east.add(winPanel(), BorderLayout.NORTH);
		east.add(listPanel(), BorderLayout.CENTER);
		
		return east;
	}
	
	private JPanel winPanel() {
		JPanel win = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("Winning Algorithm");
		border.setTitleJustification(TitledBorder.CENTER);
		win.setBorder(border);
		JTextField winAl = new JTextField(20);
		winAl.setHorizontalAlignment(JTextField.CENTER);
		winAl.setEditable(false);
		win.add(winAl);
		
		return win;
	}
	
	private JPanel listPanel() {
		JPanel list = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("List Properties");
		border.setTitleJustification(TitledBorder.CENTER);
		list.setBorder(border);
		list.setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JRadioButton radio1 = new JRadioButton("InOrder", false);
		JRadioButton radio2 = new JRadioButton("AlmostOrder", false);
		JRadioButton radio3 = new JRadioButton("ReverseOrder", false);
		JRadioButton radio4 = new JRadioButton("Random", true);
		ButtonGroup group = new ButtonGroup();
		group.add(radio1); group.add(radio2); group.add(radio3); group.add(radio4);
		buttons.add(radio1); buttons.add(radio2); buttons.add(radio3); buttons.add(radio4);
		list.add(buttons, BorderLayout.NORTH);
		
		
		return list;
	}
	
	
		
	
	public static void main(String[] args) {
		new AlgoBenchmark();
	}
}

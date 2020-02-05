package com.hit.graph.picker;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class GraphPickerPanel extends JPanel {
	JLabel computePathLabel;
	JComboBox sourceComboBox;
	JComboBox destinationComboBox;
	JComboBox graphPickerComboBox;
	JRadioButton bellManFordRadioButton;
	JRadioButton dijkstraRadioButton;
	ButtonGroup radioButtonGroup;
	JLabel noticeLabel;
	
	public JComboBox getSourceComboBox() {
		return sourceComboBox;
	}

	public JComboBox getDestinationComboBox() {
		return destinationComboBox;
	}

	public JComboBox getGraphPickerComboBox() {
		return graphPickerComboBox;
	}

	public JRadioButton getBellManFordRadioButton() {
		return bellManFordRadioButton;
	}

	public JRadioButton getDijkstraRadioButton() {
		return dijkstraRadioButton;
	}

	public ButtonGroup getRadioButtonGroup() {
		return radioButtonGroup;
	}
	
	public JLabel getNoticeLabel() {
		return noticeLabel;
	}
	
	public GraphPickerPanel() {
		setLayout(null);
		sourceComboBox = new JComboBox();
		sourceComboBox.setBounds(175, 40, 111, 25);
		sourceComboBox.setEditable(false);
		bellManFordRadioButton = new JRadioButton("Bellman-ford");
		bellManFordRadioButton.setActionCommand("bellman-ford");
		bellManFordRadioButton.setBounds(214, 76, 110, 25);
		dijkstraRadioButton = new JRadioButton("Dijkstra");
		dijkstraRadioButton.setActionCommand("dijkstra");
		dijkstraRadioButton.setBounds(326, 76, 123, 25);
		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(bellManFordRadioButton);
		radioButtonGroup.add(dijkstraRadioButton);
		bellManFordRadioButton.setSelected(true);
		destinationComboBox = new JComboBox<>();
		destinationComboBox.setBounds(334, 40, 111, 25);
		destinationComboBox.setEditable(false);
		graphPickerComboBox = new JComboBox();
		graphPickerComboBox.setBounds(175, 0, 270, 25);
		graphPickerComboBox.setEditable(true);
		noticeLabel = new JLabel("");
		noticeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		noticeLabel.setBounds(10, 108, 270, 14);
		add(dijkstraRadioButton);
		add(destinationComboBox);
		add(sourceComboBox);
		add(bellManFordRadioButton);
		add(graphPickerComboBox);
		add(noticeLabel);
		
		JLabel ComputePathLabel = new JLabel("Calculate:");
		JLabel ToLabel = new JLabel("\u2192");
		JLabel lblUsing = new JLabel("Using:");
		JLabel chooseGraphLabel = new JLabel("Choose graph:");
		JLabel ShortestPathLabel = new JLabel("");
		
		ComputePathLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ComputePathLabel.setBounds(96, 40, 69, 25);
		ToLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		ToLabel.setBounds(296, 37, 32, 25);		
		ShortestPathLabel.setBounds(4, 36, 69, 36);
		lblUsing.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsing.setBounds(161, 76, 69, 25);
		chooseGraphLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooseGraphLabel.setBounds(61, 0, 110, 25);
		
		add(chooseGraphLabel);
		add(lblUsing);
		add(ShortestPathLabel);
		add(ComputePathLabel);
		add(ToLabel);
		setVisible(true);
	}
}

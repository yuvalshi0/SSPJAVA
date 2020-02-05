package com.hit.main.client;
import javax.swing.JFrame;
import com.hit.graph.drawer.JGraphPanel;
import com.hit.graph.picker.GraphPickerPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;

public class SPPMainView extends JFrame {

	JGraphPanel graphPanel;
	GraphPickerPanel formPanel;
	JButton newGraphButton;
	
	public JGraphPanel getGraphPanel() {
		return graphPanel;
	}

	public GraphPickerPanel getFormPanel() {
		return formPanel;
	}
	
	public JButton getNewGraphButton() {
		return newGraphButton;
	}
	
	public SPPMainView() {
		JLabel TitleLabel = new JLabel("Shortest Path Calculator");
		graphPanel = new JGraphPanel(554, 298);
		formPanel = new GraphPickerPanel();
		newGraphButton = new JButton("New Graph Wizard");
		newGraphButton.setBounds(397, 446, 167, 23);
		graphPanel = new JGraphPanel(554,298);
		graphPanel.setBounds(10, 31, 554, 298);
		formPanel.setVisible(true);
		formPanel.setBounds(10, 340, 554, 129);
		
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(10, 11, 554, 14);
		
		getContentPane().add(newGraphButton);
		getContentPane().add(graphPanel);
		getContentPane().add(formPanel);
		getContentPane().add(TitleLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 519);
		getContentPane().setLayout(null);
		setTitle("Shortest Path Calculator");
	}


}

package com.hit.graph.wizard;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import com.hit.graph.drawer.JGraphPanel;
import com.hit.model.SPPModel;

import javax.swing.JTabbedPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;



public class SPPNewGraphView extends JFrame {

	JPanel mainPanel;
	JSpinner weightField;
	JComboBox fromComboBox;
	JButton addNewVertexButton;
	JLabel vertexNameLabel;
	JButton addNewEdgeButton;
	JComboBox toComboBox;
	JButton saveButton;
	JSpinner vertexNumberTextField;
	JGraphPanel graphPanel;
	JLabel noticeLabel;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JSpinner getWeightField() {
		return weightField;
	}

	public JComboBox getFromComboBox() {
		return fromComboBox;
	}

	public JButton getAddNewVertexButton() {
		return addNewVertexButton;
	}

	public JLabel getVertexNameLabel() {
		return vertexNameLabel;
	}

	public JButton getAddNewEdgeButton() {
		return addNewEdgeButton;
	}

	public JComboBox getToComboBox() {
		return toComboBox;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JSpinner getVertexNumberTextField() {
		return vertexNumberTextField;
	}

	public JGraphPanel getGraphPanel() {
		return graphPanel;
	}

	public JLabel getNoticeLabel() {
		return noticeLabel;
	}

	public SPPNewGraphView(SPPModel model) {
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		vertexNumberTextField = new JSpinner();
		vertexNumberTextField.setBounds(86, 15, 108, 22);
		addNewVertexButton = new JButton("Add");
		addNewVertexButton.setBounds(204, 15, 66, 22);
		vertexNameLabel = new JLabel("Add Vertex:");
		vertexNameLabel.setBounds(10, 18, 78, 14);
		addNewEdgeButton = new JButton("Add");
		addNewEdgeButton.setBounds(486, 15, 66, 22);
		fromComboBox = new JComboBox();
		fromComboBox.setBounds(73, 15, 108, 22);
		toComboBox = new JComboBox();
		toComboBox.setBounds(216, 15, 108, 22);
		weightField = new JSpinner();
		weightField.setBounds(380, 15, 96, 22);
		saveButton = new JButton("Save");
		saveButton.setBounds(490, 409, 89, 23);
		mainPanel.add(saveButton);
		graphPanel = new JGraphPanel(554,298);
		graphPanel.setBounds(23, 11, 554,298);
		mainPanel.add(graphPanel);
		
		JLabel lblTo = new JLabel("To");
		JLabel lblCost = new JLabel("Cost:");
		JPanel addEdgeTab = new JPanel();
		JPanel addVertexTab = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JLabel lblAddEdge = new JLabel("Add Edge:");
		mainPanel.add(tabbedPane);
		noticeLabel = new JLabel("");
		noticeLabel.setBounds(23, 418, 162, 14);
		noticeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		mainPanel.add(noticeLabel);
		tabbedPane.addTab("New Vertex", null, addVertexTab, null);
		tabbedPane.addTab("New Edge", null, addEdgeTab, null);
		tabbedPane.setBounds(10, 318, 567, 80);
		
		addVertexTab.setToolTipText("Add New Node");
		addVertexTab.setLayout(null);
		
		addVertexTab.add(vertexNumberTextField);	
		addVertexTab.add(addNewVertexButton);	
		addVertexTab.add(vertexNameLabel);
		
		addEdgeTab.setLayout(null);
		addEdgeTab.add(addNewEdgeButton);
		addEdgeTab.add(lblAddEdge);
		addEdgeTab.add(fromComboBox);
		addEdgeTab.add(toComboBox);
		addEdgeTab.add(lblTo);
		addEdgeTab.add(lblCost);
		addEdgeTab.add(weightField);
		
		lblTo.setBounds(191, 18, 78, 14);
		lblCost.setBounds(342, 18, 78, 14);
		lblAddEdge.setBounds(10, 18, 78, 14);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 482);
		setContentPane(mainPanel);
		setTitle("New Graph Wizard");
	}
}

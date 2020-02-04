import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.drawer.JGraphPanel;
import com.hit.model.SPPModel;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;



public class SPPNewGraphView extends JFrame {

	JPanel mainPanel;
	JTextField weightField;
	JComboBox fromComboBox;
	JButton addNewVertexButton;
	JLabel vertexNameLabel;
	JButton addNewEdgeButton;
	JComboBox toComboBox;
	JButton saveButton;
	JTextField vertexNumberTextField;
	JGraphPanel graphPanel;
	IntegerWeightedGraph graph = new IntegerWeightedGraph();
	private SPPModel model; 
	
	
	public SPPNewGraphView(SPPModel model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 482);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 318, 567, 80);
		mainPanel.add(tabbedPane);
		
		JPanel addVertexTab = new JPanel();
		addVertexTab.setToolTipText("Add New Node");
		tabbedPane.addTab("New Vertex", null, addVertexTab, null);
		addVertexTab.setLayout(null);
		
		vertexNumberTextField = new JTextField();
		vertexNumberTextField.setBounds(86, 15, 108, 22);
		addVertexTab.add(vertexNumberTextField);
		vertexNumberTextField.setColumns(10);
		addNewVertexButton = new JButton("Add");
		addNewVertexButton.setBounds(204, 15, 66, 22);
		addVertexTab.add(addNewVertexButton);
		addNewVertexButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int x = Integer.parseInt(vertexNumberTextField.getText());
				graph.addNode(x);
				graphPanel.drawGraph(graph);
				fromComboBox.removeAllItems();
				toComboBox.removeAllItems();
				for(int node : graph.getNodes()) fromComboBox.addItem(node);
				for(int node : graph.getNodes()) toComboBox.addItem(node);
			}
		});
	
		
		vertexNameLabel = new JLabel("Add Vertex:");
		vertexNameLabel.setBounds(10, 18, 78, 14);
		addVertexTab.add(vertexNameLabel);
		
		JPanel addEdgeTab = new JPanel();
		tabbedPane.addTab("New Edge", null, addEdgeTab, null);
		addEdgeTab.setLayout(null);
		
		addNewEdgeButton = new JButton("Add");
		addNewEdgeButton.setBounds(486, 15, 66, 22);
		addEdgeTab.add(addNewEdgeButton);
		
		addNewEdgeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int source = (int) fromComboBox.getSelectedItem();
				int destination = (int) toComboBox.getSelectedItem();
				int weight = Integer.parseInt(weightField.getText());
				graph.addEdge(new IntegerWeightedEdge(source, destination, weight));
				graphPanel.drawGraph(graph);
						}
		});
		
		
		JLabel lblAddEdge = new JLabel("Add Edge:");
		lblAddEdge.setBounds(10, 18, 78, 14);
		addEdgeTab.add(lblAddEdge);
		
		fromComboBox = new JComboBox();
		fromComboBox.setBounds(73, 15, 108, 22);
		addEdgeTab.add(fromComboBox);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(191, 18, 78, 14);
		addEdgeTab.add(lblTo);
		
		toComboBox = new JComboBox();
		toComboBox.setBounds(216, 15, 108, 22);
		addEdgeTab.add(toComboBox);
		
		JLabel lblCost = new JLabel("Cost:");
		lblCost.setBounds(342, 18, 78, 14);
		addEdgeTab.add(lblCost);
		
		weightField = new JTextField();
		weightField.setBounds(380, 15, 96, 22);
		addEdgeTab.add(weightField);
		weightField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(490, 409, 89, 23);
		mainPanel.add(saveButton);
		
		graphPanel = new JGraphPanel(554,298);
		graphPanel.setBounds(23, 11, 554,298);
		mainPanel.add(graphPanel);
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					model.post(graph);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}

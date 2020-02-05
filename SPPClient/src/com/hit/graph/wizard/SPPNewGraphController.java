package com.hit.graph.wizard;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.graph.picker.JLabelCleaner;
import com.hit.model.SPPModel;

public class SPPNewGraphController {
	
	private SwingPropertyChangeSupport propChangeFirer;
	private SPPModel model;
	private SPPNewGraphView view;
	private IntegerWeightedGraph graph;
	
	public SPPNewGraphController(SPPModel model, SPPNewGraphView view) {
		super();
		this.model = model;
		this.view = view;
		this.graph = new IntegerWeightedGraph();
		setUp();
		propChangeFirer = new SwingPropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener prop) {
	        propChangeFirer.addPropertyChangeListener(prop);
	 }
	
	public void setUp() {
		view.getAddNewEdgeButton().addActionListener(new NewEdgeAdded());
		view.getAddNewVertexButton().addActionListener(new NewVertexAdded());
		view.getSaveButton().addActionListener(new SaveNewGraph());
		view.getToComboBox().addActionListener(new NewEdgeVertexChange());
		view.getFromComboBox().addActionListener(new NewEdgeVertexChange());
		view.getAddNewEdgeButton().setEnabled(false);
	}
	
	 public void printNotice(String text, Color color) {
			view.getNoticeLabel().setForeground(color);
			view.getNoticeLabel().setText(text);
			JLabelCleaner cleaner = new JLabelCleaner(5,view.getNoticeLabel());
			cleaner.startCountdownFromNow();
	}
	 
	public void updateComboBox() {
		view.getToComboBox().removeAllItems();
		view.getFromComboBox().removeAllItems();
		for(int node : graph.getNodes()) view.getFromComboBox().addItem(node);
		for(int node : graph.getNodes()) view.getToComboBox().addItem(node);
	}
	
	private class NewVertexAdded implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int vertexName = (int) view.getVertexNumberTextField().getValue();
			graph.addNode(vertexName);
			updateComboBox();
			propChangeFirer.firePropertyChange("graph", null, graph);
		}
	}
	
	private class NewEdgeAdded implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int source = (int)view.getFromComboBox().getSelectedItem();
			int destination = (int)view.getToComboBox().getSelectedItem();
			int weight = (int) view.getWeightField().getValue();
			graph.addEdge(new IntegerWeightedEdge(source, destination, weight));
			propChangeFirer.firePropertyChange("graph", null, graph);
		}
	}
	
	private class NewEdgeVertexChange implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = view.getFromComboBox().getSelectedItem();
			Object destination = view.getToComboBox().getSelectedItem();	
			
			if(source instanceof Integer && destination instanceof Integer) {
				if(source ==  destination) {
					view.getAddNewEdgeButton().setEnabled(false);
				} else {
					view.getAddNewEdgeButton().setEnabled(true);
			}	
			}
			
		}
	}
	
	private class SaveNewGraph implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.post(graph);
				view.dispose();
			} catch (Exception e1) {
				printNotice("Error: " + e1.getMessage(), Color.RED);
			}
			
		}
	}
}




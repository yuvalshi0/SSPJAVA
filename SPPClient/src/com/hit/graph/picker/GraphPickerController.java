package com.hit.graph.picker;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;

import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.model.SPPModel;
import com.hit.model.dm.DataModel;
import com.hit.model.dm.GraphDataModel;

public class GraphPickerController implements PropertyChangeListener {
	private SPPModel model;
	private GraphPickerPanel view;
	private SwingPropertyChangeSupport propChangeFirer;
	
	
	public GraphPickerController(SPPModel model, GraphPickerPanel view) {
		super();
		this.model = model;
		this.view = view;		
		this.model.addListener(this);
		dataSetUp();
		actionSetUp();
        propChangeFirer = new SwingPropertyChangeSupport(this);
       
	}
	
	void dataSetUp() {
		 try {
	        	model.getAll();
	        } catch(Exception err) {
	        	printNotice("Could not make connection to the server",Color.RED);
	        	view.getGraphPickerComboBox().setEditable(false);
	        }
	}
	
	void actionSetUp() {
		view.getGraphPickerComboBox().addActionListener(new GraphChanged());
		view.getSourceComboBox().addActionListener(new PathChanged());
		view.getDestinationComboBox().addActionListener(new PathChanged());
		view.getBellManFordRadioButton().addActionListener(new PathChanged());
		view.getDijkstraRadioButton().addActionListener(new PathChanged());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propName = evt.getPropertyName();
        if("graphList".equalsIgnoreCase(propName)) {
        	graphListUpdated(evt);
        } else if("path".equalsIgnoreCase(propName)) {
        	graphAndPathUpdated(evt);
        }
     }
	
	void updateNodesComboBoxes(IntegerWeightedGraph graph) {
		int sourceIndex = view.getSourceComboBox().getSelectedIndex();
		int destinationIndex = view.getDestinationComboBox().getSelectedIndex();
		
		view.getSourceComboBox().removeAllItems();
		view.getDestinationComboBox().removeAllItems();
		for(int node : graph.getNodes()) view.getSourceComboBox().addItem(node);
		for(int node : graph.getNodes()) view.getDestinationComboBox().addItem(node);	
		view.getSourceComboBox().setSelectedIndex(sourceIndex);
		view.getDestinationComboBox().setSelectedIndex(destinationIndex);
	}
	
	void updateGraphList(List<DataModel<GraphDataModel>> list) {
		view.getGraphPickerComboBox().removeAllItems();
		for(DataModel<GraphDataModel> item : list) view.getGraphPickerComboBox().addItem(item);

	}
	
	 public void addListener(PropertyChangeListener prop) {
	        propChangeFirer.addPropertyChangeListener(prop);
	 }
	 
	 public void graphAndPathUpdated(PropertyChangeEvent evt) {
		GraphDataModel graph = null;
		LinkedList<Integer> path = (LinkedList<Integer>) evt.getNewValue();
     	DataModel<GraphDataModel> item = (DataModel<GraphDataModel>) view.getGraphPickerComboBox().getSelectedItem(); 
     	if(item != null) {
     		graph = item.getBody();
     	}
     	propChangeFirer.firePropertyChange("graph", null, graph);
     	propChangeFirer.firePropertyChange("path", null, path);
	 }
	 
	 public void graphListUpdated(PropertyChangeEvent evt) {
		int selectedIndex = view.getGraphPickerComboBox().getSelectedIndex();
     	List<DataModel<GraphDataModel>> list = (List<DataModel<GraphDataModel>>)evt.getNewValue();
     	updateGraphList(list);
     	if(selectedIndex >= 0) {
     		view.getGraphPickerComboBox().setSelectedIndex(selectedIndex);
     	}
     	printNotice("New Graph Added!",Color.green.darker());
	 }
	
	 public void printNotice(String text, Color color) {
		view.getNoticeLabel().setForeground(color);
		view.getNoticeLabel().setText(text);
		JLabelCleaner cleaner = new JLabelCleaner(5,view.getNoticeLabel());
		cleaner.startCountdownFromNow();
	 }
	 
	 
	private class GraphChanged implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DataModel<GraphDataModel> item = (DataModel<GraphDataModel>) view.getGraphPickerComboBox().getSelectedItem();
			if(item != null) {
			GraphDataModel graph = item.getBody();
			if(item.getBody().getNodes() != null) {
				updateNodesComboBoxes(graph);
				view.getDestinationComboBox().setEditable(true);
				view.getSourceComboBox().setEditable(true);
					}		
				propChangeFirer.firePropertyChange("graph", null, graph);
				}
			}
		}
	
	private class PathChanged implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			Object source =  view.getSourceComboBox().getSelectedItem();
			Object destination =  view.getDestinationComboBox().getSelectedItem();
			String algorithm = view.getRadioButtonGroup().getSelection().getActionCommand();
			DataModel<GraphDataModel> selectedItem = (DataModel<GraphDataModel>) view.getGraphPickerComboBox().getSelectedItem();
			IntegerWeightedGraph graph = selectedItem.getBody();
			if(source != null && destination != null) {	
				try {
					model.compute(selectedItem.id, (int) source, (int) destination, algorithm);
				} catch (Exception e) {
					printNotice("Error:" + e.getMessage(), Color.RED);
					propChangeFirer.firePropertyChange("pathCleared", null, null);
				}
			}
		}
		
	}
}


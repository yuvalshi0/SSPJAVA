package com.hit.graph.drawer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.model.SPPModel;
import com.hit.model.dm.DataModel;
import com.hit.model.dm.GraphDataModel;

public class GraphDrawerController implements PropertyChangeListener {
	private JGraphPanel panel;
	
	public GraphDrawerController(JGraphPanel panel) {
		super();
		this.panel = panel;		
	}
		
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propName = evt.getPropertyName();
        if("graph".equalsIgnoreCase(propName)) {
        	IntegerWeightedGraph graph = (IntegerWeightedGraph) evt.getNewValue();
        	panel.clearGraph();
        	panel.drawGraph(graph);
        } else if("path".equalsIgnoreCase(propName)) {
        	LinkedList<Integer> path = (LinkedList<Integer>)evt.getNewValue();
        	panel.clearPath();
        	panel.drawPath(path, Color.RED);
        } else if("pathCleared".equalsIgnoreCase(propName)) {
        	panel.clearPath();
        }
	}

}


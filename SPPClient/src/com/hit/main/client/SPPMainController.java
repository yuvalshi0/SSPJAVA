package com.hit.main.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.hit.graph.drawer.GraphDrawerController;
import com.hit.graph.picker.GraphPickerController;
import com.hit.graph.wizard.SPPNewGraphController;
import com.hit.graph.wizard.SPPNewGraphView;
import com.hit.model.SPPModel;

public class SPPMainController {
	private SPPMainView view;
	private SPPModel model;

	public SPPMainController(SPPMainView view,SPPModel model) {
		super();
		this.view = view;
		this.model = model;
		GraphDrawerController drawer = new GraphDrawerController(view.getGraphPanel());
		GraphPickerController controller = new GraphPickerController(model, view.getFormPanel());
		controller.addListener(drawer);
		setUp();
		
	}
	
	private void setUp() {
		view.getNewGraphButton().addActionListener(new NewGraphWizrd());
	}
	
	private class NewGraphWizrd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			SPPNewGraphView newGraphView = new SPPNewGraphView(model);
			GraphDrawerController drawer = new GraphDrawerController(newGraphView.getGraphPanel());
			SPPNewGraphController controller = new SPPNewGraphController(model, newGraphView);
			controller.addListener(drawer);
			newGraphView.setVisible(true);
		}	
	}
	
}

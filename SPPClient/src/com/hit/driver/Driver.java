package com.hit.driver;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.hit.main.client.SPPMainController;
import com.hit.main.client.SPPMainView;
import com.hit.model.SPPModel;

public class Driver {

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            try {
								UIManager.setLookAndFeel(info.getClassName());
							} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
									| UnsupportedLookAndFeelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            break;
				       }
					}
					
					SPPModel model = new SPPModel("localhost",12345);
					SPPMainView view = new SPPMainView();
					SPPMainController contoller = new SPPMainController(view, model);
					
					view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					view.setLocationRelativeTo(null);
					view.setVisible(true);
			}
		});
	}

}

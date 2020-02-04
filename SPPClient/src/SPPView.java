import javax.swing.JFrame;
import javax.swing.JPanel;
import com.hit.drawer.JGraphPanel;
import com.hit.model.DataModel;
import com.hit.model.GraphDataModel;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import org.junit.experimental.theories.FromDataPoints;

import java.awt.Font;



public class SPPView extends JFrame {

	JGraphPanel graphPanel;
	GraphPickerPanel formPanel;
	
	
	public JGraphPanel getGraphPanel() {
		return graphPanel;
	}

	public GraphPickerPanel getFormPanel() {
		return formPanel;
	}

	public SPPView() {
		JLabel TitleLabel = new JLabel("Shortest Path Calculator");
		graphPanel = new JGraphPanel(554, 298);
		formPanel = new GraphPickerPanel();
		
		graphPanel = new JGraphPanel(554,298);
		graphPanel.setBounds(10, 31, 554, 298);
		formPanel.setVisible(true);
		formPanel.setBounds(10, 340, 554, 129);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(10, 11, 554, 14);
		
		getContentPane().add(graphPanel);
		getContentPane().add(formPanel);
		getContentPane().add(TitleLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 519);
		getContentPane().setLayout(null);
		
		
	}


}

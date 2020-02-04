import java.awt.EventQueue;
import javax.swing.JFrame;
import com.hit.model.SPPModel;

public class Driver {

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SPPModel model = new SPPModel("localhost",12345);
					SPPView view = new SPPView();
					GraphDrawerController drawer = new GraphDrawerController(view.getGraphPanel());
					GraphPickerController controller = new GraphPickerController(model, view.getFormPanel());
					controller.addListener(drawer);
					new SPPNewGraphView(model).setVisible(true);;
					view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					view.setLocationRelativeTo(null);
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

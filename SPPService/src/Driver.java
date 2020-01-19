import java.io.IOException;

import com.hit.server.Server;

public class Driver {
	
	public static void main(String[] args) {
		Server server;
		try {
			server = new Server(12345,10);
			new Thread(server).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}

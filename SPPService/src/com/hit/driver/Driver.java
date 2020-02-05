package com.hit.driver;
import java.io.IOException;

import com.hit.server.Server;
import com.hit.util.CLI;

public class Driver {
	
	public static void main(String[] args) {
		Server server;
		try {
			server = new Server(12345,10);
			CLI cli = new CLI(System.in, System.out);
			cli.addPropertyChangeListener(server);
			new Thread(cli).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}

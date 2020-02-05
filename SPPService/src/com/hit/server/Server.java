package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.dm.DataModel;
import com.hit.graph.IGraph;
import com.hit.service.IOService;

public class Server implements Runnable,PropertyChangeListener {

	private ServerSocket serverSocket;
	private Executor executor;
	private int poolSize;
	private int port;
	private boolean power;
	private boolean monitor = false;
	
	public Server(int port, int poolSize) throws IOException {
			this.port = port;
			this.poolSize = poolSize;
			this.power = false;
	}
	
	public Server(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		this.poolSize = 3;
		this.power = false;
	}
	
	@Override
	public void run() {
		executor = Executors.newFixedThreadPool(poolSize);

             Socket socket;
             executor.execute(IOService.getInstance());
             System.out.println("Server is listening on port: " + port);
				System.out.println("Pool Size: " + poolSize);
             try {
				serverSocket = new ServerSocket(port);
				while (power) {
					try {
						socket = serverSocket.accept();
						if(monitor) { 
							System.out.println("New client connected");
						}
						
						executor.execute(new HandleRequest(socket,monitor));
						} catch (IOException e) {
							System.out.println("Server is down");
						} 
					}
           } catch (IOException e) {	
 			} finally {
				try {
					if(serverSocket != null) serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String action =(String) evt.getNewValue();
		
		switch(action) {
		case "start":
			if(power == false) {
				power = true;
				new Thread(this).start();
				break;
			}
			else 
				System.out.println("Server is already ON");
			break;
		case "stop":
			if(power == false) 
				System.out.println("Server is already OFF");
			else {
				try {
					power=false;
					serverSocket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		case "startmonitor":
			if(monitor == true) 
				System.out.println("Monitor already ON");
			else {
				monitor = true;
			}
			break;
		case "stopmonitor":
			if(monitor == false) 
				System.out.println("Monitor already OFF");
			else {
				monitor = false;
			}
			break;
		case "deletedb":
			try {
				IOService.getInstance().writeToFile(new ArrayList<>());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			default:
				System.out.println("Not a valid command");
				break;
		}
	}
	}


package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.dm.DataModel;
import com.hit.graph.IGraph;

public class Server implements Runnable {

	private ServerSocket serverSocket;
	private Executor executor;
	private int poolSize;
	
	public Server(int port, int poolSize) throws IOException {
			this.serverSocket = new ServerSocket(port);
			this.poolSize = poolSize;
	}
	
	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.poolSize = 3;
	}
	
	@Override
	public void run() {
		executor = Executors.newFixedThreadPool(poolSize);

		 System.out.println("Server is listening on port: " + serverSocket.getLocalPort());
		 System.out.println("Pool Size: " + poolSize);
         
		 while (true) {
             Socket socket;
			try {
				socket = serverSocket.accept();
				System.out.println("New client connected");
				executor.execute(
						new HandleRequest<String>(socket));
	             
			} catch (IOException e) {
				e.printStackTrace();
			}    
         }
		}
	}


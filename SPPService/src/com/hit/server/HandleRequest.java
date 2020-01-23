package com.hit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.DijkstraAlgo;
import com.hit.dm.IntegerWeightedGraph;


public class HandleRequest implements Runnable {

	Gson gson = new Gson();
	Gson prettyPrinter = new GsonBuilder().setPrettyPrinting().create();
	private Socket socket;
	
	public HandleRequest(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			HandleAction();
		} catch (IllegalArgumentException | IOException e) {
			sendResponse(
					new Response.Build().Error().setContent(e).create()
				);
		}
	}

	private Request readRequestFromReader() {
		try {
			Request request = gson.fromJson(readSocket(), Request.class);
			return request;
		} catch(JsonParseException e) {
			sendResponse(
				new Response.Build().Error().setContent(e).create()
			);
			return null;
		}
	}

	private String readSocket() {
		BufferedReader reader;
		String input = "";
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			input = reader.readLine();
		} catch (IOException e) {
			sendResponse(
					new Response.Build().Error().setContent(e).create()
				);
		}
		return input;
	}
	
	private void sendResponse(Response response) {
		try { 
		PrintStream output = new PrintStream(socket.getOutputStream());
		 String responceJson = gson.toJson(response);
		 String x = prettyPrinter.toJson(response);
		 System.out.println("Responded to the user:" + x);
		 output.println(responceJson);
		} catch(IOException e) {
			e.printStackTrace(); 
		 }
	}

	private void HandleAction() throws IllegalArgumentException, IOException, NullPointerException {
		Request request = readRequestFromReader();
		String action = request.getHeaders().get("action");
		IDaoController<IntegerWeightedGraph> controller = new IDaoController<IntegerWeightedGraph>();
		controller.setUp();
		switch (action) {
		case "COMPUTE":
			HandleComputeRequest(request);
			break;
		case "POST":
			IntegerWeightedGraph graph = new IntegerWeightedGraph(request.getContent());
			System.out.println("Posting");
			controller.setUp();
			controller.save(graph);
			sendResponse(new Response.Build().Ok().create());
			break;
		case "GET":
			  UUID id = gson.fromJson(request.getContent(), UUID.class); 
			  System.out.println("Getting");
			  controller.setUp(); 
			  sendResponse(new Response.Build().Ok().setContent(controller.find(id)).create());
			break;
		case "DELETE":
			
			  UUID deleteId = gson.fromJson(request.getContent(), UUID.class);
			  System.out.println("Deleting");
			  controller.setUp();
			  controller.delete(deleteId);
			  sendResponse(new Response.Build().Ok().create());
			break;
		default:
			sendResponse(
					new Response.Build().Error().setContent(new IllegalArgumentException("Action not found")).create()
					);
	}
}
	
	private void HandleComputeRequest(Request request) {
		IDaoController<IntegerWeightedGraph> controller = new IDaoController<IntegerWeightedGraph>();
		controller.setUp();
		ShortestPathController shortestPathController = null;
		IntegerWeightedGraph computeGraph;
		ComputeContent data = gson.fromJson(request.getContent(), ComputeContent.class);
		
		if(data.algorithm.equals("bellman-ford")) {
			shortestPathController = new ShortestPathController(new BellmanFordAlgo<Integer,Integer>());
			shortestPathController.setUp();
		} else if (data.algorithm.equals("dijkstra")) {
		shortestPathController = new ShortestPathController(new DijkstraAlgo<Integer,Integer>());
		shortestPathController.setUp();
		} else {
			shortestPathController = new ShortestPathController();
			shortestPathController.setUp();
		}
		String temp;
		try {
			temp = gson.toJson(controller.find(data.id));
			computeGraph = new IntegerWeightedGraph(temp);
			int shortestPath = shortestPathController.compute(computeGraph,data.source,data.destination);
			sendResponse(new Response.Build().Ok().setContent(shortestPath).create());
		} catch (IllegalArgumentException | IOException e) {
			sendResponse(new Response.Build().Error().setContent(e).create());
		}
		
	}

}

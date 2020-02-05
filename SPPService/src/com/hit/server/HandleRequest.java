package com.hit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
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
	private boolean monitor;
	
	public HandleRequest(Socket socket, boolean monitor) {
		this.socket = socket;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		try {
			HandleAction();
		} catch (IllegalArgumentException | IOException e) {
			sendResponse(
					new Response.Build().Error().setContent(e.getMessage()).create()
				);
		}
	}

	private Request readRequestFromReader() {
		try {
			String socketText = readSocket();
			Request request = gson.fromJson(socketText, Request.class);
			if(monitor) {
				System.out.println("Client Request:");
				System.out.println(prettyPrinter.toJson(request));
			}
			return request;
		} catch(JsonParseException e) {
			sendResponse(
				new Response.Build().Error().setContent(e.getMessage()).create()
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
					new Response.Build().Error().setContent(e.getMessage()).create()
				);
		}
		return input;
	}
	
	private void sendResponse(Response response) {
		try { 
		PrintStream output = new PrintStream(socket.getOutputStream());
		 String responceJson = gson.toJson(response);
		
		 if(monitor) {
			 String text = prettyPrinter.toJson(response);
			 System.out.println("Server Response:");
			 System.out.println(text);
				
		 }
		 output.println(responceJson);
		} catch(IOException e) {
			e.printStackTrace(); 
		 } finally {
			try {
				this.socket.close();
			} catch (IOException e) {		
				e.printStackTrace();
			}
		}
	}

	private void HandleAction() throws IllegalArgumentException, IOException, NullPointerException {
		Request request = readRequestFromReader();
		String action = request.getHeaders().get("action");
		IDaoController<IntegerWeightedGraph> controller = new IDaoController<IntegerWeightedGraph>();
		controller.setUp();
		switch (action) {
		case "COMPUTE":
			HandleComputeAction(controller,request.getContent());
			break;
		case "POST":
			HandlePostAction(controller,request.getContent());
			break;
		case "GET":
			HandleGetAction(controller,request.getContent());
			break;
		case "GETALL":
			HandleGetAllAction(controller,request.getContent());
			break;
		case "DELETE":
			HandleDeleteAction(controller,request.getContent());
			break;
		default:
			sendResponse(
					new Response.Build().Error().setContent(new IllegalArgumentException("Action not found")).create()
					);
	}
}
	
	private void HandleGetAllAction(IDaoController<IntegerWeightedGraph> controller, String content) throws IOException {
		Response response = new Response.Build().Ok().setContent(controller.getAll()).create();
		sendResponse(response);
	}

	private void HandlePostAction(IDaoController<IntegerWeightedGraph> controller, String content) throws IOException {
		IntegerWeightedGraph graph = new IntegerWeightedGraph(content);
		controller.setUp();
		controller.save(graph);
		sendResponse(new Response.Build().Ok().setContent(controller.getAll()).create());
	}
	
	private void HandleGetAction(IDaoController<IntegerWeightedGraph> controller, String content) throws IllegalArgumentException, IOException {
		  UUID id = gson.fromJson(content, UUID.class); 
		  controller.setUp(); 
		  sendResponse(new Response.Build().Ok().setContent(controller.find(id)).create());
	}
	
	private void HandleDeleteAction(IDaoController<IntegerWeightedGraph> controller, String content) throws IllegalArgumentException, IOException {
		 	UUID deleteId = gson.fromJson(content, UUID.class);
		  controller.setUp();
		  controller.delete(deleteId);
		  sendResponse(new Response.Build().Ok().create());
	}
	
	private void HandleComputeAction(IDaoController<IntegerWeightedGraph> controller, String content) {
		ShortestPathController shortestPathController = null;
		IntegerWeightedGraph computeGraph;
		ComputeContent data = gson.fromJson(content, ComputeContent.class);
		
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
			LinkedList<Integer> shortestPath = shortestPathController.compute(computeGraph,data.source,data.destination);
			sendResponse(new Response.Build().Ok().setContent(shortestPath).create());
		} catch (IllegalArgumentException | IOException e) {
			sendResponse(new Response.Build().Error().setContent(e.getMessage()).create());
		}
		
	}

}

package com.hit.model;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.swing.event.SwingPropertyChangeSupport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.model.dm.ComputeContent;
import com.hit.model.dm.DataModel;
import com.hit.model.dm.GraphDataModel;
import com.hit.model.dm.Request;
import com.hit.model.dm.Response;

public class SPPModel {
	
	String address;
	private SwingPropertyChangeSupport propChangeFirer;
	int port;
	Gson gson = new Gson();
	LinkedList<Integer> path;
	List<DataModel<GraphDataModel>> graphList;
	
	public SPPModel(String address, int port) {
		this.address = address;
		this.port = port;
		propChangeFirer = new SwingPropertyChangeSupport(this);
	}
	
	 public void addListener(PropertyChangeListener prop) {
	        propChangeFirer.addPropertyChangeListener(prop);
	    }
	
	public Socket connect() throws UnknownHostException, IOException {
			return new Socket(address, port);
	}
	
	public void writeToServer(Socket socket,Request request) throws IOException {
		 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		 String jsonReq = gson.toJson(request);
		 out.println(jsonReq);
	}
	
	public Response readServerResponse(Socket socket) throws Exception {
		 BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 String line = "";
		line = in.readLine();  
		 if(line == "") {
			 throw new IOException("Error reading server response");
		 }
		 Response res = gson.fromJson(line, Response.class);
		 if(res.getHeaders().get("status").equals("ERROR")) {
			 Exception err =  new Exception(res.getContent());
			 throw err;
		 }	 
		return res; 
	}
	
	public GraphDataModel get(UUID id) throws Exception {
		Socket socket = connect();
		String content = gson.toJson(id);
		Request request = new Request.Builder().setAction("GET").setContent(content).build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		return new GraphDataModel(response.getContent());
		
	}
	
	public void post(IntegerWeightedGraph graph) throws Exception {
		Socket socket = connect();
		String content = gson.toJson(graph);
		Request request = new Request.Builder().setAction("POST").setContent(content).build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		Type customType = new TypeToken<List<DataModel<Object>>>(){}.getType();
		List<DataModel<Object>> objectList = gson.fromJson(response.getContent(), customType);
		List<DataModel<GraphDataModel>> oldList = this.graphList;
		this.graphList = convertLists(objectList);
		propChangeFirer.firePropertyChange("graphList", oldList, graphList);
	}
	
	public void delete(UUID id) throws Exception {
		Socket socket = connect();
		String content = gson.toJson(id);
		Request request = new Request.Builder().setAction("DELETE").setContent(content).build();
		writeToServer(socket, request);
		readServerResponse(socket);
	}
	
	public void getAll() throws Exception {
		Socket socket = connect();
		Request request = new Request.Builder().setAction("GETALL").build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		Type customType = new TypeToken<List<DataModel<Object>>>(){}.getType();
		List<DataModel<Object>> objectList = gson.fromJson(response.getContent(), customType);
		
		List<DataModel<GraphDataModel>> oldList = this.graphList;
		this.graphList = convertLists(objectList);
		propChangeFirer.firePropertyChange("graphList", oldList, graphList);
	}
	
	public void compute(UUID id, int source, int destination, String algorithm) throws Exception {
		Socket socket = connect();
		String content = gson.toJson(new ComputeContent(id, source, destination, algorithm));
		Request request = new Request.Builder().setAction("COMPUTE").setContent(content).build();
		writeToServer(socket, request);
		Response response = readServerResponse(socket);
		Type customType = new TypeToken<LinkedList<Integer>>(){}.getType();
		this.path = gson.fromJson(response.getContent(), customType);
		propChangeFirer.firePropertyChange("path", null, path);
	}
	
	//We convert because gson doesn't read the graph well (same problem in the server)
	public List<DataModel<GraphDataModel>> convertLists(List<DataModel<Object>> objectList) {
		List<DataModel<GraphDataModel>> list = new ArrayList<>();
		for (DataModel<Object> data : objectList) {
			String json = gson.toJson(data.getBody());
			GraphDataModel graph = new GraphDataModel(json);
			list.add(new DataModel<GraphDataModel>(data.id,graph,data.name));
		}
		return list;
	}
}

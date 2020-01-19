package com.hit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;

public class HandleRequest<T> implements Runnable {

	Gson gson = new Gson();
	private Socket socket;
	
	public HandleRequest(Socket socket) {
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		Request<T> request = readRequestFromReader();
		try {
			HandleAction(request);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Request<T> readRequestFromReader() {
		Type ref = new TypeToken<Request<DataModel<T>>>() {}.getType();
		Request<T> request = gson.fromJson(readSocket(), ref);
		
		return request;
	}
	
	private String readSocket() {
		BufferedReader reader;
		String output="";
		try {
			reader = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			output = reader.readLine();
			System.out.println(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	private void HandleAction(Request<T> request) throws IllegalArgumentException, IOException {
		//ADD ERROR HANDLING
		String action = request.getHeaders().get("action");
		DataModel<T> content = (DataModel<T>) request.getContent();
		if(action.equals("COMPUTE")) {
			ShortestPathController<T> controller = new ShortestPathController<T>();
		} else {
			IDaoController<T> controller = new IDaoController<T>();
			switch (action) {
			case "POST":
				System.out.println("Posting");
				controller.setUp();
				controller.save(content);
				break;
			case "GET":
				System.out.println("Getting");
				controller.setUp();
				controller.find(content);
				System.out.println(content.getBody());
				break;
			case "DELETE":
				System.out.println("Deleting");
				controller.setUp();
				controller.delete(content);
				break;
			default:
				throw new IllegalArgumentException("Unknown action");
		}
	}
}
}

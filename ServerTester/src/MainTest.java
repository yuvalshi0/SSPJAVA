import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;

public class MainTest {

	public static void main(String[] args) throws Exception {
		simpleComputeReq();
	}
	
	private static void simpleComputeReq() throws Exception, IOException {
		Gson gson = new Gson(); 
		Gson prettyPrinter = new GsonBuilder().setPrettyPrinting().create();
		Socket socket = new Socket("localhost", 12345);
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("action","COMPUTE");
		ComputeContent computeContent = new ComputeContent(UUID.fromString("6f5ec4e6-37f1-4a27-8fb3-ad18186cb7ac"), 1, 3,"dijkstra");
 		
 		String content = gson.toJson(computeContent);
 		Request<String> request = new Request<String>(headers, content);
 		String requestJson = gson.toJson(request);
 		//System.out.println(requestJson);
 		out.println(requestJson);
 		
 		String line = in.readLine();  
 		Response res = gson.fromJson(line, Response.class);
		  System.out.println(prettyPrinter.toJson(res.getContent())); 
 		socket.close();
	}
	
	private static void simplePostReq() throws UnknownHostException, IOException {
		Gson gson = new Gson(); 
		Socket socket = new Socket("localhost", 12345);
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("action","POST");
		headers.put("algorithm", "bellman-ford");
		IntegerWeightedGraph graph = new IntegerWeightedGraph();
		for(int i = 1; i < 6; i++) graph.addNode(i);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, 10));
		graph.addEdge(new IntegerWeightedEdge(1, 5, 3));
		graph.addEdge(new IntegerWeightedEdge(2, 5, 4));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(3, 4, 9));
		graph.addEdge(new IntegerWeightedEdge(4, 3, 7));
		graph.addEdge(new IntegerWeightedEdge(5, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(5, 4, 2));
		graph.addEdge(new IntegerWeightedEdge(5, 3, 8));
 		
 		String content = gson.toJson(graph);
 		Request<String> request = new Request<String>(headers, content);
 		String requestJson = gson.toJson(request);
 		//System.out.println(requestJson);
 		out.println(requestJson);
 		socket.close();
	}
	
	private static void simpleGet() throws Exception, IOException {
		Gson gson = new Gson(); 
		Gson prettyPrinter = new GsonBuilder().setPrettyPrinting().create();
		Socket socket = new Socket("localhost", 12345);
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("action","GET");
		UUID id = UUID.fromString("c4720571-4b28-490e-87bd-2238faf1971a");
 		Request<String> request = new Request<String>(headers, "c4720571-4b28-490e-87bd-2238faf1971a");
 		String requestJson = gson.toJson(request);
 		//System.out.println(requestJson);
 		out.println(requestJson);
 		
 		String line = in.readLine();  
 		Response res = gson.fromJson(line, Response.class);
		  System.out.println(prettyPrinter.toJson(res.getContent())); 
		socket.close();
	}
	}


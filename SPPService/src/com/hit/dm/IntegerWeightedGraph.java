package com.hit.dm;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.graph.AbstractWeightedEdge;

public class IntegerWeightedGraph extends com.hit.algorithm.IntegerWeightedGraph{
	public IntegerWeightedGraph(String json) {
		super();
		deserialize(json);
	}
	
	//Gson doesn't support interfaces and polymorphisms instance, this why we created custom deserializer
	//The function deserializes the graph first to two hashmaps of nodes and edges (as string for key and the node as object)
	//then deserializes the twolist created, creates a graph and returns the nodes
	public void deserialize(String json) throws JsonParseException {
		Gson gson = new Gson();
	    Type listType = new TypeToken<HashMap<String, ArrayList<Object>>>() {}.getType();
 		Type listType2 = new TypeToken<List<IntegerWeightedEdge>>() {}.getType();
 		Type listType3 = new TypeToken<Set<Integer>>() {}.getType();
 		
 		HashMap<String,  ArrayList<Object>> hashGraph = gson.fromJson(json, listType);
		List<AbstractWeightedEdge<Integer,Integer>> edges = gson.fromJson(hashGraph.get("edges").toString(), listType2);
		Set<Integer> nodes = gson.fromJson(hashGraph.get("vertices").toString(), listType3);
		this.addNodes(nodes);
		this.addEdges(edges);
	}
	
}

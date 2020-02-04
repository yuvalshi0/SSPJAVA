package com.hit.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import com.hit.graph.AbstarctWeightedGraph;
import com.hit.graph.AbstractWeightedEdge;

public class BellmanFordAlgo<T,S extends Comparable<S>> extends AbstarctAlgoSPP<T,S> {
	
	private HashMap<T,S> distMap;
	@Override
	public Result<T,S> compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) throws IllegalArgumentException, IOException {
		
		distMap = new HashMap<>();
		pathMap = new HashMap<>();
		
		if(graph.getNodes() == null) {
			throw new IllegalArgumentException("No nodes were in the graph");
		}
		
		if(!graph.getNodes().contains(source) || !graph.getNodes().contains(destination)) {
			throw new IllegalArgumentException("Source or destination node were not found in the graph" );
		}
		int vertexSize = graph.getNodes().size();
		for(T node : graph.getNodes()) {
			distMap.put(node, graph.getMaxToken());
		}
		distMap.put(source,graph.getZeroToken());
		if(graph.getEdges() == null) {
			return new Result<T,S>(computePath(destination), distMap.get(destination));
		}
		for (int i = 1; i < vertexSize; i++) { 		
            	relax(graph);	
		}
		
		verfiyNoMinusCycle(graph);
		if(distMap.get(destination).equals(graph.getMaxToken())) {
			throw new IllegalArgumentException("No path was found");
		}
		return new Result<T,S>(computePath(destination), distMap.get(destination));
	}
	
	 private void relax(AbstarctWeightedGraph<T,S> graph) {
		 for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) { 
         	T u = edge.getSource();
             if(distMap.get(u).compareTo(graph.getMaxToken()) != 0) {
		                T v = edge.getDest(); 
		                S currentPathPlusWeight = edge.addTo(distMap.get(u));
		                S currentPathWeight = distMap.get(v);
		                int isTheNewPathShorter = currentPathPlusWeight.compareTo(currentPathWeight);
		                
		                if (distMap.get(u) != graph.getMaxToken() && isTheNewPathShorter < 0) { 
		                	distMap.put(v, currentPathPlusWeight); 
		                	pathMap.put(v, u);
		            } 
             }
		 }
	 }
	 
	 private void verfiyNoMinusCycle(AbstarctWeightedGraph<T,S> graph) throws IOException {
		 for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) { 
             T u = edge.getSource();
             if(distMap.get(u).compareTo(graph.getMaxToken()) != 0) {
		             T v = edge.getDest(); 
		             S currentPathPlusWeight = edge.addTo(distMap.get(u));
		             S currentPathWeight = distMap.get(v);
		             int isTheNewPathShorter = currentPathPlusWeight.compareTo(currentPathWeight);
		             
		             if (distMap.get(u) != graph.getMaxToken() && isTheNewPathShorter < 0) { 
		             	throw new IOException("Minus cycle detected in the graph");
		         } 
             }
     }
	 }
}
	

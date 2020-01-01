package com.hit.algorithm;

import java.io.IOException;
import java.util.HashMap;

import com.hit.graph.AbstarctWeightedGraph;
import com.hit.graph.AbstractWeightedEdge;

public class BellmanFordAlgo<T,S extends Comparable<S>> extends AbstarctAlgoSPP<T,S> {
	
	private HashMap<T,S> distMap;
	//TODO: consider adding relex to make code more beautiful, meaning adding all the code inside the edge loop to be in a seperate function called relax
	@Override
	public S compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) throws IOException {
		int vertexSize = graph.getNodes().size();
		distMap = new HashMap<>();
		
		if(graph.getNodes() == null) {
			throw new IOException();
		}
		
		for(T node : graph.getNodes()) {
			distMap.put(node, graph.getMaxToken());
		}
		distMap.put(source,graph.getZeroToken());
		
		if(graph.getEdges() == null) {
			return distMap.get(destination);
		}
		
		for (int i = 1; i < vertexSize; i++) { 		
            for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) { 
            	T u = edge.getSource();
                if(distMap.get(u).compareTo(graph.getMaxToken()) != 0) {
		                T v = edge.getDest(); 
		                //bug when edges are not sorted
		                S currentPathPlusWeight = edge.addTo(distMap.get(u));
		                S currentPathWeight = distMap.get(v);
		                int isTheNewPathShorter = currentPathPlusWeight.compareTo(currentPathWeight);
		                
		                if (distMap.get(u) != graph.getMaxToken() && isTheNewPathShorter < 0) { 
		                	distMap.put(v, currentPathPlusWeight); 
		            } 
                }
        }	
		
	}
		
		 for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) { 
             T u = edge.getSource();
             if(distMap.get(u).compareTo(graph.getMaxToken()) != 0) {
		             T v = edge.getDest(); 
		             S currentPathPlusWeight = edge.addTo(distMap.get(u));
		             S currentPathWeight = distMap.get(v);
		             int isTheNewPathShorter = currentPathPlusWeight.compareTo(currentPathWeight);
		             
		             if (distMap.get(u) != graph.getMaxToken() && isTheNewPathShorter < 0) { 
		             	throw new IOException();
		         } 
             }
     }	
		 
		return distMap.get(destination);
	}
}

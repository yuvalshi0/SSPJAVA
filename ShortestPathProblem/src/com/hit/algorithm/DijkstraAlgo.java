package com.hit.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import com.hit.graph.AbstarctWeightedGraph;
import com.hit.graph.AbstractWeightedEdge;

public class DijkstraAlgo<T,S extends Comparable<S>> extends AbstarctAlgoSPP<T,S> {

	private Set<T> settled; 
	private PriorityQueue<T> pq;
	HashMap<T,S> distMap;
	private AbstarctWeightedGraph<T,S> graph;
	
	@Override
	public S compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) throws IOException {
		this.graph = graph;
		pq = new PriorityQueue<T>(); 
		distMap = new HashMap<>();
		settled = new HashSet<>();
		
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
		
		pq.add(source);
		
		while (settled.size() != graph.getNodes().size()) { 
           if(pq.size() == 0) {
        	   pq.add(getNextKey());
           }
           
			T u = pq.remove();
            settled.add(u); 
            relax(u); 
        } 
		
		return distMap.get(destination);
	}
	
	private T getNextKey() {
		for(T key : distMap.keySet()) {
 		   if(!settled.contains(key)) {
 			  return key;
 		   }
 	   }
		return null;
	}

	private void relax(T u) throws IOException {

        for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) {  
            if (edge.getWeight().compareTo(graph.getZeroToken()) < 0) {
            	throw new IOException("Below zero edge detected");
            }
        	
        	if(edge.getSource() == u) {
            	T v = edge.getDest();
            if (!settled.contains(v)) { 
                S currentPathPlusWeight = edge.addTo(distMap.get(u));
                S currentPathWeight = distMap.get(v);
                int isTheNewPathShorter = currentPathPlusWeight.compareTo(currentPathWeight);
             
                if (isTheNewPathShorter < 0) { 
                	distMap.put(v, currentPathPlusWeight); 
            } 
                pq.add(v); 
            } 
           }
        } 
		
	} 
	
}
		
	


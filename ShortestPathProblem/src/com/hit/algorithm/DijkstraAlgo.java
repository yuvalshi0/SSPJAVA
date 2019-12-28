package com.hit.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
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
		
		for(T node : graph.getNodes()) {
			distMap.put(node, graph.getMaxToken());
		}
		distMap.put(source,graph.getZeroToken());
		
		pq.add(source);
		
		while (settled.size() != graph.getNodes().size()) { 
            T u = pq.remove();
            settled.add(u); 
            relax(u); 
        } 
		
		return distMap.get(destination);
	}

	private void relax(T u) throws IOException {
        // All the neighbors of v 
        for (AbstractWeightedEdge<T,S> edge : graph.getEdges()) {  
            if (edge.getWeight().compareTo(graph.getZeroToken()) < 0) {
            	throw new IOException();
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
		
	


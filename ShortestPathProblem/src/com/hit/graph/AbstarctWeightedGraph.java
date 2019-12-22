package com.hit.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstarctWeightedGraph<T,S> implements IGraph<T,S>, Weightable<S> {
	
	protected List<AbstractWeightedEdge<T,S>> edges;
	protected Set<T> vertices;
	
	public List<AbstractWeightedEdge<T,S>> getEdges() {
		return edges;
	}
	
	public Set<T> getNodes() {
		return vertices;
	}
	
	public void addNode(T item) {
		if(vertices == null) {
			vertices = new HashSet<T>();
		}
		vertices.add(
				item
				);
		
	}
		
	 public void addEdge(AbstractWeightedEdge<T,S> edge) {
		if(edges == null) {
			edges = new ArrayList<AbstractWeightedEdge<T,S>>();
		}
		if(checkEdge(edge.getDest(), edge.getSource())) {
			edges.add(edge);
		} else {
			
		}
	}
	 
	private boolean checkEdge(T source, T dest) {
		boolean x = ((vertices.contains(source) && vertices.contains(dest)) && dest != source) ? true : false;
		return x;
	}
}

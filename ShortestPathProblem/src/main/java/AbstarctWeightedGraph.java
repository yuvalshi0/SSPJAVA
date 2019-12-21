package main.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class AbstarctWeightedGraph<T,S> implements IGraph<T,S> {
	
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
	
	 abstract void edgeInsert(T source, T dest, S weight);
	
	 public void addEdge(T source, T dest, S weight) {
		if(edges == null) {
			edges = new ArrayList<AbstractWeightedEdge<T,S>>();
		}
		if(checkEdge(source, dest)) {
			edgeInsert(source, dest, weight);
		} else {
			
		}
	}
	 
	
	
	private boolean checkEdge(T source, T dest) {
		boolean x = ((vertices.contains(source) && vertices.contains(dest)) && dest != source) ? true : false;
		return x;
	}
}

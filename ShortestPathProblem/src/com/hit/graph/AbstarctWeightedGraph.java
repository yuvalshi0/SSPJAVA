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
	
	public void addNodes(Set<T> items) {
		if(vertices == null) {
			vertices = new HashSet<T>();
		}
		vertices.addAll(
				items
				);
		
	}
		
	 public void addEdge(AbstractWeightedEdge<T,S> edge) {
		if(edges == null) {
			this.edges = new ArrayList<AbstractWeightedEdge<T,S>>();
		}
		if(checkEdge(edge.getDest(), edge.getSource())) {
			edges.add(edge);
		}
	}
	 
	 public void addEdges( List<AbstractWeightedEdge<T,S>> edges) {
			if(this.edges == null) {
				this.edges = new ArrayList<AbstractWeightedEdge<T,S>>();
			}
			for(AbstractWeightedEdge<T,S> edge : edges) {
			if(checkEdge(edge.getDest(), edge.getSource())) {
				this.edges.add(edge);
				}
			}
		}
	 
	private boolean checkEdge(T source, T dest) {
		if(vertices == null) return false;
		boolean x = ((vertices.contains(source) && vertices.contains(dest)) && dest != source) ? true : false;
		return x;
	}
}

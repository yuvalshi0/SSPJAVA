package main.java;

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
	
}

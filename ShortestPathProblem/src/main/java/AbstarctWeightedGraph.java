package main.java;

import java.util.List;
import java.util.Set;

abstract class AbstarctWeightedGraph<T,S> implements IGraph<T,S> {
	
	protected List<AbstractWeightedEdge<T,S>> edges;
	protected Set<INode<T>> vertices;
	
}

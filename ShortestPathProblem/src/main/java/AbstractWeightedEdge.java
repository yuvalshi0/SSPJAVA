package main.java;

abstract class AbstractWeightedEdge<T,S> implements IEdge<T,S> {
	private INode<T> source;
	private INode<T> destination;
	private S weight;
}

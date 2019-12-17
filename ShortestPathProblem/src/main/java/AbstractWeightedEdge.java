package main.java;

abstract class AbstractWeightedEdge<T,S> implements IEdge<T> {
	private INode<T> source;
	private INode<T> destination;
	private S weight;
}

package main.java;

public interface IGraph<T,S> {
	public void addNode(T item);
	public void addEdge(AbstractWeightedEdge<T,S> edge);
}

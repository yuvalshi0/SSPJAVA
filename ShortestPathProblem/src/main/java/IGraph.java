package main.java;

public interface IGraph<T,S> {
	public void addNode(T item);
	public void addEdge(INode<T> source, INode<T> dest, S weight);
}

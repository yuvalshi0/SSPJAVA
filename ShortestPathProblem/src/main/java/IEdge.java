package main.java;

public interface IEdge<T,S> {
	public T getSource();
	public T getDest();
	public S getWeight();
}

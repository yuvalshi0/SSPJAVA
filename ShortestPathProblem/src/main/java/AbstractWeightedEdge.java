package main.java;

public abstract class AbstractWeightedEdge<T,S> implements IEdge<T,S> {
	private T source;
	private T destination;
	private S weight;
	
	public AbstractWeightedEdge(T source, T destination, S weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public S getWeight() {
		return weight;
	}
	
}

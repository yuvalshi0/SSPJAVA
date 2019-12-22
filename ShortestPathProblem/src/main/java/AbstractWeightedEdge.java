package main.java;

public abstract class AbstractWeightedEdge<T,S> implements IEdge<T,S> {
	protected T source;
	protected T destination;
	protected S weight;
	
	public AbstractWeightedEdge(T source, T destination, S weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public S getWeight() {
		return weight;
	}
	
	public T getSource() {
		return source;
	}

	public T getDest() {
		return destination;
	}
	
}

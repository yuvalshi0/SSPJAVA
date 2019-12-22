package main.java;

public interface IEdge<T,S> {
	public T getSource();
	public T getDest();
	public S addTo(S add);
	public S getWeight();

}

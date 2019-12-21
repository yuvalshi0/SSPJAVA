package main.java;

public interface Weightable<T> {
	public T getMaxToken();
	public T addTo(T add);
}

package main.java;

public interface IAlgoSPP<T,S> {
	abstract public S compute(AbstarctWeightedGraph<T,S> graph, T source, T destination);
}

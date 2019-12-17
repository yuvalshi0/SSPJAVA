package main.java;

public interface IAlgoSPP<T,S> {
	abstract public T compute(AbstarctWeightedGraph<T,S> graph);
}

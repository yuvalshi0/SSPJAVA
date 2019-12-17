package main.java;

import java.util.List;

abstract class AbstarctWeightedGraph<T,S> implements IGraph<T> {
	private List<AbstarctWeightedGraph<T,S>> edges;
}

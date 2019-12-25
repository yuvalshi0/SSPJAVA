package com.hit.algorithm;

import java.io.IOException;

import com.hit.graph.AbstarctWeightedGraph;

public interface IAlgoSPP<T,S> {
	abstract public S compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) throws IOException;
}

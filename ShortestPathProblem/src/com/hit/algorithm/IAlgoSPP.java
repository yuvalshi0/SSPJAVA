package com.hit.algorithm;

import java.io.IOException;

import com.hit.algorithm.AbstarctAlgoSPP.Result;
import com.hit.graph.AbstarctWeightedGraph;

public interface IAlgoSPP<T,S> {
	abstract public Result<T,S> compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) throws IOException;
}

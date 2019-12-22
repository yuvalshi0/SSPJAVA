package com.hit.algorithm;

import com.hit.graph.AbstarctWeightedGraph;

public class IntegerWeightedGraph extends AbstarctWeightedGraph<Integer,Integer>  {

	@Override
	public Integer getMaxToken() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Integer getZeroToken() {
		return 0;
	}	
}
package com.hit.algorithm;

import com.hit.graph.AbstractWeightedEdge;

public class IntegerWeightedEdge extends AbstractWeightedEdge<Integer, Integer> {

	public IntegerWeightedEdge(Integer source, Integer destination, Integer weight) {
		super(source, destination, weight);
	}

	@Override
	public Integer addTo(Integer add) {
		return (weight + add);
	}

}

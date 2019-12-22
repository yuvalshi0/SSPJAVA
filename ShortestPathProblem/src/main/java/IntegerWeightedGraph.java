package main.java;

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
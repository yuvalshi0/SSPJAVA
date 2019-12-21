package main.java;

public class IntegerWeightedGraph extends AbstarctWeightedGraph<Integer,Integer>  {

	@Override
	void edgeInsert(Integer source, Integer dest, Integer weight) {
		edges.add(new IntegerWeightedEdge(source, dest, weight));
		
	}



	
	
	
	

}
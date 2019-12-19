package main.java;

import java.util.ArrayList;
import java.util.HashSet;

public class WeightedGraph<T,S> extends AbstarctWeightedGraph<T,S>  {

	public WeightedGraph() {
	}
	
	@Override
	public void addNode(T item) {
		if(vertices == null) {
			vertices = new HashSet<T>();
		}
		vertices.add(
				item
				);
		
	}
	
	//TODO: add exception
	@Override
	public void addEdge(T source, T dest, S weight) {
		if(edges == null) {
			edges = new ArrayList<AbstractWeightedEdge<T,S>>();
		}
		if(checkEdge(source, dest)) {
		edges.add(
				new WeightedEdge<T,S>(source,dest,weight)
				);
		} else {
			
		}
	}
	
	private boolean checkEdge(T source, T dest) {
		boolean x = ((vertices.contains(source) && vertices.contains(dest)) && dest != source) ? true : false;
		return x;
	}

}
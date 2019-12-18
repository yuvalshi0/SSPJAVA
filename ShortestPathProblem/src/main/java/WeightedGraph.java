package main.java;

import java.util.ArrayList;
import java.util.HashSet;

public class WeightedGraph<T,S> extends AbstarctWeightedGraph<T,S>  {

	public WeightedGraph() {
	}
	
	@Override
	public void addNode(T item) {
		if(vertices == null) {
			vertices = new HashSet<INode<T>>();
		}
		vertices.add(
				new Node<T>(item) 
				);
		
	}
	//TODO: add exception
	@Override
	public void addEdge(INode<T> source, INode<T> dest, S weight) {
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
	
	private boolean checkEdge(INode<T> source, INode<T> dest) {
		boolean x = (vertices.contains(source) && vertices.contains(dest)) ? true : false;
		return x;
	}

}
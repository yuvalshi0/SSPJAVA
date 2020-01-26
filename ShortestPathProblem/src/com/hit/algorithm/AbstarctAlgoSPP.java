package com.hit.algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class AbstarctAlgoSPP<T,S extends Comparable<S>> implements IAlgoSPP<T,S> {
	HashMap<T,T> pathMap;
	LinkedList<T> path;
	int shortestPath;
	
	LinkedList<T> computePath(T destination) {
		path = new LinkedList<>();
		T current = destination;
		while(current != null) {
			
			path.addFirst(current);
			current = pathMap.get(current);
		}
		
		return path;
	}
	
	public static class Result<T,S> {
		LinkedList<T> path;
		S shortestPath;
		
		public Result(LinkedList<T> path,S shortestPath) {
			this.path = path;
			this.shortestPath = shortestPath;
		}
		
		public S asValue() {
			return shortestPath;
		}
		public LinkedList<T> asPath() {
			return path;
		}
	}
}

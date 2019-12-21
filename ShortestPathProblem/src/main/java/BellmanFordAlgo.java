package main.java;

import java.util.HashMap;

public class BellmanFordAlgo<T,S> extends AbstarctAlgoSPP<T,S> {

	@Override
	public S compute(AbstarctWeightedGraph<T,S> graph, T source, T destination) {
		int vertexSize = graph.getNodes().size();
		HashMap<T,S> distMap = new HashMap<>();
		
		for(T node : graph.getNodes()) {
			distMap.put(node, null);
		}
		
		for (int i = 1; i < vertexSize; ++i) { 
            for (IEdge<T,S> edge : graph.getEdges()) { 
                T u = edge.getSource();
                T v = edge.getDest(); 
                S weight = edge.getWeight(); 
                if (distMap.get(u) != null && distMap.get(u) + weight < distMap.get(u)) 
                    dist[v] = dist[u] + weight; 
            } 
        }
		
		return null;
	}

}

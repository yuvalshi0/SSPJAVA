package com.hit.server;

import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.IAlgoSPP;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.graph.AbstractWeightedEdge;
import com.hit.service.ShortestPathService;

public class ShortestPathController extends AbstractController<Integer> {
	private ShortestPathService service;
	private IAlgoSPP<Integer, Integer> algo;
	
	ShortestPathController(IAlgoSPP<Integer, Integer> algo) {
		this.algo = algo;
	}
	
	ShortestPathController() {
		this.algo = new BellmanFordAlgo<Integer, Integer>();
	}
	
	@Override	
	public void createServices() {
		service = new ShortestPathService(algo);
		
	}
	
	public int compute(IntegerWeightedGraph graph, Integer source, Integer dest) {
		return service.compute(graph, source, dest);
	}

}

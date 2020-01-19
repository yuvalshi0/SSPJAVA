package com.hit.server;

import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.IAlgoSPP;
import com.hit.service.ShortestPathService;

public class ShortestPathController<T> extends AbstractController<T> {
	private ShortestPathService service;
	
	@Override
	public void createServices() {
		service = new ShortestPathService(new BellmanFordAlgo<Integer, Integer>());
		
	}
	
	public void createServices(IAlgoSPP<Integer, Integer> algo) {
		service = new ShortestPathService(algo);
		
	}

}

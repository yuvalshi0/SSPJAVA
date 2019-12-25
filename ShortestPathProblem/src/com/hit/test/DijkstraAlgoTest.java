package com.hit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.DijkstraAlgo;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;

class DijkstraAlgoTest {

	public IntegerWeightedGraph graph;
	DijkstraAlgo<Integer,Integer> algo = new DijkstraAlgo<Integer,Integer>();
	
	@BeforeEach
	void createSimpleGraph() {
		graph = new IntegerWeightedGraph();	
	}
	
	@Test
	void simpleSolutionTest() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 2));
		
		int shortestPath = algo.compute(graph,1,2);
		assertEquals(1, shortestPath);
		
	}
	
	//BUGGG
	@Test
	void relaxFunctionTest() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 4));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 1));
		
		int shortestPath = algo.compute(graph,1,3);
		assertEquals(2, shortestPath);
		
	}

}

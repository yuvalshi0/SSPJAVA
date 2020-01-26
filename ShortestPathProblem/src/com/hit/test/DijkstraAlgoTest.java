package com.hit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
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
		
		int shortestPath = algo.compute(graph,1,2).asValue();
		assertEquals(1, shortestPath);
		
	}

	@Test
	void relaxFunctionTest() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 4));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 1));
		
		int shortestPath = algo.compute(graph,1,3).asValue();
		assertEquals(2, shortestPath);
		
	}
	
	@Test
	void simpleSolutionTestWithMinus() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, -1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 2));
		Assertions.assertThrows(IOException.class, () -> algo.compute(graph,1,2).asValue());
		
	}
	
	@Test
	void VertexWithNoEdgesTest() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(new IntegerWeightedEdge(2, 3, 1));
		int shortestPath= algo.compute(graph,1,2).asValue();
		assertEquals(graph.getMaxToken(), shortestPath);
		
	}
	
	@Test
	void complexGraph() throws IOException {
		for(int i = 1; i < 6; i++) graph.addNode(i);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, 10));
		graph.addEdge(new IntegerWeightedEdge(1, 5, 3));
		graph.addEdge(new IntegerWeightedEdge(2, 5, 4));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(3, 4, 9));
		graph.addEdge(new IntegerWeightedEdge(4, 3, 7));
		graph.addEdge(new IntegerWeightedEdge(5, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(5, 4, 2));
		graph.addEdge(new IntegerWeightedEdge(5, 3, 8));
		assertEquals(6,algo.compute(graph,1,3).asValue());
		
		
	}
	

}

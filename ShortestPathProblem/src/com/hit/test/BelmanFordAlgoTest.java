package com.hit.test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;

class BelmanFordAlgoTest {

	public IntegerWeightedGraph graph;
	BellmanFordAlgo<Integer,Integer> algo = new BellmanFordAlgo<Integer,Integer>();
	
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
	void simpleSolutionTestWithMinus() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(2, 3, -1));

		int shortestPath = algo.compute(graph,1,3).asValue();
		assertEquals(0, shortestPath);
		
	}
	
	@Test
	public void MinusCircleTest() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(2, 3, -5));
		graph.addEdge(new IntegerWeightedEdge(3, 1, -1));
		Assertions.assertThrows(IOException.class, () -> algo.compute(graph,1,2));
	}
	
	@Test
	void simpleSolutionTest2() throws IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, 15));
		graph.addEdge(new IntegerWeightedEdge(2, 1, 17));
		assertEquals(15,algo.compute(graph,1,2).asValue());
		
	}
	
	@Test
	void complexGraph() throws IOException {
		for(int i = 1; i < 6; i++) graph.addNode(i);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, -1));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(3, 4, -3));
		graph.addEdge(new IntegerWeightedEdge(2, 4, 2));
		graph.addEdge(new IntegerWeightedEdge(4, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(4, 5, 5));
		graph.addEdge(new IntegerWeightedEdge(2, 5, 3));
		graph.addEdge(new IntegerWeightedEdge(1, 5, 4));
		
		assertEquals(-2,algo.compute(graph,1,4).asValue());
		
	}
	
	@Test
	void testPath() throws IOException {
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
		
		LinkedList<Integer> pathFromServer = algo.compute(graph,1,3).asPath();
		LinkedList<Integer> pathExpected = new LinkedList<Integer>();
		pathExpected.add(1);
		pathExpected.add(5);
		pathExpected.add(2);
		pathExpected.add(3);
		assertEquals(pathExpected,pathFromServer);
		
	}
	
	@Test
	void noPathTest() throws IOException {
		for(int i = 1; i < 6; i++) graph.addNode(i);
		
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(new IntegerWeightedEdge(1, 2, 15));
		graph.addEdge(new IntegerWeightedEdge(2, 1, 17));
		
		LinkedList<Integer> pathFromServer = algo.compute(graph,3,1).asPath();
		LinkedList<Integer> pathExpected = new LinkedList<Integer>();
		pathExpected.add(1);
		pathExpected.add(5);
		pathExpected.add(2);
		pathExpected.add(3);
		assertEquals(pathExpected,pathFromServer);
		
	}


}

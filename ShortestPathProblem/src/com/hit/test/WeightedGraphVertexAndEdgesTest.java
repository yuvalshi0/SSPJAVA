package com.hit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;

class WeightedGraphVertexAndEdgesTest {
	public IntegerWeightedGraph graph;
	
	@BeforeEach
	void createASimpleGraph() {
		graph = new IntegerWeightedGraph();
	}
	
	@Test
	void addSimpleVertexTest() {
		graph.addNode(5);
		int size = graph.getNodes().size();
		assertEquals(1,size);
	}
	
	@Test
	void addVertexThatAlreadyExistsTest() {
		graph.addNode(5);
		graph.addNode(5);
		int size = graph.getNodes().size();
		assertEquals(1,size);
	}
	
	@Test
	void addSimpleEdgeTest() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(new IntegerWeightedEdge(5, 6, 3));
		int size = graph.getEdges().size();
		assertEquals(1, size);
	}
	
	@Test
	void addSimpleEdgeThatDoesntExistsTest() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(new IntegerWeightedEdge(5, 0, 3));
		int size = graph.getEdges().size();
		assertEquals(0, size);
	}
	
	@Test
	void insertedEdgesTest() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(new IntegerWeightedEdge(5, 6, 3));
		int weight = graph.getEdges().get(0).getWeight();
		assertEquals(3, weight);
	}
	
	@Test
	void insertedEdgesWithThreeEdgesTesy() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(new IntegerWeightedEdge(5, 6, 3));
		graph.addEdge(new IntegerWeightedEdge(6, 5, 3));
		int size = graph.getEdges().size();
		assertEquals(2, size);
	}
	
	

	@Test
	void insertedEdgesWithTheSameDestAndSource() {
		graph.addNode(5);
		graph.addEdge(new IntegerWeightedEdge(5, 5, 3));
		int size = graph.getEdges().size();
		assertEquals(0, size);
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.WeightedGraph;

class WeightedGraphVertexAndEdgesTest {
	public WeightedGraph<Integer, Integer> graph;
	
	@BeforeEach
	void createASimpleGraph() {
		graph = new WeightedGraph<Integer, Integer>();
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
		graph.addEdge(5, 6, 3);
		int size = graph.getEdges().size();
		assertEquals(1, size);
	}
	
	@Test
	void addSimpleEdgeThatDoesntExistsTest() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(5, 0, 3);
		int size = graph.getEdges().size();
		assertEquals(0, size);
	}
	
	@Test
	void insertedEdgesTest() {
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(5, 6, 3);
		int weight = graph.getEdges().get(0).getWeight();
		assertEquals(3, weight);
	}
	

	@Test
	void insertedEdgesWithTheSameDestAndSource() {
		graph.addNode(5);
		graph.addEdge(5, 5, 3);
		int size = graph.getEdges().size();
		assertEquals(0, size);
	}

}

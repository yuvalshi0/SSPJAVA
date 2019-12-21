package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.BellmanFordAlgo;
import main.java.IntegerWeightedGraph;

class BelmanFordAlgoTest {

	public IntegerWeightedGraph graph;
	
	@BeforeEach
	void createSimpleGraph() {
		graph = new IntegerWeightedGraph();
		BellmanFordAlgo algo = new BellmanFordAlgo();
	}
	
	@Test
	void simpleSolutionTest() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 2);
		int shortestPath = algo.compute(1,2);
		assertEquals(1, shortestPath);
		
	}
	
	@Test
	void simpleSolutionTest() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 2);
		graph.addEdge(2, 3, -1);
		int shortestPath = algo.compute(1,2);
		assertEquals(0, shortestPath);
		
	}

}

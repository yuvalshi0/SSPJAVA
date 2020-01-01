import java.util.Random;

import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.DijkstraAlgo;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;

public class Driver {
	
	public static void main(String[] args) {
		IntegerWeightedGraph graph = new IntegerWeightedGraph();
		ShortestPathService service = new ShortestPathService(new BellmanFordAlgo<Integer, Integer>());
		ShortestPathService service2 = new ShortestPathService(new DijkstraAlgo<Integer, Integer>());
		service.write(graph);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		graph.addEdge(new IntegerWeightedEdge(5, 2, 3));
		service.write(graph);
		service2.compute(graph, 1, 5);
		service.compute(graph, 1, 5);
		
	}

}

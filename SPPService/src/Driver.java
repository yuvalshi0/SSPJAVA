import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.IntegerWeightedGraph;

public class Driver {
	
	public static void main(String[] args) {
		IntegerWeightedGraph graph = new IntegerWeightedGraph();
		ShortestPathService service = new ShortestPathService(new BellmanFordAlgo<Integer, Integer>());
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		
		service.write(graph);
	}

}

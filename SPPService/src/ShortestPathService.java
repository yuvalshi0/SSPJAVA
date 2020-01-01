
import java.io.IOException;

import com.hit.algorithm.IAlgoSPP;
import com.hit.graph.AbstarctWeightedGraph;
import com.hit.graph.IGraph;

public class ShortestPathService {
	IAlgoSPP<Integer, Integer> algo;	
	DaoFileImpl<IGraph<?,?>> idao = new DaoFileImpl<IGraph<?,?>>("C:\\Users\\Yuval Shimon\\eclipse-workspace\\SPPJava\\SPPService\\src\\resources\\datasource.json");
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo) {
		this.algo = algo;
	}
	
	public void write(IGraph<?,?> graph) {
		idao.save(graph);
		
	}
	
	public void compute(AbstarctWeightedGraph<Integer,Integer> graph, Integer source, Integer dest) {
		try {
			long startTime = System.nanoTime();
			Integer x = algo.compute(graph, source, dest);
			long stopTime = System.nanoTime();
			System.out.println("Shortest path is: " + x);
			System.out.println("Found shortest path after: " + (stopTime - startTime));

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

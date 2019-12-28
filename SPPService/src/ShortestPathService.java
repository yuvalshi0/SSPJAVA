
import com.hit.algorithm.IAlgoSPP;
import com.hit.graph.IGraph;

public class ShortestPathService {
	IAlgoSPP<Integer, Integer> algo;	
	DaoFileImpl<IGraph<?,?>> idao = new DaoFileImpl<IGraph<?,?>>("./resources/datasource.json");
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo) {
		this.algo = algo;
	}
	
	public void write(IGraph<?,?> graph) {
		idao.save(graph);
	}
}

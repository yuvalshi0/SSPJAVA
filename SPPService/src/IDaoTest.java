import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.BellmanFordAlgo;
import com.hit.algorithm.DijkstraAlgo;
import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.dm.DataModel;
import com.hit.graph.IGraph;
import com.hit.service.ShortestPathService;

class ServiceTest {
	static String filePath = "./src\\resources\\test.json";
	IntegerWeightedGraph graph;
	static ShortestPathService BellmanService;
	static ShortestPathService DijService;
	
	@BeforeAll
	static void buildServices() {
		BellmanService = new ShortestPathService(new BellmanFordAlgo<Integer, Integer>(),filePath);
		DijService = new ShortestPathService(new DijkstraAlgo<Integer, Integer>());
	}
	
	@BeforeEach
	void buildGraph() {
		graph = new IntegerWeightedGraph();
	}
	
	@AfterEach
	void clearList() throws IOException {
		BellmanService.getDao().clear();
	}
	
	@Test
	void basicPathBellmanTest() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 2));
		graph.addEdge(new IntegerWeightedEdge(2, 3, -1));
		
		int shortestPath = BellmanService.compute(graph,1,3);
		assertEquals(0, shortestPath);
	}
	
	@Test
	void basicPathDijTest() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		graph.addEdge(new IntegerWeightedEdge(1, 3, 3));
		graph.addEdge(new IntegerWeightedEdge(2, 3, 1));
		
		int shortestPath = DijService.compute(graph,1,3);
		assertEquals(2, shortestPath);
	}
	
	@Test
	void saveToFileTest() {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		DataModel<IGraph<Integer,Integer>> input = new DataModel<IGraph<Integer,Integer>>(graph);
		BellmanService.getDao().save(input);
		int size = BellmanService.getDao().getListSize();
		assertEquals(1, size);
	}
	
	@Test
	void saveToFileNullTest() {
		BellmanService.getDao().save(null);
		int size = BellmanService.getDao().getListSize();
		assertEquals(1, size);
	}
	
	@Test
	void findByIDTest() throws IllegalArgumentException, IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		DataModel<IGraph<Integer,Integer>> input = new DataModel<IGraph<Integer,Integer>>(graph);
		BellmanService.getDao().save(input);
		DataModel<IGraph<Integer,Integer>> findResult = BellmanService.getDao().find(input.id);
		assertTrue(input.id.equals(findResult.id));
	}
	
	@Test
	void nullIDTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> BellmanService.getDao().find(null));
	}
	 
	@Test
	void itemNotFoundTest() {
		DataModel<IGraph<Integer,Integer>> input = new DataModel<IGraph<Integer,Integer>>(graph);
		BellmanService.getDao().save(input);
		Assertions.assertThrows(IOException.class, () -> BellmanService.getDao().find(2424));
	}
	
	@Test
	void deleteItemTest() throws IllegalArgumentException, IOException {
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addEdge(new IntegerWeightedEdge(1, 2, 1));
		DataModel<IGraph<Integer,Integer>> input1 = new DataModel<IGraph<Integer,Integer>>(graph);
		DataModel<IGraph<Integer,Integer>> input2 = new DataModel<IGraph<Integer,Integer>>(graph);
		BellmanService.getDao().save(input1);
		BellmanService.getDao().save(input2);
		BellmanService.getDao().delete(input1.id);
		int size = BellmanService.getDao().getListSize();
		assertEquals(1, size);
	}
	
	@Test
	void deleteNullTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> BellmanService.getDao().delete(null));
	}
	
	@Test
	void deleteNotFoundTest() {
		DataModel<IGraph<Integer,Integer>> input = new DataModel<IGraph<Integer,Integer>>(graph);
		BellmanService.getDao().save(input);
		Assertions.assertThrows(IOException.class, () -> BellmanService.getDao().delete(215125));
	}

}

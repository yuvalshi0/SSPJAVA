import java.util.UUID;

public class ComputeContent {
	UUID id;
	int source;
	int destination;
	String algorithm;
	
	public ComputeContent(UUID id, int source, int destination, String algorithm) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.algorithm = algorithm;
	}
	
	
}

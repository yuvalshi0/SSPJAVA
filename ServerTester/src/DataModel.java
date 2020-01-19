import java.util.UUID;

public class DataModel<T> {
	public final UUID id;
	private T body;
	
	public DataModel(T body) {
		this.body = body;
		this.id = UUID.randomUUID();
	}
	
	public T getBody() {
		return body;
	}
	
	
}


import java.util.Map;

import com.google.gson.JsonObject;

public class Request<T> {
	private Map<String, String> headers;
	private String content;
	
	
	public Request(Map<String, String> headers, String content) {
		super();
		this.headers = headers;
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
}

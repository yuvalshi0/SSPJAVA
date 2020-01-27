
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
	private Map<String, String> headers;
	private String content;
	
	
	public Response(Map<String, String> headers, String content) {
		this.headers = headers;
		this.content = content;
	}
	
	private Response(Build builder) {
		this.headers = builder.headers;
		this.content = builder.content;
	}

	public String getContent() {
		return content;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	

	public static class Build {
	Gson gson = new Gson();
	private Map<String, String> headers;
	private String content;
	
	public Build() {
	}
	
	public Build Ok() {	
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("status", "OK");
		this.headers = headers;
		return this;
	}
	
	public Build Error() {
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("status", "ERROR");
		this.headers = headers;
		return this;
	}
	
	public Build setContent(Object content) {
		this.content = gson.toJson(content);
		return this;
	}
	
	public Response create() {
		return new Response(this);
	}
}

}
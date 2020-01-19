package com.hit.server;
import java.util.Map;
import com.hit.dm.DataModel;


public class Request<T> {
	private Map<String, String> headers;
	private T content;
	
	
	public Request(Map<String, String> headers, T content) {
		super();
		this.headers = headers;
		this.content = content;
	}

	public T getContent() {
		return content;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
}
package com.hit.dm;

import java.util.UUID;

import com.hit.service.IDService;

public class DataModel<T> {
	public final UUID id;
	private T body;
	
	public DataModel(T body) {
		this.body = body;
		this.id = IDService.getInstance().generateID();
	}
	
	public T getBody() {
		return body;
	}
	
	
}

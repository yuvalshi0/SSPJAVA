package com.hit.dm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.hit.service.IDService;

public class DataModel<T> {
	public final UUID id;
	private T body;
	private final String name;
	String pattern = "HH:mm dd-MM-yyy";
	
	public DataModel(T body) {
		this.body = body;
		this.id = IDService.getInstance().generateID();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		this.name = "Graph #" + IDService.getInstance().getGraphCount() + " ("+simpleDateFormat.format(new Date())+")";
	}
	
	public T getBody() {
		return body;
	}
	
	
}

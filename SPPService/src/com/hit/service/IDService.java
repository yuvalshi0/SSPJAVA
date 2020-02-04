package com.hit.service;

import java.util.UUID;

public class IDService {
	private static IDService service;
	private static int graphCount = 0;
	
	public static IDService getInstance() 
    { 
        if (service == null) 
        	service = new IDService(); 
  
        return service; 
    } 
	
	public UUID generateID() {
		 UUID id = UUID.randomUUID();
		 graphCount++;
		 return id;
	}
	
	public int getGraphCount() {
		return graphCount;
	}
	
}

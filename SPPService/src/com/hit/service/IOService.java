package com.hit.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;

public class IOService implements Runnable {
	
	private static IOService service;
	private Gson gson = new Gson();
	Writer writer;
	Reader reader;
	String filePath = ".//src\\resources\\datasource.json";
	
	
	private IOService() {
	}
	
	public static IOService getInstance() 
    { 
        if (service == null) 
        	service = new IOService(); 
  
        return service; 
    } 

	@Override
	public void run() {
		service = new IOService();
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public synchronized List<?> readFileToList(Type type) throws IOException  {
		List<?> list = null;
		reader = new FileReader(filePath);
		list = gson.fromJson(reader, type);
		reader.close(); 
		if(list==null) {
	        list = new ArrayList<>();
		}	 
			return list;
	}
	
	public synchronized void writeToFile(List<?> list) throws IOException {
			writer  = new FileWriter(filePath);
			gson.toJson(list, writer);
			writer.close(); 
	}
		
}

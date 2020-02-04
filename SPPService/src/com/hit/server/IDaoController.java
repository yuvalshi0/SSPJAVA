package com.hit.server;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.hit.dm.DataModel;
import com.hit.idao.DaoFileImpl;

public class IDaoController<T> extends AbstractController<T>{
	Gson gson = new Gson();
	private DaoFileImpl<T> idao;
	String filePath = ".//src\\resources\\datasource.json";
	
	public IDaoController(String filePath) {
		this.filePath = filePath;
	}
	
	public IDaoController() {}

	@Override
	public void createServices() {
		idao = new DaoFileImpl<T>(filePath);
	}
	
	public void save(T entity) {
		idao.save(new DataModel<T>(entity));
	}
	
	public void save(DataModel<T> entity) {
		idao.save(entity);
	}
	
	public void delete(UUID id) throws IllegalArgumentException, IOException {
		idao.delete(id);
	}
	
	public T find(UUID id) throws IllegalArgumentException, IOException {
		DataModel<T> data = idao.find(id);	
		return data.getBody();
	}
	
	public DataModel<T> findDataModel(UUID id) throws IllegalArgumentException, IOException {
		return idao.find(id);	
	}
	
	public void clear() throws IOException {
		idao.clear();
	}
	
	public int getListSize() throws IOException {
		return idao.getListSize();
	}
	
	public List<DataModel<T>> getAll() throws IOException {
		return idao.getAll();
	}
	
}

package com.hit.server;

import java.io.IOException;

import com.hit.dm.DataModel;
import com.hit.idao.DaoFileImpl;

public class IDaoController<T> extends AbstractController<T>{
	public DaoFileImpl<T> idao;
	String filePath = ".//src\\resources\\datasource.json";
	
	public IDaoController(String filePath) {
		this.filePath = filePath;
	}
	
	public IDaoController() {}

	@Override
	public void createServices() {
		idao = new DaoFileImpl<T>(filePath);
	}
	
	public void save(DataModel<T> entity) {
		idao.save(entity);
	}
	
	public void delete(DataModel<T> entity) throws IllegalArgumentException, IOException {
		idao.delete(entity.id);
	}
	
	public DataModel<T> find(DataModel<T> entity) throws IllegalArgumentException, IOException {
		return idao.find(entity.id);
	}

}

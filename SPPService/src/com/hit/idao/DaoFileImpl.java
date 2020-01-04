package com.hit.idao;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;

public class DaoFileImpl<T> implements IDao<Serializable, T> {

	Gson gson = new Gson();
	String filepath;
	Writer writer;
	Reader reader;
	
	public int getListSize() {
		return readFileToList().size();
	}
	
	public DaoFileImpl(String filePath) {
		this.filepath = filePath;
	}
	
	public void clear() throws IOException {
		writeToFile(new ArrayList<>());
	}
	
	@Override
	public void save(DataModel<T> entity) {	
		try {
			List<DataModel<T>> list = readFileToList();
			list.add(entity);
			writeToFile(list);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Serializable id) throws IllegalArgumentException, IOException {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		List<DataModel<T>> list = readFileToList();
		int size = list.size();
		list.removeIf((x) -> x.id.equals(id));
		
		if(list.size() == size) {
			throw new IOException();
		}
		writeToFile(list);
		
	}
	
	@Override
	public DataModel<T> find(Serializable id) throws IllegalArgumentException, IOException {
		Serializable listid = null;
		
		if (id == null) {
			throw new IllegalArgumentException();
		}
		
		List<DataModel<T>> list = readFileToList();
		for(int i = 0; i < list.size(); i++) {
			listid = list.get(i).id;
			if(listid.equals(id)) {
				return list.get(i);
			}
		}
		throw new IOException();	
		}
	
	private List<DataModel<T>> readFileToList()  {
		Type customType = new TypeToken<List<DataModel<T>>>(){}.getType();
		List<DataModel<T>> list = null;
		
		try {
			reader = new FileReader(filepath);
			list = gson.fromJson(reader, customType);
			reader.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(list==null) {
	        list = new ArrayList<>();
	 }	 
		return list;
	}
	
	private void writeToFile(List<DataModel<T>> list) throws IOException {
			writer  = new FileWriter(filepath);
			gson.toJson(list, writer);
			writer.close();  
	}
	
}

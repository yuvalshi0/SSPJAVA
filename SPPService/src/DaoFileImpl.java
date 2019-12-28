import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import com.google.gson.Gson;

public class DaoFileImpl<T> implements IDao<Serializable, T> {

	Gson gson = new Gson();
	String filepath;
	Writer writer;
	
	public DaoFileImpl(String filePath) {
		this.filepath = filePath;
	}
	
	public DaoFileImpl(String filePath,int capacity) {
		this.filepath = filePath;
	}
	
	@Override
	public void save(T entity) {

		try {
			writer = new FileWriter(filepath);
			gson.toJson(entity,writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(T entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T find(Serializable id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
}

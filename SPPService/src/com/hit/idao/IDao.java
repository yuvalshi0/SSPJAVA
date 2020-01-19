package com.hit.idao;
import java.io.IOException;
import java.io.Serializable;

import com.hit.dm.DataModel;

public interface IDao<ID extends Serializable, T> {
	void save(T entity) ;
	void delete(ID id) throws IllegalArgumentException, IOException;
	T find(ID id) throws IllegalArgumentException, IOException;
}

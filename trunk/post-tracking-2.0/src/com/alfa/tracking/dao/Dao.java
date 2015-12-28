package com.alfa.tracking.dao;

import java.util.List;

public interface Dao <T> {

	void save(T t);
	void delete(Long id);
	void update(T t);
	List<T> listAll();
	T findById(Long id);
	Class<T> getClasse();
}

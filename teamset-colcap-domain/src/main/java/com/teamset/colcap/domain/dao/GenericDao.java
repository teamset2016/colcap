package com.teamset.colcap.domain.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T, ID extends Serializable> {
	
	
	Serializable add(final T entity);
	
	void update(final T entity);


	void delete(final T entity);
	
	@SuppressWarnings("unchecked")
	void delete(final ID ... id);

	T get(final ID id);
	
	List<T> findAll();
	
	T getOne();
	
	public void evict(final T entity);
}
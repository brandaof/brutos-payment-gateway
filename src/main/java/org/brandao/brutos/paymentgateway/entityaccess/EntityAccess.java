package org.brandao.brutos.paymentgateway.entityaccess;

import java.util.List;

public interface EntityAccess<T> {

	void save(T value) throws EntityAccessException;
	
	void update(T value) throws EntityAccessException;
	
	void delete(int id) throws EntityAccessException;
	
	T findById(int id) throws EntityAccessException;
	
	List<T> findAll() throws EntityAccessException;

	void flush() throws EntityAccessException;
	
}

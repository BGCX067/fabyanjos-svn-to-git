package com.alfa.tracking.dao;

import java.util.List;

import com.alfa.tracking.model.Objeto;

public interface DaoObjeto extends Dao<Objeto> {
	
	List<Objeto> listOpen();
	List<Objeto> listOpenAndTrackable();

}

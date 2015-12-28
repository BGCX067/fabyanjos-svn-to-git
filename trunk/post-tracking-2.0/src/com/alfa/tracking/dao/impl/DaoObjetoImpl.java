package com.alfa.tracking.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.alfa.tracking.dao.DaoObjeto;
import com.alfa.tracking.model.Objeto;

public class DaoObjetoImpl extends DaoImpl<Objeto> implements DaoObjeto {

	@Override
	public Class<Objeto> getClasse() {
		return Objeto.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Objeto> listOpen() {
		Query q = em.createQuery("select o from " + getClasse().getName() + " o where o.open = :open");
		q.setParameter("open", Boolean.TRUE);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Objeto> listOpenAndTrackable() {
		Query q = em.createQuery("select o from " + getClasse().getName() + " o where o.open = :open and o.numero is not null ");
		q.setParameter("open", Boolean.TRUE);
		return q.getResultList();
	}
}

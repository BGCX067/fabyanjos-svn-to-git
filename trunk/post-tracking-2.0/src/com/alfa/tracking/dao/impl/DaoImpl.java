package com.alfa.tracking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.alfa.tracking.dao.Dao;

public abstract class DaoImpl<T> implements Dao<T> {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	protected EntityManager em;

	public DaoImpl() {
		em = emfInstance.createEntityManager();
	}

	public void delete(Long id) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			T t = findById(id);
			em.remove(t);
			transaction.commit();
		} catch (Exception e) {
			 e.printStackTrace();
             transaction.rollback();
		}
	}

	public T findById(Long id) {
		return em.find(getClasse(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		Query q = em.createQuery("select e from " + getClasse().getName() + " e");
		return q.getResultList();
	}

	public void save(T t) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(t);
			transaction.commit();
		} catch (Exception e) {
			 e.printStackTrace();
             transaction.rollback();
		}
	}

	public void update(T t) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(t);
			transaction.commit();
		} catch (Exception e) {
			 e.printStackTrace();
             transaction.rollback();
		}
	}
}

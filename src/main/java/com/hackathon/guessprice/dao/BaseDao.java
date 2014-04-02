/*
 * 
 * Copyright (c) Hewlett Packard Company All rights reserved.
 * Shanghai, China.
 * 
 */

package com.hackathon.guessprice.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

public class BaseDao {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T queryById(Class<T> entityClass, Serializable id) {
		return em.find(entityClass, id);
	}
 
	/**
	 * @param entityClass
	 */
	public void delete(Object ... entityObj) {
		for (Object o : entityObj) {
			if (o != null) {
				em.remove(o);
			}
		}
	}

	/**
	 * @param jpqlString
	 * @return
	 */
	public int queryCount(String jpqlString) {
		Query query = em.createQuery(jpqlString);
		return Integer.valueOf(String.valueOf(query.getSingleResult()));
	}

	/**
	 * @param jpqlString
	 * @param paramValues
	 * @return
	 */
	public int queryCount(String jpqlString, Object[] paramValues) {
		Query queryObject = em.createQuery(jpqlString);
		for (int i = 0; i < paramValues.length; i++) {
			queryObject.setParameter(i + 1, paramValues[i]);
		}
		return Integer.valueOf(String.valueOf(queryObject.getSingleResult()));
	}

	/**
	 * @param jpqlString
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	public int queryCount(String jpqlString, String[] paramNames,
			Object[] paramValues) {
		Query queryObject = null;
		if (paramNames.length != paramValues.length) {
			throw new IllegalArgumentException();
		}
		else
		{
			queryObject = em.createQuery(jpqlString);
			for(int index = 0; index < paramNames.length; index++)
			{
				if(paramNames[index]==null && paramValues[index]==null)
				{
					throw new IllegalArgumentException();
				}
				else
				{
					queryObject.setParameter(paramNames[index], paramValues[index]);
				}
			}
		}
		
		return Integer.valueOf(String.valueOf(queryObject.getSingleResult()));
	}

	/**
	 * @param jpqlString
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryPage(String jpqlString, int start, int pageSize) {
		Query query = em.createQuery(jpqlString);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	/**
	 * @param jpqlString
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryPage(String jpqlString, Object[] paramValues, int start,
			int pageSize) {
		Query query = em.createQuery(jpqlString);
		for (int i = 0; i < paramValues.length; i++) {
			query.setParameter(i + 1, paramValues[i]);
		}
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	/**
	 * @param jpqlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryAll(String jpqlString) {
		Query query = em.createQuery(jpqlString);
		return query.getResultList();
	}

	/**
	 * @param queryName
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryAll(String queryName, Object[] paramValues) {
		Query queryObject = em.createQuery(queryName);
		for (int i = 0; i < paramValues.length; i++) {
			queryObject.setParameter(i + 1, paramValues[i]);
		}
		return queryObject.getResultList();
	}

	/**
	 * @param queryName
	 * @param paramNames
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryAll(String queryName, String[] paramNames,
			Object[] paramValues) {
		Query queryObject = null;
		if (paramNames.length != paramValues.length) {
			throw new IllegalArgumentException();
		}
		else
		{
			queryObject = em.createQuery(queryName);
			
			for(int index = 0; index < paramNames.length; index++)
			{
				if(paramNames[index]==null && paramValues[index]==null)
				{
					throw new IllegalArgumentException();
				}
				else
				{
					queryObject.setParameter(paramNames[index], paramValues[index]);
				}
			}
		}
		return queryObject.getResultList();
	}

	/**
	 * The entityObj will not be deleted from memory if you do not invoke flush
	 * or the end of transaction
	 * 
	 * @param entityObj
	 */
	public void deleteWithFlush(Object entityObj) {
		delete(entityObj);
		em.flush();
	}

	/**
	 * @param <T>
	 * @param entityList
	 */
	public <T> void deleteAll(List<T> entityList) {
		if (entityList != null) {
			for (T entity : entityList) {
				if (entity != null) {
				em.remove(entity);
				}
			}
		}	
	}
	
	public <T> void deleteAllWithFlush(List<T> entityList) {
		deleteAll(entityList);
		em.flush();
	}

	/**
	 * @param module
	 */
	/**
	 * @param module
	 */
	public void save(Object module) {
		em.persist(module);
	}


	/**
	 * @param entityList
	 */
	public <T> void saveAll(List<T> entityList) {
		for (int i = 0; i < entityList.size(); i++) {
			em.persist(entityList.get(i));
		}
	}
	
	/**
	 * This function will return the record number what have been impacted.
	 * 
	 * @param jpqlString
	 * @param paramValues
	 * @return
	 */
	public int updateAll(String jpqlString, Object[] paramValues) {
		Query updateObject = em.createQuery(jpqlString);
		for (int i = 0; i < paramValues.length; i++) {
			updateObject.setParameter(i + 1, paramValues[i]);
		}
		return updateObject.executeUpdate();
	}

	/**
	 * This function will return the record number what have been impacted.
	 * 
	 * @param jpqlString
	 * @param paramValues
	 * @return
	 */
	public int updateAll(String jpqlString) {
		Query updateObject = em.createQuery(jpqlString);
		return updateObject.executeUpdate();
	}

	/**
	 * 
	 * @param queryName
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> fuzzyQuery(String queryName, Object[] paramValues) {

		String realQueryName = queryName;
		for (int i = 0; i < paramValues.length; i++) {
			String paramValue = String.valueOf(paramValues[i]);
			if (StringUtils.isEmpty(paramValue)) {
				// get the value name
				int index = realQueryName.indexOf("?" + String.valueOf(i + 1));
				String tempStr = realQueryName.substring(0, index);
				String[] tempStrArray = tempStr.split(" ");
				String realValueName = tempStrArray[tempStrArray.length - 2];
				realValueName = realValueName.trim();
				// append value is null for it.
				realValueName = " or " + realValueName + " is null ";
				realQueryName = realQueryName.substring(0, index + 2)
						+ realValueName + realQueryName.substring(index + 2);
			}

		}
		Query queryObject = em.createQuery(realQueryName);
		for (int j = 0; j < paramValues.length; j++) {
			queryObject.setParameter(j + 1, "%" + paramValues[j] + "%");
		}
		return queryObject.getResultList();

	}

	/**
	 * 
	 */
	public void flush() {
		em.flush();
	}
	
	/**
	 * This method updates/merges the entity in the persistence context. 
	 * @param module _ The entity to be merged.
	 * @return - Returns the attached entity after merge.
	 */
	public <T> T update(T module) {
		return em.merge(module);
	}


	/**
	 * This method returns a list of objects using named query.
	 * @param queryId - Named query id
	 * @param paramNames - Array of parameter names
	 * @param paramValues - Array of parameter values
	 * @return - Returns a list of objects 
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultUsingNamedQuery(String queryId, String[] paramNames,
			Object[] paramValues) {
		Query queryObject = prepareQueryObjectUsingNamedQuery(queryId, paramNames, paramValues);
		
		return queryObject.getResultList();
	}

	/**
	 * This method returns a count of objects that match the criteria in the named query.
	 * @param queryId - Named query id
	 * @param paramNames - Array of parameter names
	 * @param paramValues - Array of parameter values
	 * @return - Returns a count of objects 
	 */
	public int queryCountUsingNamedQuery(String queryId, String[] paramNames,
			Object[] paramValues) throws Exception {
		Query queryObject = prepareQueryObjectUsingNamedQuery(queryId, paramNames, paramValues);		
		
		int count = 0; 
		if(queryObject.getSingleResult()==null)
		{
			throw new Exception("queryCountUsingNamedQuery in BaseDao fetch the result set as null");
		}
		else
		{
			count = ((BigDecimal)queryObject.getSingleResult()).intValue();
		}
		
		return count;
	}
	
	/**
	 * This method executes the update query with specified parameters.
	 * @param queryId - Named query id
	 * @param paramNames - Array of parameter names
	 * @param paramValues - Array of parameter values
	 * @return - Returns the number of rows updated.
	 */
	public int updateUsingNamedQuery(String queryId, String[] paramNames,
			Object[] paramValues) {
		Query queryObject = prepareQueryObjectUsingNamedQuery(queryId, paramNames, paramValues);		
		
		return queryObject.executeUpdate();
	}
	
	private Query prepareQueryObjectUsingNamedQuery(String queryId, String[] paramNames,
			Object[] paramValues)
	{
		Query queryObject = null;
		if (paramNames!=null && paramValues!=null && paramNames.length != paramValues.length) {
			throw new IllegalArgumentException();
		}
		else
		{
			queryObject = em.createNamedQuery(queryId);
			
			if(paramNames!=null && paramValues!= null)
			{
				for(int index = 0; index < paramNames.length; index++)
				{
					if(paramNames[index]==null && paramValues[index]==null)
					{
						throw new IllegalArgumentException();
					}
					else
					{
						queryObject.setParameter(paramNames[index], paramValues[index]);
					}
				}
			}
		}
		
		return queryObject;
	}
	
		
	/**
	 * Exposes the Hibernate session's evict method as detach which is not available
	 * in JPA 1 but available in JPA2
	 * @param entity to detach
	 */
	public void detach(Object entity) {
		Session session = (Session) em.getDelegate();
		session.evict(entity);
	}
	
}

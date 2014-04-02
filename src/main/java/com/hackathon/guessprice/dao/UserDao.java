package com.hackathon.guessprice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hackathon.guessprice.entity.User;

@Repository
public class UserDao extends BaseDao {

//	@PersistenceContext
//	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		Query query = this.getEntityManager().createQuery("from User");
		return query.getResultList();
	}
}

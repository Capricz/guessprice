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

	public int countUsers() {
		String jpql = "SELECT COUNT(*) FROM User WHERE role = 1";
		int count = queryCount(jpql);
		return count;
	}

	public List<Object[]> findRegionCountList() {
		String sql = " SELECT REGION,COUNT(REGION) FROM USER WHERE ROLE <> 0 GROUP BY REGION ";
		Query query = this.getEntityManager().createNativeQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public User findUser(String username, String password) {
		String jpql = " from User where userName = :username and password = :password ";
		List<User> list = this.getEntityManager().createQuery(jpql).getResultList();
		return (User) (list.isEmpty()?null:list.get(0));
	}

	@SuppressWarnings("unchecked")
	public List<User> findUserByName(String username) {
		String jpql = " from User where userName = :userName ";
		Query query = this.getEntityManager().createQuery(jpql);
		query.setParameter("userName", username);
		return query.getResultList();
	}

	public User finUserById(int userId) {
		return this.getEntityManager().find(User.class, userId);
	}
}

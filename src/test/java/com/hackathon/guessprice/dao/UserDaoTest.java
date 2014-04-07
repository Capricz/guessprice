package com.hackathon.guessprice.dao;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.guessprice.MySQLConfig;
import com.hackathon.guessprice.dao.UserDao;
import com.hackathon.guessprice.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MySQLConfig.class}, loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class UserDaoTest {
	
	@Autowired
	public UserDao userDao;
	
	/*@Test
	public void testQueryAll(){
		List<User> list = userDao.findAll();
		assertNotNull(list);
		assertEquals(6, list.size());
	}*/
	
	@Test
//	@Transactional
	public void testAddUser(){
		User u = new User();
		u.setUserName("user");
		u.setPassword("user");
		u.setRegion("EMEA");
		u.setRole(1);
		userDao.save(u);
//		int count = userDao.queryCount("select count(*) from User");
//		assertEquals(7, count);
	}
	
	@Test
//	@Transactional
	public void testDeleteUser(){
		User u = userDao.findUser("user", "user");
		if(u!=null){
			userDao.delete(u);
		}
	}
}

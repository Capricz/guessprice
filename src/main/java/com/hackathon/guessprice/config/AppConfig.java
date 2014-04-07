package com.hackathon.guessprice.config;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.hackathon.guessprice.service.UserService;

@Configuration
@PropertySource(value={"classpath:MySQL.properties"})
@ComponentScan("com.hackathon.guessprice")
public class AppConfig {
	
	private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String URL = "jdbc.url";
	private static final String USERNAME = "jdbc.username";
	private static final String PASSWORD = "jdbc.password";
	
	@Autowired
	public Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
		ds.setUrl(env.getProperty(URL));
		ds.setUsername(env.getProperty(USERNAME));
		ds.setPassword(env.getProperty(PASSWORD));
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceProviderClass(HibernatePersistence.class);
		em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		return em;
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	/*@Bean
	public UserService userService(){
		return new UserService();
	}*/
}

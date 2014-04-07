package com.hackathon.guessprice;

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
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource(value={"classpath:MySQL.properties"})
@ComponentScan("com.hackathon.guessprice.dao")
//@Profile("dev")
public class MySQLConfig {
	
	private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String URL = "jdbc.url";
	private static final String USERNAME = "jdbc.username";
	private static final String PASSWORD = "jdbc.password";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	
//	public String driverName = "com.mysql.jdbc.Driver";
//	public String url = "jdbc:mysql://localhost:3306/test";
//	public String user = "root";
//	public String pass = "root";
//	public String dialect = "org.hibernate.dialect.MySQLDialect";
//	public String hbm2ddl = "update";
	
	@Autowired
	public Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		System.out.println("In dataSource()...");
		System.out.println(DRIVER_CLASS_NAME + " = "+env.getProperty(DRIVER_CLASS_NAME));
		System.out.println(URL + " = "+env.getProperty(URL));
		System.out.println(USERNAME + " = "+env.getProperty(USERNAME));
		System.out.println(PASSWORD + " = "+env.getProperty(PASSWORD));
		System.out.println(DRIVER_CLASS_NAME + " = " + env.getProperty(DRIVER_CLASS_NAME));
		
		ds.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
		ds.setUrl(env.getProperty(URL));
		ds.setUsername(env.getProperty(USERNAME));
		ds.setPassword(env.getProperty(PASSWORD));
		
//		ds.setDriverClassName(driverName);
//		ds.setUrl(url);
//		ds.setUsername(user);
//		ds.setPassword(pass);
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		System.out.println("In entityManagerFactoryBean()...");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceProviderClass(HibernatePersistence.class);
//		em.setPackagesToScan("com.hackathon.guessprice.entity");
		em.setPersistenceXmlLocation("classpath:persistence.xml");
		em.setPersistenceUnitName("guessprice");
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaDialect(new HibernateJpaDialect());
		return em;
//		em.setPackagesToScan(new String[]{"com.zliang.mySprJpa"});
//		em.setPersistenceUnitName("persistenceUnit");
//		em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
//		Properties prop = new Properties();
//		prop.setProperty(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
//		prop.setProperty("hibernate.dialect", dialect);
//		prop.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		em.setJpaProperties(additionalProperties());
//		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	}
	
	private JpaVendorAdapter jpaVendorAdapter() {
		System.out.println("In jpaVendorAdapter()...");
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabasePlatform(env.getProperty(HIBERNATE_DIALECT));
		return null;
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		System.out.println("In txManager()...");
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}
	
	/*@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}*/

	/*
	
	final Properties additionalProperties() {
		return new Properties() {
			{
//				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", dialect);

				// setProperty("hibernate.globally_quoted_identifiers", "true");
				// note: necessary in launchpad-storage, but causing problems here
			}
		};
	}*/
	
}

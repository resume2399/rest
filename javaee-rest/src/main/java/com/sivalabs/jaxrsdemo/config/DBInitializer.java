package com.sivalabs.jaxrsdemo.config;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jaxrsdemo.domain.User;


/**
 * @author Siva
 *
 */
@Singleton
@Startup
@DataSourceDefinition(
	 name = "java:app/myDS",
     className = "com.mysql.jdbc.jdbc2.optional.MysqlXADataSource",
     portNumber = 3306,
     serverName = "localhost",
     databaseName = "test",
     user = "root",
     password = "admin")
public class DBInitializer
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@PostConstruct
	void init()
	{
		User user1 = new User(null, "admin", "admin@gmail.com","admin", new Date());
		User user2 = new User(null, "siva", "siva@gmail.com","siva", new Date());
		
		try
		{
			em.persist(user1);
			em.persist(user2);
			logger.debug("************* Inserted sample users *****************");
		} catch (Exception e)
		{
			//e.printStackTrace();
			logger.error("Error in Initializing Seed Data: "+e.getMessage());
		}
		
	}
}

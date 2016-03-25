package com.audien.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionPoolTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolTest.class);


	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationCtx.xml");
		
		logger.info("context.toString():"+context.toString());
		
		DataSource masterDataSource = (DataSource) context.getBean("masterDataSource");
		logger.info("masterDataSource.toString():"+masterDataSource.toString());
		
		DataSource slaveDataSource = (DataSource) context.getBean("slaveDataSource");
		logger.info("slaveDataSource.toString():"+slaveDataSource.toString());
		
	}

}

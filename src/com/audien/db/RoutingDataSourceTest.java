package com.audien.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class RoutingDataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceTest.class);
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationCtx.xml");
		DataSourceTransactionManager txManger = (DataSourceTransactionManager) context.getBean("txManager");
		
		TransactionTemplate template = (TransactionTemplate) context.getBean("transactionTemplate");
		
		DataSource dataSource = (DataSource) context.getBean("setDataSource");
		logger.info("RoutingDataSourceTest.dataSource:"+dataSource.toString());
		
		try {
			Connection connection =  dataSource.getConnection();
			DatabaseMetaData dmd = connection.getMetaData();
			String url = dmd.getURL();
			
			logger.info(url );
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

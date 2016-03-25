package com.audien.db.replicationdriver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class ReplicationDataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(ReplicationDataSourceTest.class);
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:replicationDriverContext.xml");
		DataSource dataSource = (DataSource) context.getBean("replicationDataSource");
		
		readMaster(dataSource);
		readSlave(dataSource);
	}
	
	private static void readMaster(DataSource dataSource) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection =  dataSource.getConnection();
			
			stmt = connection.createStatement();
			String sql;
			sql = "select * from tbl_member ";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				logger.info("readMaster: "+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
			}
			
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void readSlave(DataSource dataSource) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection =  dataSource.getConnection();
			connection.setReadOnly(true);
			stmt = connection.createStatement();
			String sql;
			sql = "select * from tbl_member ";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				logger.info("readSlave: "+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
			}
			
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

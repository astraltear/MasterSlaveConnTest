package com.audien.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PureConnectionTest {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.60.231:13306/audien";
//	 static final String DB_URL = "jdbc:mysql://192.168.60.232:13306/audien";

	static final String USER = "test";
	static final String PASS = "test";

	public static void main(String[] args) {

		pureReadOnlyFalseConnect();
		pureReadOnlyTrueConnect();

	}
	
	private static void pureReadOnlyFalseConnect() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("pureReadOnlyFalseConnect Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("pureReadOnlyFalseConnect before Setting conn.isReadOnly():" + conn.isReadOnly());
			System.out.println("pureReadOnlyFalseConnect after Setting conn.isReadOnly():" + conn.isReadOnly());

			System.out.println("pureReadOnlyFalseConnect Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "insert into master_create_1(col2,col3) values('slave','slave text')";
			int re = stmt.executeUpdate(sql);

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void pureReadOnlyTrueConnect() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("pureReadOnlyTrueConnect Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("pureReadOnlyTrueConnect before Setting conn.isReadOnly():" + conn.isReadOnly());
			conn.setReadOnly(true);
			System.out.println("pureReadOnlyTrueConnect after Setting conn.isReadOnly():" + conn.isReadOnly());

			System.out.println("pureReadOnlyTrueConnect Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "insert into master_create_1(col2,col3) values('slave','slave text')";
			int re = stmt.executeUpdate(sql);

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

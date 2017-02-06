package com.agm.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/rage";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String MYSQL_PACKAGE = "com.mysql.jdbc.Driver";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(MYSQL_PACKAGE);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

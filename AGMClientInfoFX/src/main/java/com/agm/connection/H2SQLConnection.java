package com.agm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2SQLConnection {
	
	private static final String DB_URL = "jdbc:h2:~/agm";
	private static final String USERNAME = "rage";
	private static final String PASSWORD = "";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnectionObjects(ResultSet rs, Statement ps) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

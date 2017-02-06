package com.agm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.agm.connection.MySQLConnection;
import com.agm.dao.Dao;
import com.agm.model.Contact;
import com.agm.model.SystemUser;

public class DaoImpl extends MySQLConnection implements Dao {

	private Connection conn = this.getConnection();
	
	@Override
	public SystemUser getSystemUserByLogin(String login) {
		final String query = "Select user_id from user where login = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = this.conn.prepareStatement(query);
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<SystemUser> getAllSystemUsers() {
		final String query = "Select user_id from user";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("USER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}

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
		final String query = "Select u.user_id, u.login, u.password "
				+ " , u.created_by, u.created_date, u.last_mod_by, u.last_mod_date "
				+ " , c.first_name, c.last_name, c.primary_contact_number, c.secondary_contact_number"
				+ " , c.created_by, c.created_date, c.last_mod_by, c.last_mod_date "
				+ " From Rage.User u "
				+ "  Left Join Rage.Contact c on c.contact_id = u.contact_id"
				+ " Where u.login = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = this.conn.prepareStatement(query);
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				return new SystemUser(rs);
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

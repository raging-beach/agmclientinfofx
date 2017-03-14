package com.agm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agm.connection.H2SQLConnection;
import com.agm.dao.Dao;
import com.agm.model.Contact;
import com.agm.model.SystemUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoImpl extends H2SQLConnection implements Dao {

	private Connection conn = this.getConnection();
	
	@Override
	public SystemUser getSystemUserByLogin(String login) {
		final String query = "Select u.user_id, u.login, u.password "
				+ " , u.created_by, u.created_date, u.last_mod_by, u.last_mod_date "
				+ " , c.first_name, c.last_name, c.primary_contact_number, c.secondary_contact_number"
				+ " , c.created_by, c.created_date, c.last_mod_by, c.last_mod_date "
				+ " From User u "
				+ "  Left Join Contact c on c.contact_id = u.contact_id"
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
	public ObservableList<SystemUser> getAllSystemUsers() {
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
	public ObservableList<Contact> getAllContacts() {
		final String query = "Select contact_id, first_name, last_name, primary_contact_number"
				+ ", secondary_contact_number, created_by, created_date, last_mod_by, last_mod_date"
				+ " From Contact ";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			final ObservableList<Contact> contacts = FXCollections.observableArrayList();
			ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				contacts.add(new Contact(rs));
			}
			return contacts;
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnectionObjects(rs, ps);
		}
		return null;
	}

	@Override
	public void saveContact(Contact contact, String userLogin) {
		final StringBuffer sql = new StringBuffer();
		if (contact.getId() == null || contact.getId() > 0 == false) {
			//Update
			sql.append("Insert into Contact (first_name, last_name, primary_contact_number");
			sql.append(", secondary_contact_number, created_by, created_date)");
			sql.append(" Values (?, ?, ?, ?, ?, now()) ");
			PreparedStatement ps = null;
			
			try {
				ps = this.conn.prepareStatement(sql.toString());
				ps.setString(1, contact.getFirstName());
				ps.setString(2, contact.getLastName());
				ps.setString(3, contact.getContactNumber());
				ps.setString(4, contact.getSecondaryContactNumber());
				ps.setString(5, userLogin);
				ps.executeUpdate();
				this.closeConnectionObjects(null, ps);
			} catch (SQLException e) {
				e.printStackTrace();
				this.closeConnectionObjects(null, ps);
			}

		} else {
			//Create New
			sql.append("Update Contact ");
			sql.append("Set first_name = ?, last_name = ?, primary_contact_number = ?");
			sql.append(", secondary_contact_number = ?, last_mod_by = ?, last_mod_date = now() ");
			sql.append("Where contact_id = ?");
			PreparedStatement ps = null;
			
			try {
				ps = this.conn.prepareStatement(sql.toString());
				ps.setString(1, contact.getFirstName());
				ps.setString(2, contact.getLastName());
				ps.setString(3, contact.getContactNumber());
				ps.setString(4, contact.getSecondaryContactNumber());
				ps.setString(5, userLogin);
				ps.setInt(6, contact.getId().intValue());
				ps.executeUpdate();
				this.closeConnectionObjects(null, ps);
			} catch (SQLException e) {
				e.printStackTrace();
				this.closeConnectionObjects(null, ps);
			}
		}
	}

	@Override
	public boolean deleteContact(Long contactId) {
		PreparedStatement ps = null;
		
		try {
			ps = this.conn.prepareStatement("Delete from Contact where contact_id = ?");
			ps.setInt(1, contactId.intValue());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnectionObjects(null, ps);
		}
		return false;
	}

}

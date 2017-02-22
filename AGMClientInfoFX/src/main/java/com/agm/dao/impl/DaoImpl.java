package com.agm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.agm.connection.MySQLConnection;
import com.agm.dao.Dao;
import com.agm.model.Contact;
import com.agm.model.SystemUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoImpl extends MySQLConnection implements Dao {

	private Connection conn = this.getConnection();
	
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
		    StandardServiceRegistryBuilder.destroy(registry);
		}
    }
	
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
				+ " From Rage.Contact ";
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
		}
		return null;
	}

}

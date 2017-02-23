package com.agm.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.agm.utils.Constants;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
	
	private LongProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty contactNumber;
	private StringProperty secondaryContactNumber;
	private String createdBy;
	private Date createdDate;
	private String lastModBy;
	private Date lastModDate;
	
	public Contact(ResultSet rs) throws SQLException {
		this.id = new SimpleLongProperty(rs.getLong(1)) ;
		this.firstName = new SimpleStringProperty(rs.getString(2));
		this.lastName = new SimpleStringProperty(rs.getString(3));
		this.contactNumber = new SimpleStringProperty(rs.getString(4));
		this.secondaryContactNumber = new SimpleStringProperty(rs.getString(5));
		this.createdBy = rs.getString(6);
		this.createdDate = rs.getDate(7);
		this.lastModBy = rs.getString(8);
		this.lastModDate = rs.getDate(9);
	}

	public Contact() {
		this.id = new SimpleLongProperty(0l);
		this.firstName = new SimpleStringProperty(Constants.EMPTY_STRING);
		this.lastName = new SimpleStringProperty(Constants.EMPTY_STRING);
		this.contactNumber = new SimpleStringProperty(Constants.EMPTY_STRING);
		this.secondaryContactNumber = new SimpleStringProperty(Constants.EMPTY_STRING);
		this.createdBy = Constants.EMPTY_STRING;
		this.lastModBy = Constants.EMPTY_STRING;
	}

	public Long getId() {
		return id.get();
	}
	
	public LongProperty getIdProperty() {
		return id;
	}

	public void setId(Long id) {
		this.id.set(id);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty getFirstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty getLastNameProperty() {
		return lastName;
	}

	public String getContactNumber() {
		return contactNumber.get();
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber.set(contactNumber);
	}
	
	public StringProperty getContactNumberProperty() {
		return contactNumber;
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber.get();
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber.set(secondaryContactNumber);
	}
	
	public StringProperty getSecondaryContactNumberProperty() {
		return secondaryContactNumber;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModBy() {
		return lastModBy;
	}

	public void setLastModBy(String lastModBy) {
		this.lastModBy = lastModBy;
	}

	public Date getLastModDate() {
		return lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}
	

	public void setContactDetailsByUser(ResultSet rs) throws SQLException {
		this.firstName = new SimpleStringProperty(rs.getString(8));
		this.lastName = new SimpleStringProperty(rs.getString(9));
		this.contactNumber = new SimpleStringProperty(rs.getString(10));
		this.secondaryContactNumber = new SimpleStringProperty(rs.getString(11));
		this.createdBy = rs.getString(12);
		this.createdDate = rs.getDate(13);
		this.lastModBy = rs.getString(14);
		this.lastModDate = rs.getDate(15);

	}
}

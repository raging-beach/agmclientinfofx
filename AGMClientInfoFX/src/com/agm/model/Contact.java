package com.agm.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
	private StringProperty createdBy;
	private Date createdDate;
	private StringProperty lastModBy;
	private Date lastModDate;

	public Contact(ResultSet rs) throws SQLException {
		this.id = new SimpleLongProperty(rs.getLong(1)) ;
		this.firstName = new SimpleStringProperty(rs.getString(2));
		this.lastName = new SimpleStringProperty(rs.getString(3));
		this.contactNumber = new SimpleStringProperty(rs.getString(4));
		this.secondaryContactNumber = new SimpleStringProperty(rs.getString(5));
		this.createdBy = new SimpleStringProperty(rs.getString(6));
		this.createdDate = rs.getDate(7);
		this.lastModBy = new SimpleStringProperty(rs.getString(8));
		this.lastModDate = rs.getDate(9);
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public LongProperty getId() {
		return id;
	}

	public void setId(LongProperty id) {
		this.id = id;
	}

	public StringProperty getFirstName() {
		return firstName;
	}
	
	public String getFirstNameStr() {
		return this.getFirstName().get();
	}

	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}
	
	public String getLastNameStr() {
		return this.getLastName().get();
	}

	public StringProperty getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(StringProperty contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getContactNumberStr() {
		return this.getContactNumber().get();
	}

	public StringProperty getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public void setSecondaryContactNumber(StringProperty secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}
	
	public String getSecondaryContactNumberStr() {
		return this.getSecondaryContactNumber().get();
	}

	public StringProperty getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(StringProperty createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public StringProperty getLastModBy() {
		return lastModBy;
	}

	public void setLastModBy(StringProperty lastModBy) {
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
		this.createdBy = new SimpleStringProperty(rs.getString(12));
		this.createdDate = rs.getDate(13);
		this.lastModBy = new SimpleStringProperty(rs.getString(14));
		this.lastModDate = rs.getDate(15);

	}
}

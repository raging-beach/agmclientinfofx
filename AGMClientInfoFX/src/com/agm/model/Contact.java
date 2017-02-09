package com.agm.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Contact {

	private Long id;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String secondaryContactNumber;
	private String createdBy;
	private Date createdDate;
	private String lastModBy;
	private Date lastModDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
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
		this.firstName = rs.getString(8);
		this.lastName = rs.getString(9);
		this.contactNumber = rs.getString(10);
		this.secondaryContactNumber = rs.getString(11);
		this.createdBy = rs.getString(12);
		this.createdDate = rs.getDate(13);
		this.lastModBy = rs.getString(14);
		this.lastModDate = rs.getDate(15);
		
	}
}

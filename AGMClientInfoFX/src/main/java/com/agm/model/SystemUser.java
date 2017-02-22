package com.agm.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SystemUser {

	private Long id;
	private Long contactId;
	private String login;
	private String password;
	private String createdBy;
	private Date createdDate;
	private String lastModBy;
	private Date lastModDate;
	private Contact contactDetails;
	private String loginError;
	
	public SystemUser() {
		super();
	}

	public SystemUser(ResultSet rs) throws SQLException {
		this.id = rs.getLong(1);
		this.login = rs.getString(2);
		this.password = rs.getString(3);
		this.createdBy = rs.getString(4);
		this.createdDate = rs.getDate(5);
		this.lastModBy = rs.getString(6);
		this.lastModDate = rs.getDate(7);
		this.contactDetails = new Contact();
		this.contactDetails.setContactDetailsByUser(rs);
	}

	public SystemUser(String errMsg) {
		this.loginError = errMsg;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Contact getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(Contact contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
}

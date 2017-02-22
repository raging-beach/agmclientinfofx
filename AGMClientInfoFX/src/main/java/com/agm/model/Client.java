package com.agm.model;

import java.util.Date;

public class Client {

	private Long id;
	private String businessName;
	private String contactFirstName;
	private String contactLastName;
	private String primaryPhoneNumber;
	private String secondaryPhoneNumber;
	private String businessAddress;
	private String note;
	private Date signedDate;
	private String status;
	private String businessType;
	private String createdBy;
	private Date createdDate;
	private String lastModBy;
	private Date lastModDate;
	
	public Client() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}
	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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
	
	
}

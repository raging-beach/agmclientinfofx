package com.agm.service;

import com.agm.model.Contact;

import javafx.collections.ObservableList;

public interface Service {
	
	public String getLoginError(String userName, String password);
	public ObservableList<Contact> getAllContacts();

}

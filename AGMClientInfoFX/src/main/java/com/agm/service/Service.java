package com.agm.service;

import com.agm.model.Contact;
import com.agm.model.SystemUser;

import javafx.collections.ObservableList;

public interface Service {
	
	public SystemUser getLoginError(String userName, String password);
	public ObservableList<Contact> getAllContacts();
	public void saveContact(Contact contact, String userLogin);
	public boolean deleteContact(Long contactId);
}

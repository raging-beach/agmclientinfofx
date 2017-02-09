package com.agm.dao;

import com.agm.model.Contact;
import com.agm.model.SystemUser;

import javafx.collections.ObservableList;

public interface Dao {
	public SystemUser getSystemUserByLogin(String login);
	public ObservableList<SystemUser> getAllSystemUsers();
	public ObservableList<Contact> getAllContacts();
}

package com.agm.dao;

import java.util.List;

import com.agm.model.Contact;
import com.agm.model.SystemUser;

public interface Dao {
	public SystemUser getSystemUserByLogin(String login);
	public List<SystemUser> getAllSystemUsers();
	public List<Contact> getAllContacts();
}

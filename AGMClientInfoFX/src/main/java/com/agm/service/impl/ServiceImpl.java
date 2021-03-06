package com.agm.service.impl;

import com.agm.dao.Dao;
import com.agm.dao.impl.DaoImpl;
import com.agm.model.Contact;
import com.agm.model.SystemUser;
import com.agm.service.Service;
import com.agm.utils.CommonHelper;
import com.agm.utils.Constants;

import javafx.collections.ObservableList;

public class ServiceImpl implements Service {
	
	private Dao dao;

	public ServiceImpl() {
		super();
		this.dao = new DaoImpl();
	}

	@Override
	public SystemUser getLoginError(String userName, String password) {
		if (CommonHelper.hasValidValue(userName)) {
			final SystemUser user = this.dao.getSystemUserByLogin(userName);
			if (user != null && CommonHelper.isEqual(user.getPassword(), password)) {
				return user;
			} else {
				return new SystemUser(Constants.USER_DOES_NOT_EXIST);
			}
		} else {
			return new SystemUser(Constants.USERNAME_REQUIRED);
		}
	}

	@Override
	public ObservableList<Contact> getAllContacts() {
		return this.dao.getAllContacts();
	}

	@Override
	public void saveContact(Contact contact, String userLogin) {
		this.dao.saveContact(contact, userLogin);
	}

	@Override
	public boolean deleteContact(Long contactId) {
		return this.dao.deleteContact(contactId);
	}
}

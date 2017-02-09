package com.agm.service.impl;

import java.util.List;

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
	public String getLoginError(String userName, String password) {
		if (CommonHelper.hasValidValue(userName)) {
			final SystemUser user = this.dao.getSystemUserByLogin(userName);
			if (user != null && CommonHelper.isEqual(user.getPassword(), password)) {
				return Constants.EMPTY_STRING;
			} else {
				return Constants.USER_DOES_NOT_EXIST;
			}
		} else {
			return Constants.USERNAME_REQUIRED;
		}
	}

	@Override
	public ObservableList<Contact> getAllContacts() {
		return this.dao.getAllContacts();
	}
}

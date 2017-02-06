package com.agm.service.impl;

import com.agm.dao.Dao;
import com.agm.dao.impl.DaoImpl;
import com.agm.model.SystemUser;
import com.agm.service.Service;
import com.agm.utils.CommonHelper;
import com.agm.utils.Constants;

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
			if (user != null) {
				return Constants.EMPTY_STRING;
			} else {
				return Constants.USER_DOES_NOT_EXIST;
			}
		} else {
			return Constants.USERNAME_REQUIRED;
		}
	}
}

package com.ysyt.dao;

import org.hibernate.SessionFactory;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

public interface IAuthDao {

	UserBean createUpdateUserBean(UserBean userBean,
			SessionFactory sessionFactory);

	void createUpdateLoginDetails(LoginCredentials loginCredentials,
			SessionFactory sessionFactory);
	
	

}

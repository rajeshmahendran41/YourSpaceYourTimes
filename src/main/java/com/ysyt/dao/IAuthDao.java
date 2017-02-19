package com.ysyt.dao;

import java.math.BigInteger;

import org.hibernate.SessionFactory;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.PasswordRequest;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.AuthResponse;

public interface IAuthDao {

	UserBean createUpdateUserBean(UserBean userBean,
			SessionFactory sessionFactory);

	void createUpdateLoginDetails(LoginCredentials loginCredentials,
			SessionFactory sessionFactory);

	Long checkLogin(LoginRequest loginRequest, SessionFactory sessionFactory);

	UserBean getUserBean(Long userId, SessionFactory sessionFactory);

	LoginCredentials checkLoginEmail(PasswordRequest pwdRequest,
			SessionFactory sessionFactory);
	
	

}

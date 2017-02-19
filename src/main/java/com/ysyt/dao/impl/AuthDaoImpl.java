package com.ysyt.dao.impl;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.dao.IAuthDao;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.PasswordRequest;

@Repository
public class AuthDaoImpl implements IAuthDao {
	
	
	@Autowired
	private HttpServletRequest httpRequest;

	@Override
	public UserBean createUpdateUserBean(UserBean userBean,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(userBean);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
				
		return userBean;
	}

	@Override
	public void createUpdateLoginDetails(LoginCredentials loginCredentials,
			SessionFactory sessionFactory) {
		
		
		sessionFactory.getCurrentSession().saveOrUpdate(loginCredentials);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
						
	}

	@Override
	public Long checkLogin(LoginRequest loginRequest,
			SessionFactory sessionFactory) {
		
		LoginCredentials credentials = new LoginCredentials();
		
		credentials = (LoginCredentials) sessionFactory.getCurrentSession().createCriteria(LoginCredentials.class)
							.add(Restrictions.eq("email", loginRequest.getEmail()))
							.uniqueResult();
		if(!Util.isNull(credentials)){
			
			if(loginRequest.getPassword().equals(Util.getDecryptedPassword(credentials.getPassword()))){
				return credentials.getUserId();
			}
			
		}else{
			return null;
		}
		
		return null;
		
		
	}
	
	
	@Override
	public LoginCredentials checkLoginEmail(PasswordRequest pwdRequest,
			SessionFactory sessionFactory) {
		
		LoginCredentials credentials = new LoginCredentials();
		
		credentials =  (LoginCredentials) sessionFactory.getCurrentSession().createCriteria(LoginCredentials.class)
							.add(Restrictions.eq("userId", Util.getUserId(httpRequest) ))
							.uniqueResult();
		if(!Util.isNull(credentials)){
			
			if(pwdRequest.getOldPassword().equals(Util.getDecryptedPassword(credentials.getPassword()))){
				return credentials;
			}
			
		}else{
			return null;
		}
		
		return null;
		
		
	}

	@Override
	public UserBean getUserBean(Long userId, SessionFactory sessionFactory) {
		
		return (UserBean) sessionFactory.getCurrentSession().createCriteria(UserBean.class)
				.add(Restrictions.eq("id", userId))
				.uniqueResult();
	}



}

package com.ysyt.dao.impl;

import java.math.BigInteger;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.dao.IAuthDao;
import com.ysyt.to.request.LoginRequest;

@Repository
public class AuthDaoImpl implements IAuthDao {

	@Override
	public UserBean createUpdateUserBean(UserBean userBean,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(userBean);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().flush();
				
		return userBean;
	}

	@Override
	public void createUpdateLoginDetails(LoginCredentials loginCredentials,
			SessionFactory sessionFactory) {
		
		
		sessionFactory.getCurrentSession().saveOrUpdate(loginCredentials);
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().flush();
						
	}

	@Override
	public BigInteger checkLogin(LoginRequest loginRequest,
			SessionFactory sessionFactory) {
		
		return ((LoginCredentials) sessionFactory.getCurrentSession().createCriteria(LoginCredentials.class)
							.add(Restrictions.eq("email", loginRequest.getEmail()))
							.add(Restrictions.eq("password", loginRequest.getPassword()))
							.uniqueResult()).getUserId();
		
		
	}

	@Override
	public UserBean getUserBean(BigInteger userId, SessionFactory sessionFactory) {
		
		return (UserBean) sessionFactory.getCurrentSession().createCriteria(UserBean.class)
				.add(Restrictions.eq("userId", userId))
				.uniqueResult();
	}



}

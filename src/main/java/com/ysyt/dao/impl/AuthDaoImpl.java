package com.ysyt.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.dao.IAuthDao;

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

}

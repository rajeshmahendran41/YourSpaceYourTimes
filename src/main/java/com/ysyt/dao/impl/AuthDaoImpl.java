package com.ysyt.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ysyt.bean.UserBean;
import com.ysyt.dao.IAuthDao;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

@Repository
public class AuthDaoImpl implements IAuthDao {

	@Override
	public SignupResponse setSigupResponse(SignupRequest request,SessionFactory sessionFactory) {
		
		    sessionFactory.getCurrentSession().saveOrUpdate(request.getUserDetails());
			sessionFactory.getCurrentSession().clear();
			sessionFactory.getCurrentSession().flush();
			
			
			
			
		
		return null;
		
		
	}

}

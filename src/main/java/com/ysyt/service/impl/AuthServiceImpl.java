package com.ysyt.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysyt.dao.IAuthDao;
import com.ysyt.service.IAuthService;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private IAuthDao iAuthDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SignupResponse setSignupResponse(SignupRequest request) {
		
		return iAuthDao.setSigupResponse(request,sessionFactory);
	}

}

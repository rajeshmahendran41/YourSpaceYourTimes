package com.ysyt.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
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
		
		SignupResponse res = new SignupResponse();

		request.getUserDetails().setCreatedAt(Util.getCurrentTimeStamp());
		request.getUserDetails().setUpdatedAt(Util.getCurrentTimeStamp());
		res.setUserBean(createUpdateUserBean(request.getUserDetails()));
		
		if(!Util.isNull(request.getUserDetails().getId())){
			request.getLoginDetails().setUserId(request.getUserDetails().getId());
			request.getLoginDetails().setCreatedBy(request.getUserDetails().getId());
			request.getLoginDetails().setUpdatedBy(request.getUserDetails().getId());
			request.getLoginDetails().setCreatedAt(Util.getCurrentTimeStamp());
			request.getLoginDetails().setUpdatedAt(Util.getCurrentTimeStamp());
		}
		if(!Util.isNull(request.getLoginDetails().getPassword())){
			request.getLoginDetails().setPassword(Util.getEncryptedPassword(request.getLoginDetails().getPassword()));
		}
		
		createUpdateLoginDetails(request.getLoginDetails());
		return res;
	}
	
	@Override
	public UserBean createUpdateUserBean(UserBean userBean) {
				
		return iAuthDao.createUpdateUserBean(userBean,sessionFactory);
	}

	@Override
	public void createUpdateLoginDetails(LoginCredentials loginCredentials) {
		
		iAuthDao.createUpdateLoginDetails(loginCredentials,sessionFactory);

	}
	
	
	
	

}

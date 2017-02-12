package com.ysyt.service.impl;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.constants.SessionConstant;
import com.ysyt.dao.IAuthDao;
import com.ysyt.service.IAuthService;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.AuthResponse;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private IAuthDao iAuthDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private HttpServletRequest httpRequest;

	@Override
	public AuthResponse setSignupResponse(SignupRequest request) {
		
		AuthResponse res = new AuthResponse();

		request.getUserDetails().setCreatedAt(Util.getCurrentTimeStamp());
		request.getUserDetails().setUpdatedAt(Util.getCurrentTimeStamp());
		res.setUserBean(createUpdateUserBean(request.getUserDetails()));
		
		if(!Util.isNull(request.getUserDetails())){
			HttpSession httpSession = httpRequest.getSession(true);
	        httpSession.setAttribute(SessionConstant.USER_BEAN,
	                request.getUserDetails());
		}
		
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
	
	
	@Override
	public UserBean getUserBean(BigInteger userId) {
		
		return iAuthDao.getUserBean(userId,sessionFactory);

	}

	@Override
	public AuthResponse loginAction(LoginRequest loginRequest) {
		
		AuthResponse respone = new AuthResponse();
		UserBean userBean = new UserBean();
		
						
		BigInteger userId = iAuthDao.checkLogin(loginRequest,sessionFactory);
		
		userBean = getUserBean(userId);
		if(!Util.isNull(userBean)){
			respone.setUserBean(userBean);
			HttpSession httpSession = httpRequest.getSession(true);
	        httpSession.setAttribute(SessionConstant.USER_BEAN,
	             userBean);
		}
		
		return respone;
	}
	
	
	
	

}

package com.ysyt.dao;

import org.hibernate.SessionFactory;

import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

public interface IAuthDao {

	SignupResponse setSigupResponse(SignupRequest request, SessionFactory sessionFactory);
	
	

}

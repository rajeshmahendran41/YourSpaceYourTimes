package com.ysyt.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysyt.dao.IAuthDao;
import com.ysyt.service.IAuthService;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private IAuthDao iAuthDao;
	
	@Autowired
	private SessionFactory sessionFactory;

}

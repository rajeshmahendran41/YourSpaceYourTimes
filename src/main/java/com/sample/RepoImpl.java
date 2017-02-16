package com.sample;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepoImpl implements IRepo {

	@Override
	@Transactional
	public String getData(SessionFactory ses) {
		
		return (String) ses.getCurrentSession().createSQLQuery("select password from flipticket.login_credetinals  limit 1").uniqueResult();
		 
	}

	@Override
	@Transactional
	public String getData1(SessionFactory ses) {
		
		return (String) ses.getCurrentSession().createSQLQuery("select name from public.abc limit 1").uniqueResult();

	}
	
	

}

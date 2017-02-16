package com.sample;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Service1 implements IService {

	@Autowired(required=true)
	private SessionFactory ses;
	
	@Autowired
	private IRepo repo;
	
	
	@Override
	public String getData() {
		System.out.println(ses);
		
		
		return repo.getData(ses);
	}


	@Override
	public String getData1() {
		
		return repo.getData1(ses);

	}
	


}

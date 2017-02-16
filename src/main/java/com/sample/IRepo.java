package com.sample;

import org.hibernate.SessionFactory;

public interface IRepo {
	
	public String getData(SessionFactory ses);

	public String getData1(SessionFactory ses);

}

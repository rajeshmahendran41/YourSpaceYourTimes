package com.instamojo.api.dao;

import org.hibernate.SessionFactory;

import com.ysyt.bean.Transactions;
import com.ysyt.bean.UserAccomodationMapping;

public interface ITransactionDao {

	Transactions createOrder(Transactions transaction,
			SessionFactory sessionFactory);

	Transactions getOrderDetails(String orderId, SessionFactory sessionFactory);

	UserAccomodationMapping createUpdateUserAccomodationMapping(
			UserAccomodationMapping userAccomodationMapping,
			SessionFactory sessionFactory);

}

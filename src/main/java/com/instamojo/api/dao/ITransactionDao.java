package com.instamojo.api.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.ysyt.bean.PercentageSplitUpMaster;
import com.ysyt.bean.Transactions;
import com.ysyt.bean.UserAccomodationMapping;
import com.ysyt.bean.UserBillsPercentage;

public interface ITransactionDao {

	Transactions createOrder(Transactions transaction,
			SessionFactory sessionFactory);

	Transactions getOrderDetails(String orderId, SessionFactory sessionFactory);

	UserAccomodationMapping createUpdateUserAccomodationMapping(
			UserAccomodationMapping userAccomodationMapping,
			SessionFactory sessionFactory);

	List<PercentageSplitUpMaster> getPercentages(SessionFactory sessionFactory);

	void createUpdateSplitUp(UserBillsPercentage splits,
			SessionFactory sessionFactory);

}

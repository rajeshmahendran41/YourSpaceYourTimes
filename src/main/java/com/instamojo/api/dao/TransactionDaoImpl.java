package com.instamojo.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Util.Util;
import com.instamojo.wrapper.request.OrderListRequest;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.PercentageSplitUpMaster;
import com.ysyt.bean.Transactions;
import com.ysyt.bean.UserAccomodationMapping;
import com.ysyt.bean.UserBillsPercentage;
import com.ysyt.constants.YSYTConstants;

@Repository
public class TransactionDaoImpl implements ITransactionDao {
	
	
	@Autowired
	private HttpServletRequest httpRequest;

	@Override
	public Transactions createOrder(Transactions transaction,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(transaction);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return transaction;
		}

	@Override
	public Transactions getOrderDetails(String orderId,
			SessionFactory sessionFactory) {
		
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Transactions.class)
				.add(Restrictions.eq("isDeleted",false));
				
				if(!Util.isNull(orderId)){
					criteria.add(Restrictions.eq("orderId", orderId));
				}
				
				return (Transactions) criteria.uniqueResult();		
	}

	@Override
	public UserAccomodationMapping createUpdateUserAccomodationMapping(
			UserAccomodationMapping userAccomodationMapping,
			SessionFactory sessionFactory) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(userAccomodationMapping);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
		return userAccomodationMapping;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PercentageSplitUpMaster> getPercentages(
			SessionFactory sessionFactory) {
		

		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(PercentageSplitUpMaster.class)
				.add(Restrictions.eq("isDeleted",false));
				
				
				
				return (List<PercentageSplitUpMaster>) criteria.list();	
		
	}

	@Override
	public void createUpdateSplitUp(UserBillsPercentage splits,
			SessionFactory sessionFactory) {
		
		
		sessionFactory.getCurrentSession().saveOrUpdate(splits);
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
		
	}

	@Override
	public List<Transactions> orderList(OrderListRequest request,SessionFactory sessionFactory) {
		
		List<Transactions> transactions = new ArrayList<Transactions>();
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Transactions.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("depositType", YSYTConstants.SECURITY_DEPOSIT))
				.add(Restrictions.eq("userId", Util.getCurrentUser(httpRequest).getId()))
				.setFirstResult(!Util.isNull(request.getOffset())?request.getOffset():0)
				.setMaxResults(!Util.isNull(request.getLimit())?request.getLimit():0)
				.addOrder(Order.desc("createdAt"));
				
				transactions= criteria.list();
				
				for(Transactions trans : transactions){
					
					userAccomodationMapping(trans,sessionFactory);
				}
				return transactions;
	}
	
	@Override
	public Transactions orderDetails(String orderId,SessionFactory sessionFactory) {
		
		Transactions transactions = new Transactions();
		
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Transactions.class)
				.add(Restrictions.eq("isDeleted",false))
				.add(Restrictions.eq("depositType", YSYTConstants.SECURITY_DEPOSIT))
				.add(Restrictions.eq("orderId",orderId));
				
				transactions= (Transactions) criteria.uniqueResult();
					
				userAccomodationMapping(transactions, sessionFactory);
				
				return transactions;
	}
	
	public void userAccomodationMapping(Transactions trans,SessionFactory sessionFactory){
		UserAccomodationMapping userAccomodationMapping = new UserAccomodationMapping();

		
		Criteria userMapping =  sessionFactory.getCurrentSession().createCriteria(UserAccomodationMapping.class)
				.add(Restrictions.eq("orderId", trans.getOrderId()));
		
		userAccomodationMapping = (UserAccomodationMapping) userMapping.uniqueResult();
		if(!Util.isNull(userAccomodationMapping)){
		
			if(!Util.isNull(userAccomodationMapping.getJoinDate())){
				trans.setJoinDate(userAccomodationMapping.getJoinDate());
			}
			if(!Util.isNull(userAccomodationMapping.getVacateDate())){
				trans.setVacateDate(userAccomodationMapping.getVacateDate());
			}
		}
	}




}

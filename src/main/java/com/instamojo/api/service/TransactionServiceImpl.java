package com.instamojo.api.service;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.instamojo.api.dao.ITransactionDao;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.response.CreatePaymentOrderResponse;
import com.instamojo.wrapper.response.PaymentOrderDetailsResponse;
import com.ysyt.bean.PercentageSplitUpMaster;
import com.ysyt.bean.Transactions;
import com.ysyt.bean.UserAccomodationMapping;
import com.ysyt.bean.UserBillsPercentage;
import com.ysyt.constants.YSYTConstants;
import com.ysyt.to.response.TransactionResponse;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private ITransactionDao iTransactionDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private HttpServletRequest httpRequest;

	@Override
	public TransactionResponse createTransactionOrder(
			CreatePaymentOrderResponse createPaymentOrderResponse,
			PaymentOrder order) {
		
		TransactionResponse res = new TransactionResponse();
		Transactions transaction = new Transactions();
		
		transaction= setTranscation(createPaymentOrderResponse,order);
		transaction = iTransactionDao.createOrder(transaction,sessionFactory);
		res.setTransaction(transaction);
		
		return res;
	}
	
	@Override
	public Transactions getTranscationDetailsByOrderId(
			String orderId) {
		
		Transactions transaction = new Transactions();
		transaction = iTransactionDao.getOrderDetails(orderId,sessionFactory);
		return transaction;
	}
	
	@Override
	public void storePercentageSplitUp(
			Double amount,String orderId) {
		
		Double totalAmount = amount;
		
		List<PercentageSplitUpMaster> percentages = new ArrayList<>();
				
		percentages = iTransactionDao.getPercentages(sessionFactory);
		
		for(PercentageSplitUpMaster splitPercentage : percentages){
			
			UserBillsPercentage splits = new UserBillsPercentage();
			
			Double splitAmount = 0.00;
			
			if(!Util.isNull(splitPercentage.getPercentage())){
				
				splitAmount= ((totalAmount)*(splitPercentage.getPercentage()/100.00));
				
				if(splitPercentage.getId()==1){
					splitAmount+=3;
				}else if(splitPercentage.getId()==3){
					splitAmount-=3;
				}
				
				splits.setAmount(splitAmount);
				splits.setCreatedAt(Util.getCurrentTimeStamp());
				splits.setUpdatedAt(Util.getCurrentTimeStamp());
				splits.setCreatedBy(Util.getUserId(httpRequest));
				splits.setUpdatedBy(Util.getUserId(httpRequest));
				splits.setOrderId(orderId);
				splits.setPercentageAllocationId(splitPercentage.getId());
				
				iTransactionDao.createUpdateSplitUp(splits,sessionFactory);

		
			}
			
		}
	
	}

	
	private Transactions setTranscation(CreatePaymentOrderResponse createPaymentOrderResponse,
			PaymentOrder order){
		Transactions transaction = new Transactions();
		transaction.setTransactionId(order.getTransactionId());
		transaction.setAmount(createPaymentOrderResponse.getPaymentOrder().getAmount());
		transaction.setCreatedAt(Util.getCurrentTimeStamp());
		transaction.setUpdatedAt(Util.getCurrentTimeStamp());
		transaction.setCreatedBy(Util.getUserId(httpRequest));
		transaction.setUpdatedBy(Util.getUserId(httpRequest));
		transaction.setCurrencty(createPaymentOrderResponse.getPaymentOrder().getCurrency());
		if(!Util.isNull(order.getAccomodationId())){
			transaction.setAccomodationId(order.getAccomodationId());
		}
		if(!Util.isNull(order.getDepositType())){
			transaction.setDepositType(order.getDepositType());
		}
		if(!Util.isNull(order.getRoomCost())){
			transaction.setRoomCost(order.getRoomCost());
		}
		if(!Util.isNull(order.getFoodCost())){
			transaction.setFoodCost(order.getFoodCost());
		}
		if(!Util.isNull(order.getAdditionalCost())){
			transaction.setAdditionalCost(order.getAdditionalCost());
		}
		if(!Util.isNull(order.getAmountRoomCost())){
			transaction.setTotalRoomCost(order.getAmountRoomCost());
		}
		transaction.setDescription(createPaymentOrderResponse.getPaymentOrder().getDescription());
		transaction.setEmail(createPaymentOrderResponse.getPaymentOrder().getEmail());
		transaction.setGsonResponse(createPaymentOrderResponse.getJsonResponse());
		transaction.setName(createPaymentOrderResponse.getPaymentOrder().getName());
		transaction.setOrderId(createPaymentOrderResponse.getPaymentOrder().getId());
		transaction.setUserId(Util.getCurrentUser(httpRequest).getId());
		transaction.setPaymentUrl(createPaymentOrderResponse.getPaymentOptions().getPaymentUrl());
		transaction.setPhone(createPaymentOrderResponse.getPaymentOrder().getPhone());
		transaction.setRedirectUrl(createPaymentOrderResponse.getPaymentOrder().getRedirectUrl());
		transaction.setStatus(createPaymentOrderResponse.getPaymentOrder().getStatus());
		transaction.setTransactionId(createPaymentOrderResponse.getPaymentOrder().getTransactionId());
		
		return transaction;

	}

	@Override
	public void checkInitialTransaction(
			PaymentOrderDetailsResponse paymentOrderDetailsResponse) {
	
		Transactions transaction = getTranscationDetailsByOrderId(paymentOrderDetailsResponse.getId());
		
		if(transaction.getDepositType().equals(YSYTConstants.SECURITY_DEPOSIT)){
			if(paymentOrderDetailsResponse.getStatus().equals(YSYTConstants.INSTAMOJO_STATUS_SUCCESS)){
				if(transaction.getStatus().equals(YSYTConstants.INSTAMOJO_STATUS_PENDING)){
					
					
					// update Status
					transaction.setStatus(paymentOrderDetailsResponse.getStatus());
					transaction.setUpdatedAt(Util.getCurrentTimeStamp());
					transaction.setUpdatedBy(Util.getCurrentUser(httpRequest).getId());
					
					// Update status in transaction Table
					transaction = iTransactionDao.createOrder(transaction,sessionFactory);
					
					UserAccomodationMapping userAccomodationMapping = new UserAccomodationMapping();
					userAccomodationMapping.setAccomodationId(transaction.getAccomodationId());
					if(!Util.isNull(transaction.getAdditionalCost())){
						userAccomodationMapping.setAdditionalCost(transaction.getAdditionalCost());
					}	
					userAccomodationMapping.setCreatedAt(Util.getCurrentTimeStamp());
					userAccomodationMapping.setUpdatedAt(Util.getCurrentTimeStamp());
					userAccomodationMapping.setCreatedBy(Util.getUserId(httpRequest));
					userAccomodationMapping.setUpdatedBy(Util.getUserId(httpRequest));
					if(!Util.isNull(transaction.getFoodCost())){
						userAccomodationMapping.setFoodCost(transaction.getFoodCost());
					}
					userAccomodationMapping.setJoinDate(Util.getCurrentTimeStamp());
					userAccomodationMapping.setOrderId(transaction.getOrderId());
					if(!Util.isNull(transaction.getRoomCost())){
						userAccomodationMapping.setRoomCost(transaction.getRoomCost());
					}
					userAccomodationMapping.setSecurityDeposit(transaction.getAmount());
					if(!Util.isNull(transaction.getTotalRoomCost())){
						userAccomodationMapping.setTotalCost(transaction.getTotalRoomCost());
					}
					userAccomodationMapping.setUserId(Util.getUserId(httpRequest));
					userAccomodationMapping = iTransactionDao.createUpdateUserAccomodationMapping(userAccomodationMapping,sessionFactory);
					storePercentageSplitUp(userAccomodationMapping.getSecurityDeposit(),userAccomodationMapping.getOrderId());
					
				}
			}
		}
		
		
	}
	

}

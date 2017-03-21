package com.instamojo.api.service;

import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.response.CreatePaymentOrderResponse;
import com.instamojo.wrapper.response.PaymentOrderDetailsResponse;
import com.ysyt.bean.Transactions;
import com.ysyt.to.response.TransactionResponse;

public interface ITransactionService {

	TransactionResponse createTransactionOrder(
			CreatePaymentOrderResponse createPaymentOrderResponse, PaymentOrder order);

	Transactions getTranscationDetailsByOrderId(String orderId);

	void checkInitialTransaction(
			PaymentOrderDetailsResponse paymentOrderDetailsResponse);

	void storePercentageSplitUp(Double amount, String orderId);

}

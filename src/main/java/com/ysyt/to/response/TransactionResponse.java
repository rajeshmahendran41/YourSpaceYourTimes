package com.ysyt.to.response;

import com.response.CommonResponse;
import com.ysyt.bean.Transactions;

public class TransactionResponse extends CommonResponse {

	private Transactions transaction;

	public Transactions getTransaction() {
		return transaction;
	}

	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}
}

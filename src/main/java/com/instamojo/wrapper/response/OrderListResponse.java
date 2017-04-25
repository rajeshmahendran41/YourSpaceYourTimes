package com.instamojo.wrapper.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.Transactions;



public class OrderListResponse extends CommonResponse {

	private List<Transactions> transactions;

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
}

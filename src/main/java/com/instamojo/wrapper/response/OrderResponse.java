package com.instamojo.wrapper.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.Transactions;



public class OrderResponse extends CommonResponse {

	private Transactions orderDetails;

	public Transactions getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Transactions orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}

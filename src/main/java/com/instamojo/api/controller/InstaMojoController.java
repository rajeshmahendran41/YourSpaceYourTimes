package com.instamojo.api.controller;

import java.util.logging.Level;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.InvalidPaymentOrderException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderFilter;
import com.instamojo.wrapper.response.CreatePaymentOrderResponse;
import com.instamojo.wrapper.response.PaymentOrderDetailsResponse;
import com.instamojo.wrapper.response.PaymentOrderListResponse;

@RestController
@RequestMapping(value="/api/instamojo/")
public class InstaMojoController {

	@RequestMapping(value = "order", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public CreatePaymentOrderResponse createOrder( @RequestBody PaymentOrder order){
		
		CreatePaymentOrderResponse createPaymentOrderResponse = new CreatePaymentOrderResponse();
        

		Instamojo api = null;

		try {
		    // gets the reference to the instamojo api
		    api = InstamojoImpl.getApi("DQCRWsqSzQ14uMhwK6q6s4RmnxkjqDI9jV7WRjlj", "ws8nbm3DucuqXSRCP5nNyZjZYJPqAc7WgzcZOb1ed5nLrmBhd6zCjwKiM5atPtx3XT0ZY9pOd042OnpFIyPhS4kNOXbRzT9hycQePPmeTG7dDF18hwEMWFlon8MQL09c", "https://test.instamojo.com/v2/", "https://test.instamojo.com/oauth2/token/");
		} catch (ConnectionException e) {
			
		}

		boolean isOrderValid = order.validate();

		if (isOrderValid) {
		    try {
		         createPaymentOrderResponse = api.createNewPaymentOrder(order);
		        // print the status of the payment order.
		        System.out.println(createPaymentOrderResponse.getPaymentOrder().getStatus());
		    } catch (InvalidPaymentOrderException e) {

		        if (order.isTransactionIdInvalid()) {
		            System.out.println("Transaction id is invalid. This is mostly due to duplicate  transaction id.");
		        }
		        if (order.isCurrencyInvalid()) {
		            System.out.println("Currency is invalid.");
		        }
		    } catch (ConnectionException e) {
		    }
		} else {
		    // inform validation errors to the user.
		    if (order.isTransactionIdInvalid()) {
		        System.out.println("Transaction id is invalid.");
		    }
		    if (order.isAmountInvalid()) {
		      System.out.println("Amount can not be less than 9.00.");
		    }
		    if (order.isCurrencyInvalid()) {
		      System.out.println("Please provide the currency.");
		    }
		    if (order.isDescriptionInvalid()) {
		      System.out.println("Description can not be greater than 255 characters.");
		        }
		    if (order.isEmailInvalid()) {
		      System.out.println("Please provide valid Email Address.");
		    }
		    if (order.isNameInvalid()) {
		      System.out.println("Name can not be greater than 100 characters.");
		    }
		    if (order.isPhoneInvalid()) {
		      System.out.println("Phone is invalid.");
		    }
		    if (order.isRedirectUrlInvalid()) {
		      System.out.println("Please provide valid Redirect url.");
		    }

		    if (order.isWebhookInvalid()) {
		      System.out.println("Provide a valid webhook url");
		    }
		}
		
        return createPaymentOrderResponse;
	}
	
	@RequestMapping(value = "/orderId/{orderId}", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public PaymentOrderDetailsResponse orderId(@PathVariable(value="orderId") String orderId ){
		
		Instamojo api = null;
		PaymentOrderDetailsResponse paymentOrderDetailsResponse= new PaymentOrderDetailsResponse();

		
		try {
		    api = InstamojoImpl.getApi("DQCRWsqSzQ14uMhwK6q6s4RmnxkjqDI9jV7WRjlj", "ws8nbm3DucuqXSRCP5nNyZjZYJPqAc7WgzcZOb1ed5nLrmBhd6zCjwKiM5atPtx3XT0ZY9pOd042OnpFIyPhS4kNOXbRzT9hycQePPmeTG7dDF18hwEMWFlon8MQL09c", "https://test.instamojo.com/v2/", "https://test.instamojo.com/oauth2/token/");

		     paymentOrderDetailsResponse = api.getPaymentOrderDetails(orderId);

		    // print the status of the payment order.
		    System.out.println(paymentOrderDetailsResponse.getStatus());
		} catch (ConnectionException e) {
			
		}
		
		return paymentOrderDetailsResponse;

	}
	
	
	@RequestMapping(value = "/transactionId/{transactionId}", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public PaymentOrderDetailsResponse transactionId(@PathVariable(value="transactionId") String transactionId ){
		
		Instamojo api = null;
		PaymentOrderDetailsResponse paymentOrderDetailsResponse= new PaymentOrderDetailsResponse();

		
		try {
		    api = InstamojoImpl.getApi("DQCRWsqSzQ14uMhwK6q6s4RmnxkjqDI9jV7WRjlj", "ws8nbm3DucuqXSRCP5nNyZjZYJPqAc7WgzcZOb1ed5nLrmBhd6zCjwKiM5atPtx3XT0ZY9pOd042OnpFIyPhS4kNOXbRzT9hycQePPmeTG7dDF18hwEMWFlon8MQL09c", "https://test.instamojo.com/v2/", "https://test.instamojo.com/oauth2/token/");

		     paymentOrderDetailsResponse = api.getPaymentOrderDetailsByTransactionId(transactionId);

		    // print the status of the payment order.
		    System.out.println(paymentOrderDetailsResponse.getStatus());
		} catch (ConnectionException e) {
			
		}
		
		return paymentOrderDetailsResponse;

	}
	
	@RequestMapping(value = "/order/list", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public PaymentOrderListResponse orderList( ){
		
		Instamojo api = null;
		PaymentOrderListResponse paymentOrderListResponse= new PaymentOrderListResponse();

		
		try {
			 api =  InstamojoImpl.getApi("DQCRWsqSzQ14uMhwK6q6s4RmnxkjqDI9jV7WRjlj", "ws8nbm3DucuqXSRCP5nNyZjZYJPqAc7WgzcZOb1ed5nLrmBhd6zCjwKiM5atPtx3XT0ZY9pOd042OnpFIyPhS4kNOXbRzT9hycQePPmeTG7dDF18hwEMWFlon8MQL09c", "https://test.instamojo.com/v2/", "https://test.instamojo.com/oauth2/token/");


			PaymentOrderFilter paymentOrderFilter = new PaymentOrderFilter();
			 paymentOrderListResponse =  api.getPaymentOrderList(paymentOrderFilter);

			// print the list of all payment orders.
			for (PaymentOrder paymentOrder : paymentOrderListResponse.getPaymentOrders()) {
			    System.out.println("Result = " + paymentOrder.getStatus());
			}

			} catch (ConnectionException e) {
				
			}
		
		return paymentOrderListResponse;

	}

	
}

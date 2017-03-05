package com.instamojo.api.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Util.Util;
import com.instamojo.api.service.ITransactionService;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.InvalidPaymentOrderException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderFilter;
import com.instamojo.wrapper.response.CreatePaymentOrderResponse;
import com.instamojo.wrapper.response.PaymentOrderDetailsResponse;
import com.instamojo.wrapper.response.PaymentOrderListResponse;
import com.ysyt.to.response.TransactionResponse;

@RestController
@RequestMapping(value="/api/instamojo/")
public class InstaMojoController {
	
	@Autowired 
	private ITransactionService iTransactionService;
	
	@Value("${instamojo.clientId}")
	private String clientId;
	
	@Value("${instamojo.clientSecret}")
	private String clientSecret;
	
	@Value("${instamojo.apiEndPoint}")
	private String apiEndPoint;
	
	@Value("${instamojo.authEndPoint}")
	private String authEndPoint;
	

	@RequestMapping(value = "order", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public TransactionResponse createOrder( @RequestBody PaymentOrder order){
		
		CreatePaymentOrderResponse createPaymentOrderResponse = new CreatePaymentOrderResponse();
		TransactionResponse transactionResponse = new TransactionResponse();

		Instamojo api = null;

		try {
		    api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPoint,authEndPoint);
		
		} catch (ConnectionException e) {
			
		}

		boolean isOrderValid = order.validate();

		if (isOrderValid) {
		    try {
		         createPaymentOrderResponse = api.createNewPaymentOrder(order);
		         
		         
		         if(!Util.isNull(createPaymentOrderResponse.getPaymentOrder())){
		        	 return iTransactionService.createTransactionOrder(createPaymentOrderResponse,order);
		         }
		         
		         
		    } catch (InvalidPaymentOrderException e) {

		        if (order.isTransactionIdInvalid()) {
		        	Util.throwPrimeException("Transaction id is invalid. This is mostly due to duplicate  transaction id.");
		        }
		        if (order.isCurrencyInvalid()) {
		        	Util.throwPrimeException("Currency is invalid.");

		        }
		    } catch (ConnectionException e) {
		    }
		} else {
		    // inform validation errors to the user.
		    if (order.isTransactionIdInvalid()) {
		        Util.throwPrimeException("Transaction id is invalid.");
		    }
		    if (order.isAmountInvalid()) {
		      Util.throwPrimeException("Amount can not be less than 9.00.");
		    }
		    if (order.isCurrencyInvalid()) {
		      Util.throwPrimeException("Please provide the currency.");
		    }
		    if (order.isDescriptionInvalid()) {
		      Util.throwPrimeException("Description can not be greater than 255 characters.");
		        }
		    if (order.isEmailInvalid()) {
		      Util.throwPrimeException("Please provide valid Email Address.");
		    }
		    if (order.isNameInvalid()) {
		      Util.throwPrimeException("Name can not be greater than 100 characters.");
		    }
		    if (order.isPhoneInvalid()) {
		      Util.throwPrimeException("Phone is invalid.");
		    }
		    if (order.isRedirectUrlInvalid()) {
		      Util.throwPrimeException("Please provide valid Redirect url.");
		    }

		    if (order.isWebhookInvalid()) {
		      Util.throwPrimeException("Provide a valid webhook url");
		    }
		}
		
        return transactionResponse;
	}
	
	
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public PaymentOrderDetailsResponse orderRefresh(@PathVariable(value="orderId") String orderId ){
		
		Instamojo api = null;
		PaymentOrderDetailsResponse paymentOrderDetailsResponse= new PaymentOrderDetailsResponse();

		
		try {
		    api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPoint,authEndPoint);
		    paymentOrderDetailsResponse = api.getPaymentOrderDetails(orderId);
		    if(!Util.isNull(paymentOrderDetailsResponse.getId())){
		    	iTransactionService.checkInitialTransaction(paymentOrderDetailsResponse);
		    }
		} catch (ConnectionException e) {
			
		}
		
		return paymentOrderDetailsResponse;

	}
	

	
	@RequestMapping(value = "/orderId/{orderId}", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public PaymentOrderDetailsResponse orderId(@PathVariable(value="orderId") String orderId ){
		
		Instamojo api = null;
		PaymentOrderDetailsResponse paymentOrderDetailsResponse= new PaymentOrderDetailsResponse();

		
		try {
		    api = InstamojoImpl.getApi("DQCRWsqSzQ14uMhwK6q6s4RmnxkjqDI9jV7WRjlj", "ws8nbm3DucuqXSRCP5nNyZjZYJPqAc7WgzcZOb1ed5nLrmBhd6zCjwKiM5atPtx3XT0ZY9pOd042OnpFIyPhS4kNOXbRzT9hycQePPmeTG7dDF18hwEMWFlon8MQL09c", "https://test.instamojo.com/v2/", "https://test.instamojo.com/oauth2/token/");

		     paymentOrderDetailsResponse = api.getPaymentOrderDetails(orderId);

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
		    Util.throwPrimeException(paymentOrderDetailsResponse.getStatus());
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

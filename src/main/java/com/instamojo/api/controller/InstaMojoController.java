package com.instamojo.api.controller;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

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
import com.ysyt.bean.Accomodations;
import com.ysyt.constants.YSYTConstants;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.response.TransactionResponse;

@RestController
@RequestMapping(value="/api/instamojo/")
public class InstaMojoController {
	
	@Autowired 
	private ITransactionService iTransactionService;
	
	@Autowired
	private IAccomodationService iAccomodationService;
	
	@Autowired
    private HttpServletRequest httpRequest;
	
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
		
		validatePayment(order);
		
		order = generateTransaction(order);

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
		        	Util.throwException("Transaction id is invalid. This is mostly due to duplicate  transaction id.");
		        }
		        if (order.isCurrencyInvalid()) {
		        	Util.throwException("Currency is invalid.");

		        }
		    } catch (ConnectionException e) {
		    }
		} else {
		    // inform validation errors to the user.
		    if (order.isTransactionIdInvalid()) {
		        Util.throwException("Transaction id is invalid.");
		    }
		    if (order.isAmountInvalid()) {
		      Util.throwException("Amount can not be less than 9.00.");
		    }
		    if (order.isCurrencyInvalid()) {
		      Util.throwException("Please provide the currency.");
		    }
		    if (order.isDescriptionInvalid()) {
		      Util.throwException("Description can not be greater than 255 characters.");
		        }
		    if (order.isEmailInvalid()) {
		      Util.throwException("Please provide valid Email Address.");
		    }
		    if (order.isNameInvalid()) {
		      Util.throwException("Name can not be greater than 100 characters.");
		    }
		    if (order.isPhoneInvalid()) {
		      Util.throwException("Phone is invalid.");
		    }
		    if (order.isRedirectUrlInvalid()) {
		      Util.throwException("Please provide valid Redirect url.");
		    }

		    if (order.isWebhookInvalid()) {
		      Util.throwException("Provide a valid webhook url");
		    }
		}
		
        return transactionResponse;
	}
	
	
	private PaymentOrder generateTransaction(PaymentOrder order) {
		
		order.setCurrency("INR");
		
		if(order.getDepositType().equals(YSYTConstants.SECURITY_DEPOSIT)){
			order.setTransactionId("YSYTSD_"+Util.getCurrentUser(httpRequest).getId()+order.getAccomodationId()+Util.getCurrentTimeStamp().getTime());
		}
		
		return order;
	}


	private void validatePayment(PaymentOrder order) {

		if(order.getDepositType().equals(YSYTConstants.SECURITY_DEPOSIT)){
			Accomodations accomodation = new Accomodations();
			
			if(Util.isNull(order.getAccomodationId())){
				Util.throwException("Accomdation Id is Mandatory");
			}
			
			accomodation = iAccomodationService.getAccomodation(order.getAccomodationId());
			if(!Util.isNull(accomodation)){
				if(accomodation.getIsFoodMandatory()){
					if(!accomodation.getIsFoodMandatory().equals(order.getIsFoodSelected())){
						Util.throwException("Food is Mandatory");
					}
				}
				
				if(!Util.isNull(order.getIsFoodSelected())){
					if(order.getIsFoodSelected()){
						if(!Util.isNull(order.getFoodCost())){
							if(!accomodation.getFoodCost().equals(order.getFoodCost())){
								Util.throwException("Food Cost Mismatches");
							}
						}else{
							Util.throwException("Please provide Food Cost");
						}
					}
				}
				if(!accomodation.getSecurityDeposit().equals(order.getAmount())){
					Util.throwException("Security Deposit Cost Mismatches");
	
				}
				
				if(!Util.isNull(order.getRoomCost())){
					if(!accomodation.getRoomCost().equals(order.getRoomCost())){
						Util.throwException("Room Cost Mismatches");
		
					}
				}else
				{
					Util.throwException("Please provide Room Cost");
				}
			}else{
				Util.throwException("Accomodation Doesn't Exist");
			}
		}
		
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
		    api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPoint,authEndPoint);

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
		    api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPoint,authEndPoint);

		     paymentOrderDetailsResponse = api.getPaymentOrderDetailsByTransactionId(transactionId);

		    // print the status of the payment order.
		    Util.throwException(paymentOrderDetailsResponse.getStatus());
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
		    api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPoint,authEndPoint);


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

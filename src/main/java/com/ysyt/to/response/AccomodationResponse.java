package com.ysyt.to.response;

import java.math.BigInteger;

import com.response.CommonResponse;
import com.ysyt.bean.Accomodations;


public class AccomodationResponse extends CommonResponse {

	private Accomodations accomodation;

	public Accomodations getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodations accomodation) {
		this.accomodation = accomodation;
	}
	
}

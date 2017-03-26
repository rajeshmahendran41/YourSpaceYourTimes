package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AccomodationTypes;


public class AccomodationTypeResponse extends CommonResponse {

	private List<AccomodationTypes> accomodationTypes;

	public List<AccomodationTypes> getAccomodationTypes() {
		return accomodationTypes;
	}

	public void setAccomodationTypes(List<AccomodationTypes> accomodationTypes) {
		this.accomodationTypes = accomodationTypes;
	}
	
}

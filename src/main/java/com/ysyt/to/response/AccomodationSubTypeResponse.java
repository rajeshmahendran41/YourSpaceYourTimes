package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AccomodationSubTypes;
import com.ysyt.bean.AccomodationTypes;


public class AccomodationSubTypeResponse extends CommonResponse {

	private List<AccomodationSubTypes> accomodationSubTypes;

	public List<AccomodationSubTypes> getAccomodationSubTypes() {
		return accomodationSubTypes;
	}

	public void setAccomodationSubTypes(
			List<AccomodationSubTypes> accomodationSubTypes) {
		this.accomodationSubTypes = accomodationSubTypes;
	}

	
	
}

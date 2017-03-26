package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AccomodationGenders;
import com.ysyt.bean.AccomodationTypes;


public class AccomodationGenderResponse extends CommonResponse {

	private List<AccomodationGenders> accomodationGender;

	public List<AccomodationGenders> getAccomodationGender() {
		return accomodationGender;
	}

	public void setAccomodationGender(List<AccomodationGenders> accomodationGender) {
		this.accomodationGender = accomodationGender;
	}

	
}

package com.ysyt.wrapper;

import java.util.List;

import com.ysyt.bean.AccomodationsDetails;

public class AccomodationsWrapper {

	private List<AccomodationsDetails> accomodationDetails;
	private Boolean isEnrolled;


	public List<AccomodationsDetails> getAccomodationDetails() {
		return accomodationDetails;
	}

	public void setAccomodationDetails(
			List<AccomodationsDetails> accomodationDetails) {
		this.accomodationDetails = accomodationDetails;
	}

	public Boolean getIsEnrolled() {
		return isEnrolled;
	}

	public void setIsEnrolled(Boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

}

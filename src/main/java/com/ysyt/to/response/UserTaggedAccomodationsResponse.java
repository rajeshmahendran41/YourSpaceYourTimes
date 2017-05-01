package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.Accomodations;
import com.ysyt.bean.UserAccomodationMapping;

public class UserTaggedAccomodationsResponse extends CommonResponse {

	public List<UserAccomodationMapping> getAccomodationList() {
		return accomodationList;
	}

	public void setAccomodationList(List<UserAccomodationMapping> accomodationList) {
		this.accomodationList = accomodationList;
	}

	private List<UserAccomodationMapping> accomodationList;

	

}

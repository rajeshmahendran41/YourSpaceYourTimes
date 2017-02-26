package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.Accomodations;

public class AccomodationListResponse extends CommonResponse {

	private List<Accomodations> accomodationList;

	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}


	public List<Accomodations> getAccomodationList() {
		return accomodationList;
	}

	public void setAccomodationList(List<Accomodations> accomodationList) {
		this.accomodationList = accomodationList;
	}

}

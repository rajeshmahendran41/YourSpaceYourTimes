package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.LocationBean;


public class LocationResponse extends CommonResponse {

	private List<LocationBean> locations;

	public List<LocationBean> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationBean> locations) {
		this.locations = locations;
	}

	
	
}

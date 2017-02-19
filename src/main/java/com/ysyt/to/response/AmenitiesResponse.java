package com.ysyt.to.response;


import java.util.List;
import java.util.Map;

import com.response.CommonResponse;
import com.ysyt.bean.AttributesMaster;

public class AmenitiesResponse  extends CommonResponse{

	private Map<String, List<AttributesMaster>> amenities;

	public Map<String, List<AttributesMaster>> getAmenities() {
		return amenities;
	}

	public void setAmenities(Map<String, List<AttributesMaster>> amenities) {
		this.amenities = amenities;
	}

	
	

}

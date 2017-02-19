package com.ysyt.to.response;


import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AttributesMaster;

public class AmenitiesMasterResponse  extends CommonResponse{

	private List<AttributesMaster> amenities;
	private AttributesMaster amenitiy;

	public AttributesMaster getAmenitiy() {
		return amenitiy;
	}

	public void setAmenitiy(AttributesMaster amenitiy) {
		this.amenitiy = amenitiy;
	}

	public List<AttributesMaster> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<AttributesMaster> amenities) {
		this.amenities = amenities;
	}

}

package com.ysyt.to.response;


import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;

public class AmenitiesMasterResponse  extends CommonResponse{

	private List<AttributesMaster> amenities;
	private AttributesMaster amenitiy;
	private AmenitiesMapping amenitiyMapping;

	public AmenitiesMapping getAmenitiyMapping() {
		return amenitiyMapping;
	}

	public void setAmenitiyMapping(AmenitiesMapping amenitiyMapping) {
		this.amenitiyMapping = amenitiyMapping;
	}

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

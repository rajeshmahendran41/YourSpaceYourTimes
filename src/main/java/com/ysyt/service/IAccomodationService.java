package com.ysyt.service;

import java.util.List;

import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.to.request.AmenitiesMasterRequest;


public interface IAccomodationService {

	List<AttributesMaster> getAmenitiesParent(AmenitiesMasterRequest request);

	AttributesMaster createAttributes(AttributesMaster request);

	AttributesMaster getAttributeMasterById(Long id);

	AmenitiesMapping createAmenitiyMapping(AmenitiesMapping request);

	

}

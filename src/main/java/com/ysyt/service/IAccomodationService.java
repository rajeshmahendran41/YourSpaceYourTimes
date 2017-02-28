package com.ysyt.service;

import java.util.List;
import java.util.Map;

import com.ysyt.bean.Accomodations;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.LocationBean;
import com.ysyt.bean.Uploads;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AccomodationRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.LocationRequest;


public interface IAccomodationService {

	List<AttributesMaster> getAmenitiesParent(AmenitiesMasterRequest request);

	AttributesMaster createAttributes(AttributesMaster request);

	AttributesMaster getAttributeMasterById(Long id);

	AmenitiesMapping createAmenitiyMapping(AmenitiesMapping request);

	Map<String, List<AttributesMaster>> getAmenitiesList(Long typeId, String sourceName);

	Accomodations createAccomodation(AccomodationRequest request);

	List<LocationBean> getLocationDetails(LocationRequest request);

	Accomodations getAccomodation(Long accomodationId);

	Map<String, Object> getAccomodationList(AccomodationListRequest request);

	Uploads createUploads(Uploads upload);

}

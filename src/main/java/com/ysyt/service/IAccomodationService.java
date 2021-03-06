package com.ysyt.service;

import java.util.List;
import java.util.Map;

import com.ysyt.bean.AccomodationGenders;
import com.ysyt.bean.AccomodationSubTypes;
import com.ysyt.bean.Accomodations;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributeOptions;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.LocationBean;
import com.ysyt.bean.Uploads;
import com.ysyt.bean.UserAccomodationMapping;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AccomodationRequest;
import com.ysyt.to.request.AccomodationSubTypesRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.AttributeListRequest;
import com.ysyt.to.request.FilterRequest;
import com.ysyt.to.request.LocationRequest;
import com.ysyt.to.request.UserTaggedAccomdoationRequest;
import com.ysyt.to.response.AccomodationTypeResponse;
import com.ysyt.to.response.FilterResponse;
import com.ysyt.to.response.UserTaggedAccomodationsResponse;


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

	FilterResponse getAccomodationFilter(FilterRequest request);

	AccomodationTypeResponse getAccomodationTypes();

	List<AccomodationGenders> getAccomodationGender();

	List<AccomodationSubTypes> getAccomodationSubTypes(
			AccomodationSubTypesRequest request);

	AttributeOptions createAttributeOptions(AttributeOptions request);
	
	AttributeOptions getAttributeOptionById(Long id);

	List<AttributeOptions> getAttributeOptionList(AttributeListRequest request);

	Map<String, List<AttributesMaster>> fetchAmenities(Long id);

	List<UserAccomodationMapping> getUserTaggedAccomodationDetails(
			UserTaggedAccomdoationRequest request);

}

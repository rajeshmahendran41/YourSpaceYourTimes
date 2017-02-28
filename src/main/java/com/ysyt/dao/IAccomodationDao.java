package com.ysyt.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.ysyt.bean.Accomodations;
import com.ysyt.bean.AccomodationsDetails;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.LocationBean;
import com.ysyt.bean.Uploads;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.LocationRequest;


public interface IAccomodationDao {

	List<AttributesMaster> getAmenitiesParent(AmenitiesMasterRequest request,
			SessionFactory sessionFactory);


	AttributesMaster getAttributeMasterById(Long id,
			SessionFactory sessionFactory);


	AttributesMaster createOrUpdateAttributeMaster(
			AttributesMaster oldAttributeBean, SessionFactory sessionFactory);


	AmenitiesMapping getAmenitiesMappingById(Long id, SessionFactory sessionFactory);


	AmenitiesMapping createOrUpdateAmenitiesMapping(
			AmenitiesMapping oldAttributeBean, SessionFactory sessionFactory);


	List<AmenitiesMapping> getAmenitiesMappingList(Long typeId,
			String sourceName, SessionFactory sessionFactory);


	Accomodations getAccomodatoinById(Long id, SessionFactory sessionFactory);


	Accomodations createOrUpdateAccomodation(Accomodations oldAccomodation,
			SessionFactory sessionFactory);


	AccomodationsDetails getAccomodationDetailsById(Long id,
			SessionFactory sessionFactory);


	void createOrUpdateAccomodationDetails(
			AccomodationsDetails acccomodationDetails,
			SessionFactory sessionFactory);


	List<LocationBean> getLocationDetails(LocationRequest request,
			SessionFactory sessionFactory);


	Map<String, Object> getAccomodationList(AccomodationListRequest request,
			SessionFactory sessionFactory);


	Uploads createUpload(Uploads upload, SessionFactory sessionFactory);

	
}

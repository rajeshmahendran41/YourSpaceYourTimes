
package com.ysyt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.horizontals.filter.DataTransformer.FilterDataTransformer;
import com.horizontals.filter.wrapper.EntityWrapper;
import com.horizontals.filter.wrapper.FilterConstant;
import com.horizontals.filter.wrapper.FilterObject;
import com.horizontals.filter.wrapper.FilterWrapper;
import com.ysyt.bean.AccomodationGenders;
import com.ysyt.bean.AccomodationSubTypes;
import com.ysyt.bean.Accomodations;
import com.ysyt.bean.AccomodationsDetails;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributeOptions;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.bean.LocationBean;
import com.ysyt.bean.Uploads;
import com.ysyt.dao.IAccomodationDao;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AccomodationRequest;
import com.ysyt.to.request.AccomodationSubTypesRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.AttributeListRequest;
import com.ysyt.to.request.FilterRequest;
import com.ysyt.to.request.LocationRequest;
import com.ysyt.to.response.AccomodationTypeResponse;
import com.ysyt.to.response.FilterResponse;
import com.ysyt.wrapper.ParentAmenityHelper;

@Service
@Transactional
public class AccomodationServiceImpl implements IAccomodationService {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private HttpServletRequest httpRequest;
	
	@Autowired
	private IAccomodationDao iAccomodationDao;

	@Override
	public List<AttributesMaster> getAmenitiesParent(
			AmenitiesMasterRequest request) {
		
		
		
		return iAccomodationDao.getAmenitiesParent(request,sessionFactory);
	}

	
	@Override
	public AttributesMaster createAttributes(AttributesMaster oldAttributeBean) {
		
		if(!Util.isNull(oldAttributeBean.getId())){
			AttributesMaster currentAttributeBean = getAttributeMasterById(oldAttributeBean.getId());
		
			if(!Util.isNull(currentAttributeBean)){			
				oldAttributeBean = updateAttributeDetails(oldAttributeBean,currentAttributeBean);
			}
		}
		
		oldAttributeBean = iAccomodationDao.createOrUpdateAttributeMaster(oldAttributeBean,sessionFactory);
		
		return oldAttributeBean;
	}
	
	
	@Override
	public AttributesMaster getAttributeMasterById(Long id) {
		
		return iAccomodationDao.getAttributeMasterById(id,sessionFactory);

	}

	private AttributesMaster updateAttributeDetails(AttributesMaster oldAttributesMaster,
			AttributesMaster currentAttributesMaster) {
		
		if(!Util.isNull(currentAttributesMaster.getTitle())){
			oldAttributesMaster.setTitle(currentAttributesMaster.getTitle());
		}
		
		if(!Util.isNull(currentAttributesMaster.getDescription())){
			oldAttributesMaster.setDescription(currentAttributesMaster.getDescription());
		}
		
		if(!Util.isNull(currentAttributesMaster.getIsAmenities())){
			oldAttributesMaster.setIsAmenities(currentAttributesMaster.getIsAmenities());
		}
		
		if(!Util.isNull(currentAttributesMaster.getIsDeleted())){
			oldAttributesMaster.setIsDeleted(currentAttributesMaster.getIsDeleted());
		}
		
		if(!Util.isNull(currentAttributesMaster.getParentId())){
			oldAttributesMaster.setParentId(currentAttributesMaster.getParentId());
		}
			
		
		return oldAttributesMaster;
	}


	@Override
	public AmenitiesMapping createAmenitiyMapping(AmenitiesMapping oldAttributeBean) {
		
		if(!Util.isNull(oldAttributeBean.getId())){
			AmenitiesMapping currentAttributeBean = getAmenitiesMappingById(oldAttributeBean.getId());
		
			if(!Util.isNull(currentAttributeBean)){			
				oldAttributeBean = updateAmenitiesMappingDetails(oldAttributeBean,currentAttributeBean);
			}
		}
		
		oldAttributeBean = iAccomodationDao.createOrUpdateAmenitiesMapping(oldAttributeBean,sessionFactory);
		
		return oldAttributeBean;
		
	}


	private AmenitiesMapping updateAmenitiesMappingDetails(
			AmenitiesMapping currentAttributeBean,
			AmenitiesMapping oldAttributeBean) {
		
		if(!Util.isNull(currentAttributeBean.getAttributeId())){
			oldAttributeBean.setAttributeId(currentAttributeBean.getAttributeId());
		}
		
		if(!Util.isNull(currentAttributeBean.getParentAmenitiyId())){
			oldAttributeBean.setParentAmenitiyId(currentAttributeBean.getParentAmenitiyId());
		}
		if(!Util.isNull(currentAttributeBean.getIsDeleted())){
			oldAttributeBean.setIsDeleted(currentAttributeBean.getIsDeleted());
		}
				
		return oldAttributeBean;
	}

	private AmenitiesMapping getAmenitiesMappingById(Long id) {
		
		return iAccomodationDao.getAmenitiesMappingById(id,sessionFactory);
	}


	@Override
	public Map<String, List<AttributesMaster>> getAmenitiesList(Long typeId,String sourceName) {

		Map<String, List<AttributesMaster>> mapAmenities = new HashMap<>();
		List<AmenitiesMapping> amenitiesMapping = new ArrayList<>();
		Set<ParentAmenityHelper> parentAmenities = new  HashSet<>();
		List<AttributesMaster> attributeMaster = null;
		
		amenitiesMapping = iAccomodationDao.getAmenitiesMappingList(typeId,sourceName, sessionFactory);
		
		for(AmenitiesMapping amenitiesSet : amenitiesMapping){
			ParentAmenityHelper parentAmenitiy = new ParentAmenityHelper();
			parentAmenitiy.setParentId(amenitiesSet.getParentBean().getId());
			parentAmenitiy.setTitle(amenitiesSet.getParentBean().getTitle());

			parentAmenities.add(parentAmenitiy);
		}
		
		for(ParentAmenityHelper parentAmenity : parentAmenities){
			
			attributeMaster = new ArrayList<>();
			
			for(AmenitiesMapping amenities : amenitiesMapping){
				
				if(parentAmenity.getTitle().equals(amenities.getParentBean().getTitle())){
					AttributesMaster amenityMaster =  new AttributesMaster(); 
					amenityMaster = amenities.getAttributeBean();
					amenityMaster.setParentId(parentAmenity.getParentId());
					attributeMaster.add(amenityMaster);					
				}
				
			}
			mapAmenities.put(parentAmenity.getTitle(), attributeMaster);
		}
		
		return mapAmenities;
	}


	@Override
	public Accomodations createAccomodation(AccomodationRequest request) {
		
		Accomodations oldAccomodation = request.getAccomodation();
		
		List<AccomodationsDetails> accomodationDetailsBean = request.getAccomodation().getAccomodationDetails();
		
		if(!Util.isNull(oldAccomodation.getId())){
			Accomodations currentAccomodation = getAccomodationById(oldAccomodation.getId());
		
			if(!Util.isNull(currentAccomodation)){			
				oldAccomodation = updateAccomodationDetails(oldAccomodation,currentAccomodation);
			}else{
				Util.throwException("Resource Not Available");
			}
		}else{
			oldAccomodation.setCreatedAt(Util.getCurrentTimeStamp());
			oldAccomodation.setCreatedBy(Util.getUserId(httpRequest));
		}

		oldAccomodation.setUpdatedAt(Util.getCurrentTimeStamp());
		oldAccomodation.setUpdatedBy(Util.getUserId(httpRequest));
		
		oldAccomodation = iAccomodationDao.createOrUpdateAccomodation(oldAccomodation,sessionFactory);
		
		if(!Util.isNull(oldAccomodation)){
			
			if(!Util.isNullList(accomodationDetailsBean)){
				
				for(AccomodationsDetails acccomodationDetails : accomodationDetailsBean){
				
					if(!Util.isNull(acccomodationDetails.getId())){
						AccomodationsDetails currentAccomodationDetails = getAccomodationDetailsById(acccomodationDetails.getId());
					
						if(!Util.isNull(currentAccomodationDetails)){			
							acccomodationDetails = updateExtraAccomodationDetailBean(acccomodationDetails,currentAccomodationDetails);
						}else{
							Util.throwException("Extra Details Resource Not Available");
						}
					}else{
						acccomodationDetails.setCreatedAt(Util.getCurrentTimeStamp());
						acccomodationDetails.setCreatedBy(Util.getUserId(httpRequest));
						acccomodationDetails.setSourceId(request.getAccomodation().getId());
						acccomodationDetails.setSourceType("accomodation");
					}
					
					
					acccomodationDetails.setCreatedAt(Util.getCurrentTimeStamp());
					acccomodationDetails.setCreatedBy(Util.getUserId(httpRequest));
					acccomodationDetails.setSourceId(request.getAccomodation().getId());
					acccomodationDetails.setSourceType("accomodation");
					acccomodationDetails.setUpdatedAt(Util.getCurrentTimeStamp());
					acccomodationDetails.setUpdatedBy(Util.getUserId(httpRequest));
					
					iAccomodationDao.createOrUpdateAccomodationDetails(acccomodationDetails,sessionFactory);
					
				}
				
			}
			
		}
		
				
		return oldAccomodation;
		
	}


	private AccomodationsDetails updateExtraAccomodationDetailBean(
			AccomodationsDetails acccomodationDetails,
			AccomodationsDetails currentAccomodationDetails) {
		

		if(!Util.isNull(acccomodationDetails.getAttributeId())){
			currentAccomodationDetails.setAttributeId(acccomodationDetails.getAttributeId());
		}
		
		if(!Util.isNull(acccomodationDetails.getIsAmenities())){
			currentAccomodationDetails.setIsAmenities(acccomodationDetails.getIsAmenities());
		}

		if(!Util.isNull(acccomodationDetails.getIsDeleted())){
			currentAccomodationDetails.setIsDeleted(acccomodationDetails.getIsDeleted());
		}
		
		if(!Util.isNull(acccomodationDetails.getOrderId())){
			currentAccomodationDetails.setOrderId(acccomodationDetails.getOrderId());
		}
		
		
		if(!Util.isNull(acccomodationDetails.getParentId())){
			currentAccomodationDetails.setParentId(acccomodationDetails.getParentId());
		}
		
		if(!Util.isNull(acccomodationDetails.getSourceId())){
			currentAccomodationDetails.setParentId(acccomodationDetails.getSourceId());
		}
		
		if(!Util.isNull(acccomodationDetails.getSourceType())){
			currentAccomodationDetails.setSourceType(acccomodationDetails.getSourceType());
		}
		
		if(!Util.isNull(acccomodationDetails.getValue())){
			currentAccomodationDetails.setValue(acccomodationDetails.getValue());
		}
		
		
		return currentAccomodationDetails;
		
	}


	private AccomodationsDetails getAccomodationDetailsById(Long id) {
		
		return iAccomodationDao.getAccomodationDetailsById(id, sessionFactory);
	}


	private Accomodations updateAccomodationDetails(
			Accomodations oldAccomodation, Accomodations currentAccomodation) {
		
		if(!Util.isNull(currentAccomodation.getTitle())){
			oldAccomodation.setTitle(currentAccomodation.getTitle());
		}
		
		if(!Util.isNull(currentAccomodation.getDescription())){
			oldAccomodation.setDescription(currentAccomodation.getDescription());
		}
		
		if(!Util.isNull(currentAccomodation.getAddress())){
			oldAccomodation.setAddress(currentAccomodation.getAddress());
		}
		
		if(!Util.isNull(currentAccomodation.getLocationId())){
			oldAccomodation.setLocationId(currentAccomodation.getLocationId());
		}
		
		if(!Util.isNull(currentAccomodation.getRoomCost())){
			oldAccomodation.setRoomCost(currentAccomodation.getRoomCost());
		}
		
		if(!Util.isNull(currentAccomodation.getFoodCost())){
			oldAccomodation.setFoodCost(currentAccomodation.getFoodCost());
		}
		
		if(!Util.isNull(currentAccomodation.getSecurityDeposit())){
			oldAccomodation.setSecurityDeposit(currentAccomodation.getSecurityDeposit());
		}
		
		if(!Util.isNull(currentAccomodation.getAdditionalCost())){
			oldAccomodation.setAdditionalCost(currentAccomodation.getAdditionalCost());
		}
		
		if(!Util.isNull(currentAccomodation.getCurrentRoomCount())){
			oldAccomodation.setCurrentRoomCount(currentAccomodation.getCurrentRoomCount());
		}
		
		if(!Util.isNull(currentAccomodation.getIsDeleted())){
			oldAccomodation.setIsDeleted(currentAccomodation.getIsDeleted());
		}
		
		if(!Util.isNull(currentAccomodation.getLatitude())){
			oldAccomodation.setLatitude(currentAccomodation.getLatitude());
		}
		
		if(!Util.isNull(currentAccomodation.getLongitude())){
			oldAccomodation.setLongitude(currentAccomodation.getLongitude());
		}
		
		if(!Util.isNull(currentAccomodation.getTotalRoomCount())){
			oldAccomodation.setTotalRoomCount(currentAccomodation.getTotalRoomCount());
		}
		if(!Util.isNull(currentAccomodation.getTypeId())){
			oldAccomodation.setTypeId(currentAccomodation.getTypeId());
		}
		
		if(!Util.isNull(currentAccomodation.getIsFoodMandatory())){
			oldAccomodation.setIsFoodMandatory(currentAccomodation.getIsFoodMandatory());
		}
		if(!Util.isNull(currentAccomodation.getGenderAvailabilityId())){
			oldAccomodation.setGenderAvailabilityId(currentAccomodation.getGenderAvailabilityId());
		}
		
		if(!Util.isNull(currentAccomodation.getSubTypeId())){
			oldAccomodation.setSubTypeId(currentAccomodation.getSubTypeId());
		}
		
		
		return oldAccomodation;
		
	}


	private Accomodations getAccomodationById(Long id) {
		
		return iAccomodationDao.getAccomodatoinById(id,sessionFactory);
		
	}


	@Override
	public List<LocationBean> getLocationDetails(LocationRequest request) {
		
		return iAccomodationDao.getLocationDetails(request,sessionFactory);
	}


	@Override
	public Accomodations getAccomodation(Long accomodationId) {

		return iAccomodationDao.getAccomodatoinById(accomodationId, sessionFactory);
	}


	@Override
	public Map<String, Object> getAccomodationList(
			AccomodationListRequest request) {
		return iAccomodationDao.getAccomodationList(request,sessionFactory);
	}


	@Override
	public Uploads createUploads(Uploads upload) {
		
		upload.setCreatedAt(Util.getCurrentTimeStamp());
		upload.setCreatedBy(Util.getUserId(httpRequest));
		upload.setUpdatedAt(Util.getCurrentTimeStamp());
		upload.setUpdatedBy(Util.getUserId(httpRequest));
		
		return iAccomodationDao.createUpload(upload,sessionFactory);
	}


	@Override
	public FilterResponse getAccomodationFilter(FilterRequest request) {
		
		FilterResponse response = new FilterResponse();	
		FilterWrapper wrapper = new FilterWrapper();	
		List<FilterObject> filterList = new ArrayList<>();
		
		List<EntityWrapper> roomTypes= new ArrayList<>();
		roomTypes.add(new EntityWrapper((long) 1,"Paying Guest"));
		roomTypes.add(new EntityWrapper((long)2, "Hostel"));
		roomTypes.add(new EntityWrapper((long)3, "Rental Flat"));
		
		
		
		filterList.add(FilterDataTransformer.convertFilterData("Space Type", "spaceTypes", FilterConstant.RADIO, roomTypes));
		
		Double minRange = iAccomodationDao.getMinPriceRange("MIN",request.getLocationIds(),request.getTypeId(),sessionFactory);
		Double maxRange = iAccomodationDao.getMinPriceRange("MAX",request.getLocationIds(),request.getTypeId(),sessionFactory);

		
		filterList.add(FilterDataTransformer.convertScrollFilterData("Price Range", "priceRange", FilterConstant.SLIDER, minRange,maxRange,minRange,maxRange));
		
		wrapper.setFilter(filterList);
		response.setFilterData(wrapper);
		
		return response;
	}


	@Override
	public AccomodationTypeResponse getAccomodationTypes() {
		return iAccomodationDao.getAccomodationTypes(sessionFactory);
	}


	@Override
	public List<AccomodationGenders> getAccomodationGender() {
		
		return iAccomodationDao.getAccomodationGender(sessionFactory);
	}


	@Override
	public List<AccomodationSubTypes> getAccomodationSubTypes(
			AccomodationSubTypesRequest request) {
		return iAccomodationDao.getAccomodationSubTypes(request,sessionFactory);
	}


	@Override
	public AttributeOptions createAttributeOptions(AttributeOptions request) {
		
				
		if(!Util.isNull(request.getId())){
			AttributeOptions currentAttributeOptions = getAttributeOptionById(request.getId());
		
			if(!Util.isNull(currentAttributeOptions)){			
				request = updateAttributeOptionDetails(currentAttributeOptions,request);
			}else{
				Util.throwException("Resource Not Available");
			}
		}

		return iAccomodationDao.createAttributeOptions(request,sessionFactory);
			
	}

	private AttributeOptions updateAttributeOptionDetails(
			AttributeOptions  currentAttributeOptions, AttributeOptions request) {
		
		if(!Util.isNull(request.getAttributeId())){
			currentAttributeOptions.setAttributeId(request.getAttributeId());
		}
		if(!Util.isNull(request.getAttributeValues())){
			currentAttributeOptions.setAttributeValues(request.getAttributeValues());
		}
		if(!Util.isNull(request.getEntityId())){
			currentAttributeOptions.setEntityId(request.getEntityId());
		}
		if(!Util.isNull(request.getEntityType())){
			currentAttributeOptions.setEntityType(request.getEntityType());
		}
		if(!Util.isNull(request.getIsDeleted())){
			currentAttributeOptions.setIsDeleted(request.getIsDeleted());
		}

		
		return currentAttributeOptions;
		
	}


	@Override
	public AttributeOptions getAttributeOptionById(Long id) {
		
		return iAccomodationDao.getAttributeOptionById(id, sessionFactory);

		
	}


	@Override
	public List<AttributeOptions> getAttributeOptionList(
			AttributeListRequest request) {
		
		return iAccomodationDao.getAttributeOptionList(request, sessionFactory);

	}


	@Override
	public Map<String, List<AttributesMaster>> fetchAmenities(Long id) {
		
		
		Map<String, List<AttributesMaster>> mapAmenities = new HashMap<>();
		List<AccomodationsDetails> amenitiesMapping = new ArrayList<>();
		Set<ParentAmenityHelper> parentAmenities = new  HashSet<>();
		List<AttributesMaster> attributeMaster = null;
		
		amenitiesMapping = iAccomodationDao.getAmenitiesBasedonAccomodations(id, sessionFactory);
		
		for(AccomodationsDetails amenitiesSet : amenitiesMapping){
			ParentAmenityHelper parentAmenitiy = new ParentAmenityHelper();
			parentAmenitiy.setParentId(amenitiesSet.getParentAttributes().getId());
			parentAmenitiy.setTitle(amenitiesSet.getParentAttributes().getTitle());

			parentAmenities.add(parentAmenitiy);
		}
		
		for(ParentAmenityHelper parentAmenity : parentAmenities){
			
			attributeMaster = new ArrayList<>();
			
			for(AccomodationsDetails amenities : amenitiesMapping){
				
				if(parentAmenity.getTitle().equals(amenities.getParentAttributes().getTitle())){
					AttributesMaster amenityMaster =  new AttributesMaster(); 
					amenityMaster = amenities.getAttributes();
					amenityMaster.setParentId(parentAmenity.getParentId());
					attributeMaster.add(amenityMaster);					
				}
				
			}
			mapAmenities.put(parentAmenity.getTitle(), attributeMaster);
		}
		
		return mapAmenities;
	}
	
	

}

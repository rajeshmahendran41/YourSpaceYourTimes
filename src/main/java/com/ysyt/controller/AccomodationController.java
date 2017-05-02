
package com.ysyt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.constants.CommonConstants;
import com.ysyt.bean.AccomodationTypes;
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributeOptions;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AccomodationRequest;
import com.ysyt.to.request.AccomodationSubTypesRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.AttributeListRequest;
import com.ysyt.to.request.FilterRequest;
import com.ysyt.to.request.LocationRequest;
import com.ysyt.to.request.UserTaggedAccomdoationRequest;
import com.ysyt.to.response.AccomodationGenderResponse;
import com.ysyt.to.response.AccomodationListResponse;
import com.ysyt.to.response.AccomodationResponse;
import com.ysyt.to.response.AccomodationSubTypeResponse;
import com.ysyt.to.response.AccomodationTypeResponse;
import com.ysyt.to.response.AmenitiesMasterResponse;
import com.ysyt.to.response.AmenitiesResponse;
import com.ysyt.to.response.AttributeOptionListResponse;
import com.ysyt.to.response.AttributeOptionResponse;
import com.ysyt.to.response.FilterResponse;
import com.ysyt.to.response.LocationResponse;
import com.ysyt.to.response.UserTaggedAccomodationsResponse;
import com.ysyt.wrapper.DataTransformer;

@RestController
@RequestMapping(value="api/accomodation/")
public class AccomodationController {
	
	
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	@Autowired
	private IAccomodationService iAccomodationService;
	
	@RequestMapping(value = "amenitiesParent", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AmenitiesMasterResponse getAmenitiesParent(@RequestBody AmenitiesMasterRequest request ){
        
		AmenitiesMasterResponse res = new AmenitiesMasterResponse();
		res.setAmenities(iAccomodationService.getAmenitiesParent(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "attributes", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AmenitiesMasterResponse createAttributes(@RequestBody AttributesMaster request ){
        
		AmenitiesMasterResponse res = new AmenitiesMasterResponse();
		res.setAmenitiy(iAccomodationService.createAttributes(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "amenitiesMapping", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AmenitiesMasterResponse createAmenitiesMapping(@RequestBody AmenitiesMapping request ){
        
		AmenitiesMasterResponse res = new AmenitiesMasterResponse();
		res.setAmenitiyMapping(iAccomodationService.createAmenitiyMapping(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "amenitiesList", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AmenitiesResponse getAmenititesList(@RequestParam("typeId") Long typeId,@RequestParam("sourceName") String sourceName ){
        
		AmenitiesResponse res = new AmenitiesResponse();
		res.setAmenities(iAccomodationService.getAmenitiesList(typeId,sourceName));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "amenities", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AmenitiesResponse getAmenititesList(@RequestParam("id") Long id ){
        
		AmenitiesResponse res = new AmenitiesResponse();
		res.setAmenities(iAccomodationService.fetchAmenities(id));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	
	@RequestMapping(value = "createUpdate", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AccomodationResponse createAccomodation(@RequestBody AccomodationRequest request){
        
		AccomodationResponse res = new AccomodationResponse();
		res.setAccomodation(iAccomodationService.createAccomodation(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "locations", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public LocationResponse getLocations(@RequestBody LocationRequest request){
        
		LocationResponse res = new LocationResponse();
		res.setLocations(iAccomodationService.getLocationDetails(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AccomodationResponse getAccomodation(@PathVariable(value="id") Long accomodationId){
        
		AccomodationResponse res = new AccomodationResponse();
		res.setAccomodation(iAccomodationService.getAccomodation(accomodationId));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AccomodationListResponse getAccomodationList(@RequestBody AccomodationListRequest request){
        
		AccomodationListResponse res = new AccomodationListResponse();
		
		request = DataTransformer.convertFilter(request);
		
		res = DataTransformer.transformListResult(iAccomodationService.getAccomodationList(request));
		
		
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	
	@RequestMapping(value = "filter", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public FilterResponse getAccomodationFilter(@RequestBody FilterRequest request){
        
		FilterResponse res = new FilterResponse();
		
		res = iAccomodationService.getAccomodationFilter(request);
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "accomodationTypes", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AccomodationTypeResponse getAccomodationTypes(){
        
		AccomodationTypeResponse res = new AccomodationTypeResponse();
		
		res = iAccomodationService.getAccomodationTypes();
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	
	@RequestMapping(value = "accomodationGender", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AccomodationGenderResponse getAccomodationGender(){
        
		AccomodationGenderResponse res = new AccomodationGenderResponse();
		
		res.setAccomodationGender(iAccomodationService.getAccomodationGender());
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	
	}
	
	@RequestMapping(value = "accomodationSubTypes", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AccomodationSubTypeResponse getAccomodationSubTypes(@RequestBody AccomodationSubTypesRequest request){
        
		AccomodationSubTypeResponse res = new AccomodationSubTypeResponse();
		
		res.setAccomodationSubTypes(iAccomodationService.getAccomodationSubTypes(request));
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	
	}
	
	@RequestMapping(value = "attributeOptions", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AttributeOptionResponse createAttributeOptions(@RequestBody AttributeOptions request){
        
		AttributeOptionResponse res = new AttributeOptionResponse();
		res.setAttributeOptions(iAccomodationService.createAttributeOptions(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	
	@RequestMapping(value = "attributeOptions/list", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AttributeOptionListResponse getAttributeOptionList(@RequestBody AttributeListRequest request){
        
		AttributeOptionListResponse res = new AttributeOptionListResponse();
		
		res.setAttributeOptionList(iAccomodationService.getAttributeOptionList(request));
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	
	}
	
	
	@RequestMapping(value = "userTaggedAccomodations/list", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public UserTaggedAccomodationsResponse getAttributeOptionList(@RequestBody UserTaggedAccomdoationRequest request){
        
		UserTaggedAccomodationsResponse res = new UserTaggedAccomodationsResponse();
		
		res.setAccomodationList(iAccomodationService.getUserTaggedAccomodationDetails(request));
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	
	}
	
	
}

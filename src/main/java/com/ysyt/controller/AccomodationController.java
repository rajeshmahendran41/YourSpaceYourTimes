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
import com.ysyt.bean.AmenitiesMapping;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.request.AccomodationRequest;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.request.LocationRequest;
import com.ysyt.to.response.AccomodationListResponse;
import com.ysyt.to.response.AccomodationResponse;
import com.ysyt.to.response.AmenitiesMasterResponse;
import com.ysyt.to.response.AmenitiesResponse;
import com.ysyt.to.response.LocationResponse;
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
	
	@RequestMapping(value = "createUpdate", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AccomodationResponse createAccomodation(@RequestBody AccomodationRequest request){
        
		AccomodationResponse res = new AccomodationResponse();
		res.setAccomodation(iAccomodationService.createAccomodation(request));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "locaions", method = RequestMethod.POST, produces ="application/json")
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
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AccomodationListResponse getAccomodationList(@RequestBody AccomodationListRequest request){
        
		AccomodationListResponse res = new AccomodationListResponse();
		
		res = DataTransformer.transformListResult(iAccomodationService.getAccomodationList(request));
		
		
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
}

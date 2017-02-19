package com.ysyt.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.boot.jaxb.hbm.spi.AttributeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.constants.CommonConstants;
import com.ysyt.bean.AttributesMaster;
import com.ysyt.service.IAccomodationService;
import com.ysyt.to.request.AmenitiesMasterRequest;
import com.ysyt.to.response.AmenitiesMasterResponse;
import com.ysyt.to.response.AuthResponse;

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
}

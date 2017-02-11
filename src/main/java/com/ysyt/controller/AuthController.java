package com.ysyt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.constants.CommonConstants;
import com.ysyt.service.IAuthService;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

@RestController(value="api/auth")
public class AuthController {
	
	@Autowired
	private IAuthService iAuthService;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public SignupResponse getDatabaseConnection(@RequestBody SignupRequest request ){
        
		SignupResponse res = new SignupResponse();
		
		res = iAuthService.setSignupResponse(request);
		
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
 }
	

}

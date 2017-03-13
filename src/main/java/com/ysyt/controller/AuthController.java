package com.ysyt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Util.Util;
import com.constants.CommonConstants;
import com.ysyt.bean.UserBean;
import com.ysyt.service.IAuthService;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.PasswordRequest;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.AuthResponse;
import com.ysyt.to.response.RoleResponse;

@RestController
@RequestMapping(value="api/auth/")
public class AuthController {
	
	@Autowired
	private IAuthService iAuthService;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AuthResponse signUp(@RequestBody SignupRequest request ){
        
		AuthResponse res = new AuthResponse();
		res = iAuthService.setSignupResponse(request);
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AuthResponse loginAction(@RequestBody LoginRequest loginRequest ){
        
		AuthResponse res = new AuthResponse();
		res = iAuthService.loginAction(loginRequest);
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AuthResponse changePassword(@RequestBody PasswordRequest passwordRequest ){
        
		AuthResponse res = new AuthResponse();
		res = iAuthService.changePassword(passwordRequest);
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST, produces ="application/json")
    @ResponseBody
    public AuthResponse changePassword(@RequestBody UserBean userDetails ){
        
		AuthResponse res = new AuthResponse();
		res.setUserBean(iAuthService.updateUserDetails(userDetails));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/currentUser", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public AuthResponse getCurrentUserDetails(){
        
		AuthResponse res = new AuthResponse();
		res.setUserBean(Util.getCurrentUser(httpRequest));
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}
	
	@RequestMapping(value = "/currentRole", method = RequestMethod.GET, produces ="application/json")
    @ResponseBody
    public RoleResponse getCurrentUserRole(){
        
		RoleResponse res = new RoleResponse();
		res.setRoles(Util.getCurrentUser(httpRequest).getRoles());
		res.setMessage(CommonConstants.SUCCESS);
		res.setStatus(CommonConstants.OK);
		
        return res;
	}

}

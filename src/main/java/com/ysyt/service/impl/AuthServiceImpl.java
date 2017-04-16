
package com.ysyt.service.impl;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Util.Util;
import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.constants.SessionConstant;
import com.ysyt.dao.IAuthDao;
import com.ysyt.service.IAuthService;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.PasswordRequest;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.AuthResponse;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private IAuthDao iAuthDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private HttpServletRequest httpRequest;

	@Override
	public AuthResponse setSignupResponse(SignupRequest request) {
		
		AuthResponse res = new AuthResponse();
		
		if(validateEmail(request.getLoginDetails().getEmail())){
			
			Util.throwPrimeException("Email Id already Exist");
			
		}
		

		request.getUserDetails().setCreatedAt(Util.getCurrentTimeStamp());
		request.getUserDetails().setUpdatedAt(Util.getCurrentTimeStamp());
		res.setUserBean(createUpdateUserBean(request.getUserDetails()));
		
		if(!Util.isNull(request.getUserDetails())){
			HttpSession httpSession = httpRequest.getSession(true);
	        httpSession.setAttribute(SessionConstant.USER_BEAN,
	                request.getUserDetails());
		}
		
		if(!Util.isNull(request.getUserDetails().getId())){
			request.getLoginDetails().setUserId(request.getUserDetails().getId());
			request.getLoginDetails().setCreatedBy(request.getUserDetails().getId());
			request.getLoginDetails().setUpdatedBy(request.getUserDetails().getId());
			request.getLoginDetails().setCreatedAt(Util.getCurrentTimeStamp());
			request.getLoginDetails().setUpdatedAt(Util.getCurrentTimeStamp());
		}
		if(!Util.isNull(request.getLoginDetails().getPassword())){
			request.getLoginDetails().setPassword(Util.getEncryptedPassword(request.getLoginDetails().getPassword()));
		}
		
		createUpdateLoginDetails(request.getLoginDetails());
		return res;
	}
	
	@Override
	public UserBean createUpdateUserBean(UserBean userBean) {
				
		return iAuthDao.createUpdateUserBean(userBean,sessionFactory);
	}
	
	@Override
	public Boolean validateEmail(String email) {
				
		return iAuthDao.validateEmail(email,sessionFactory);
	}

	@Override
	public void createUpdateLoginDetails(LoginCredentials loginCredentials) {
		
		iAuthDao.createUpdateLoginDetails(loginCredentials,sessionFactory);

	}
	
	
	@Override
	public UserBean getUserBean(Long userId) {
		
		return iAuthDao.getUserBean(userId,sessionFactory);

	}

	@Override
	public AuthResponse loginAction(LoginRequest loginRequest) {
		
		AuthResponse respone = new AuthResponse();
		UserBean userBean = new UserBean();
		
						
		Long userId = iAuthDao.checkLogin(loginRequest,sessionFactory);
		
		if(!Util.isNull(userId)){
		
			userBean = getUserBean(userId);
			if(!Util.isNull(userBean)){
				respone.setUserBean(userBean);
				HttpSession httpSession = httpRequest.getSession(true);
		        httpSession.setAttribute(SessionConstant.USER_BEAN,
		             userBean);
			}
		}else{
			Util.throwPrimeException("Incorrect UserName or Password");
		}
		
		return respone;
	}

	@Override
	public AuthResponse changePassword(PasswordRequest passwordRequest) {
		
		AuthResponse res = new AuthResponse();
	
		LoginCredentials credentials = iAuthDao.checkLoginEmail(passwordRequest, sessionFactory);
		
		if(!Util.isNull(credentials)){
			if(!Util.isNull(passwordRequest.getNewPassword())){
				credentials.setPassword(Util.getEncryptedPassword(passwordRequest.getNewPassword()));
				credentials.setUpdatedAt(Util.getCurrentTimeStamp());
				credentials.setUpdatedBy(Util.getUserId(httpRequest));
			}
			
			iAuthDao.createUpdateLoginDetails(credentials,sessionFactory);
			
			res.setUserBean(Util.getCurrentUser(httpRequest));
		}else{
			Util.throwPrimeException("Incorrect Old Password");
		}
		
		
		
		return res;
	}
	
	@Override
	public UserBean updateUserDetails(UserBean oldUserBean) {
		
		UserBean currentUserBean = getUserBean(oldUserBean.getId());
	
		if(!Util.isNull(currentUserBean)){			
			oldUserBean = updateUserDetails(currentUserBean,oldUserBean);
			oldUserBean = createUpdateUserBean(oldUserBean);

		}else{
			Util.throwPrimeException("Something Went Wrong");
		}
		
		
		return oldUserBean;
	}

	private UserBean updateUserDetails(UserBean oldUserBean,
			UserBean currentUserBean) {
		
		if(!Util.isNull(currentUserBean.getAddress())){
			oldUserBean.setAddress(currentUserBean.getAddress());
		}
		
		if(!Util.isNull(currentUserBean.getFirstName())){
			oldUserBean.setFirstName(currentUserBean.getFirstName());
		}
		
		if(!Util.isNull(currentUserBean.getIs_deleted())){
			oldUserBean.setIs_deleted(currentUserBean.getIs_deleted());
		}
		
		if(!Util.isNull(currentUserBean.getLastName())){
			oldUserBean.setLastName(currentUserBean.getLastName());
		}
		
		if(!Util.isNull(currentUserBean.getMobileNumber())){
			oldUserBean.setMobileNumber(currentUserBean.getMobileNumber());
		}
		
		if(!Util.isNull(currentUserBean.getPhotoPath())){
			oldUserBean.setPhotoPath(currentUserBean.getPhotoPath());
		}
		
		if(!Util.isNull(currentUserBean.getPinCode())){
			oldUserBean.setPinCode(currentUserBean.getPinCode());
		}
		
		if(!Util.isNull(currentUserBean.getRoleId())){
			oldUserBean.setRoleId(currentUserBean.getRoleId());
		}
		
		if(!Util.isNull(currentUserBean.getSecondaryContact())){
			oldUserBean.setSecondaryContact(currentUserBean.getSecondaryContact());
		}
		
		if(!Util.isNull(currentUserBean.getGender())){
			oldUserBean.setGender(currentUserBean.getGender());
		}
		
		if(!Util.isNull(currentUserBean.getMartialStatus())){
			oldUserBean.setMartialStatus(currentUserBean.getMartialStatus());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentAddress1())){
			oldUserBean.setPermanentAddress1(currentUserBean.getPermanentAddress1());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentAddress2())){
			oldUserBean.setPermanentAddress2(currentUserBean.getPermanentAddress2());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentCity())){
			oldUserBean.setPermanentCity(currentUserBean.getPermanentCity());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentLandmark())){
			oldUserBean.setPermanentLandmark(currentUserBean.getPermanentLandmark());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentPincode())){
			oldUserBean.setPermanentPincode(currentUserBean.getPermanentPincode());
		}
		
		if(!Util.isNull(currentUserBean.getPermanentState())){
			oldUserBean.setPermanentState(currentUserBean.getPermanentState());
		}
		
		if(!Util.isNull(currentUserBean.getBillingAddress1())){
			oldUserBean.setBillingAddress1(currentUserBean.getBillingAddress1());
		}
		
		if(!Util.isNull(currentUserBean.getBillingAddress2())){
			oldUserBean.setBillingAddress2(currentUserBean.getBillingAddress2());
		}
		
		if(!Util.isNull(currentUserBean.getBillingCity())){
			oldUserBean.setBillingCity(currentUserBean.getBillingCity());
		}
		
		if(!Util.isNull(currentUserBean.getBillingLandmark())){
			oldUserBean.setBillingLandmark(currentUserBean.getBillingLandmark());
		}
		
		if(!Util.isNull(currentUserBean.getBillingPincode())){
			oldUserBean.setBillingPincode(currentUserBean.getBillingPincode());
		}
		
		if(!Util.isNull(currentUserBean.getBillingState())){
			oldUserBean.setBillingState(currentUserBean.getBillingState());
		}
		
		if(!Util.isNull(currentUserBean.getBillingAddressReplica())){
			oldUserBean.setBillingAddressReplica(currentUserBean.getBillingAddressReplica());
		}
		
		oldUserBean.setUpdatedAt(Util.getCurrentTimeStamp());
		oldUserBean.setUpdatedBy(Util.getUserId(httpRequest));
		
		return oldUserBean;
	}
	
	
	
	

}

package com.ysyt.to.request;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;

public class SignupRequest {
	
	private LoginCredentials loginDetails;
	private UserBean userDetails;

	public LoginCredentials getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LoginCredentials loginDetails) {
		this.loginDetails = loginDetails;
	}

	public UserBean getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserBean userDetails) {
		this.userDetails = userDetails;
	}

}

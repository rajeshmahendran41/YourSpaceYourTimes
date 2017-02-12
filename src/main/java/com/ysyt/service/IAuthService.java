package com.ysyt.service;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

public interface IAuthService {

	SignupResponse setSignupResponse(SignupRequest request);

	UserBean createUpdateUserBean(UserBean userBean);

	void createUpdateLoginDetails(LoginCredentials loginCredentials);

}

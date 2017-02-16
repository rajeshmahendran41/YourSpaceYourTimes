package com.ysyt.service;

import java.math.BigInteger;

import com.ysyt.bean.LoginCredentials;
import com.ysyt.bean.UserBean;
import com.ysyt.to.request.LoginRequest;
import com.ysyt.to.request.PasswordRequest;
import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.AuthResponse;

public interface IAuthService {

	AuthResponse setSignupResponse(SignupRequest request);

	UserBean createUpdateUserBean(UserBean userBean);

	void createUpdateLoginDetails(LoginCredentials loginCredentials);

	AuthResponse loginAction(LoginRequest loginRequest);

	UserBean getUserBean(BigInteger userId);

	AuthResponse changePassword(PasswordRequest passwordRequest);

}

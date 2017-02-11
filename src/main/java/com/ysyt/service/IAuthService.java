package com.ysyt.service;

import com.ysyt.to.request.SignupRequest;
import com.ysyt.to.response.SignupResponse;

public interface IAuthService {

	SignupResponse setSignupResponse(SignupRequest request);

}

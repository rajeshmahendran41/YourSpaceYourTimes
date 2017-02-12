package com.ysyt.to.response;

import com.response.CommonResponse;
import com.ysyt.bean.UserBean;

public class AuthResponse extends CommonResponse{
	
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}

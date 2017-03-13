package com.ysyt.to.response;

import com.response.CommonResponse;
import com.ysyt.bean.Roles;
import com.ysyt.bean.UserBean;

public class RoleResponse extends CommonResponse{
	
	private Roles roles;

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	
}

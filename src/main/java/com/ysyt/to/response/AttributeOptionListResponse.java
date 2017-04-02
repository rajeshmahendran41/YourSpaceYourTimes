package com.ysyt.to.response;

import java.util.List;

import com.response.CommonResponse;
import com.ysyt.bean.AccomodationSubTypes;
import com.ysyt.bean.AccomodationTypes;
import com.ysyt.bean.AttributeOptions;


public class AttributeOptionListResponse extends CommonResponse {

	private List<AttributeOptions> attributeOptionList;

	public List<AttributeOptions> getAttributeOptionList() {
		return attributeOptionList;
	}

	public void setAttributeOptionList(List<AttributeOptions> attributeOptionList) {
		this.attributeOptionList = attributeOptionList;
	}

	

	
	
}

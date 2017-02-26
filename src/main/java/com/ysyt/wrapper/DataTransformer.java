package com.ysyt.wrapper;

import java.util.List;
import java.util.Map;

import com.ysyt.bean.Accomodations;
import com.ysyt.to.response.AccomodationListResponse;

public class DataTransformer {

	
	@SuppressWarnings("unchecked")
	public static AccomodationListResponse transformListResult(Map<String,Object> response){
		
		AccomodationListResponse res = new AccomodationListResponse();
		res.setAccomodationList((List<Accomodations>) response.get("list"));
		res.setCount( (Long) response.get("count"));

		
		return res;
	}
	

}

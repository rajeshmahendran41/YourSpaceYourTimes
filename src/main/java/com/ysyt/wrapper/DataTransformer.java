package com.ysyt.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Util.Util;
import com.horizontals.filter.wrapper.EntityWrapper;
import com.horizontals.filter.wrapper.FilterObject;
import com.horizontals.filter.wrapper.FilterWrapper;
import com.ysyt.bean.Accomodations;
import com.ysyt.to.request.AccomodationListRequest;
import com.ysyt.to.response.AccomodationListResponse;

public class DataTransformer {

	
	@SuppressWarnings("unchecked")
	public static AccomodationListResponse transformListResult(Map<String,Object> response){
		
		AccomodationListResponse res = new AccomodationListResponse();
		res.setAccomodationList((List<Accomodations>) response.get("list"));
		res.setCount( (Long) response.get("count"));

		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public static AccomodationListRequest convertFilter(AccomodationListRequest request){
		
		AccomodationListRequest res = request;
		
		FilterWrapper filter =  request.getFilterList();
		if(!Util.isNullList(filter.getFilter())){
			
			for(FilterObject filterObject : filter.getFilter()){
				
				switch (filterObject.getFilterName().toLowerCase()){
				
					case "spaceTypes":
						
						 res.setTypeIds(convertLongList(filterObject.getFilterValues()));
					 break;
					
					case "priceRange":
					
						if(!Util.isNull(filterObject.getFilterValues().get(0))){
							res.setMaxCost(filterObject.getFilterValues().get(0).getSeleectedMax());
							res.setMincost(filterObject.getFilterValues().get(0).getSelectedMin());
						}
					break;
					
				default:
				break;
				
				}
				
			}
						
		}

		
		return res;
	}
	
	public static List<Long> convertLongList(List<EntityWrapper> filterValues){
		
		List<Long> convertedValues = new ArrayList<>();
		if(!Util.isNullList(filterValues)){
			for(EntityWrapper entityWrapper :  filterValues){
				convertedValues.add(entityWrapper.getLongId());
			}
		}
		return convertedValues;
		
	}
	

}

package com.horizontals.filter.DataTransformer;

import java.util.List;

import com.horizontals.filter.wrapper.EntityWrapper;
import com.horizontals.filter.wrapper.FilterObject;

public class FilterDataTransformer {
	
	public static FilterObject convertFilterData(String displayName, String filterName , String filterType , List<EntityWrapper> filterValues ){
		
		FilterObject filter = new FilterObject();
		filter.setDisplayName(displayName);
		filter.setFilterName(filterName);
		filter.setFilterType(filterType);
		filter.setFilterValues(filterValues);
		return filter;
		
	}

}

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
	
public static FilterObject convertScrollFilterData(String displayName, String filterName , String filterType , Double minValue , Double maxValue , Double minSelected, Double maxSelected){
		
		FilterObject filter = new FilterObject();
		
		filter.setDisplayName(displayName);
		filter.setFilterName(filterName);
		filter.setFilterType(filterType);
		filter.setMinValue(minValue);
		filter.setMaxValue(maxValue);
		filter.setSelectedMin(minSelected);
		filter.setSeleectedMax(maxSelected);
		return filter;
		
	}

}

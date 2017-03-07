package com.ysyt.to.response;

import com.horizontals.filter.wrapper.FilterWrapper;
import com.response.CommonResponse;

public class FilterResponse extends CommonResponse {
	
	private FilterWrapper filterData;

	public FilterWrapper getFilterData() {
		return filterData;
	}

	public void setFilterData(FilterWrapper filterData) {
		this.filterData = filterData;
	}

}

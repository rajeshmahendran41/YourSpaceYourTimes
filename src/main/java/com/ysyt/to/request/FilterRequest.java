package com.ysyt.to.request;

import java.util.List;

import com.horizontals.filter.wrapper.FilterWrapper;


public class FilterRequest extends FilterWrapper {
	
	private String tabName;
	private Integer typeId;
	private List<Long> locationIds;
	
	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<Long> getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}

}

package com.horizontals.filter.wrapper;

import java.util.List;

import com.ysyt.bean.AccomodationsDetails;

public class FilterObject {

	private String filterName;
	private String description;
	private String filterType;
	private String displayName;
	private List<EntityWrapper> filterValues;
	private String selectedValues="";
	private Boolean isFilterApplied=false;

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilterType() {
		return filterType;
	}


	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<EntityWrapper> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<EntityWrapper> filterValues) {
		this.filterValues = filterValues;
	}

	public String getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(String selectedValues) {
		this.selectedValues = selectedValues;
	}

	public Boolean getIsFilterApplied() {
		return isFilterApplied;
	}

	public void setIsFilterApplied(Boolean isFilterApplied) {
		this.isFilterApplied = isFilterApplied;
	}



	
	

}

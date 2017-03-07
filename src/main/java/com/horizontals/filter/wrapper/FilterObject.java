package com.horizontals.filter.wrapper;

import java.util.List;

import com.ysyt.bean.AccomodationsDetails;

public class FilterObject {

	private String filterName;
	private String description;
	private String filterType;
	private String displayName;
	private List<EntityWrapper> filterValues;
	private EntityWrapper selectedValues;
	private Double minValue;
	private Double maxValue;
	private Double selectedMin;
	private Double seleectedMax;

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

	public EntityWrapper getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(EntityWrapper selectedValues) {
		this.selectedValues = selectedValues;
	}

	

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getSelectedMin() {
		return selectedMin;
	}

	public void setSelectedMin(Double selectedMin) {
		this.selectedMin = selectedMin;
	}

	public Double getSeleectedMax() {
		return seleectedMax;
	}

	public void setSeleectedMax(Double seleectedMax) {
		this.seleectedMax = seleectedMax;
	}
	

}

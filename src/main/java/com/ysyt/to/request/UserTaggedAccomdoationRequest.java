package com.ysyt.to.request;

import java.util.List;

import com.horizontals.filter.wrapper.FilterWrapper;


public class UserTaggedAccomdoationRequest {
	
	private Integer limit;
	private Integer offset;
	private String projections;
	private List<Long> typeIds;
	private Double mincost;
	private Double maxCost;
	private List<Long> locationIds;
	private FilterWrapper filterList;
	private String sortBy;
	private String sortType;

	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public String getProjections() {
		return projections;
	}
	public void setProjections(String projections) {
		this.projections = projections;
	}
	public List<Long> getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(List<Long> typeIds) {
		this.typeIds = typeIds;
	}
	public Double getMincost() {
		return mincost;
	}
	public void setMincost(Double mincost) {
		this.mincost = mincost;
	}
	public Double getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(Double maxCost) {
		this.maxCost = maxCost;
	}
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public FilterWrapper getFilterList() {
		return filterList;
	}
	public void setFilterList(FilterWrapper filterList) {
		this.filterList = filterList;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	
}

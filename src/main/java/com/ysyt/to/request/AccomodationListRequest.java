package com.ysyt.to.request;

import java.math.BigInteger;

import com.ysyt.bean.Accomodations;


public class AccomodationListRequest {
	
	private Integer limit;
	private Integer offset;
	private String projections;

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

	
}

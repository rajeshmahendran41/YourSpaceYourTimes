package com.ysyt.to.request;

public class AmenitiesMappingCreateRequest {

	private Long amenitiesParentId;
	private Integer limit;
	private Integer offset;
	private String query;

	public Long getAmenitiesParentId() {
		return amenitiesParentId;
	}

	public void setAmenitiesParentId(Long amenitiesParentId) {
		this.amenitiesParentId = amenitiesParentId;
	}

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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}

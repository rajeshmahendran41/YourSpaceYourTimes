package com.horizontals.filter.wrapper;


public class EntityWrapper {

	public EntityWrapper(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	private Integer id;
	private String title;
	private Long longId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getLongId() {
		return longId;
	}
	public void setLongId(Long longId) {
		this.longId = longId;
	}

}

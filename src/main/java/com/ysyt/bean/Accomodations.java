package com.ysyt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysyt.wrapper.AccomodationsWrapper;

@Entity
@Table(name = "accomodations", schema = CommonConstants.SCHEMA)
public class Accomodations extends AccomodationsWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="tilte")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="cost")
	private Integer  cost;
	
	@Column(name="address")
	private String address;
	
	@Column(name="location_id")
	private Long locationId;
	
	
	@Column(name="latitude")
	private BigDecimal latitude;
	
	@Column(name="longitude")
	private BigDecimal longitude;
	
	@Column(name="total_room_count",nullable = false ,insertable=false, columnDefinition = "boolean default 0")
	private Integer totalRoomCount;
	
	@Column(name="current_room_count",nullable = false ,insertable=false, columnDefinition = "integer default 0")
	private Integer currentRoomCount;
	
	@Column(name="type_id")
	private Long typeId;
	
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isDeleted;
		
		
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Integer getTotalRoomCount() {
		return totalRoomCount;
	}

	public void setTotalRoomCount(Integer totalRoomCount) {
		this.totalRoomCount = totalRoomCount;
	}

	public Integer getCurrentRoomCount() {
		return currentRoomCount;
	}

	public void setCurrentRoomCount(Integer currentRoomCount) {
		this.currentRoomCount = currentRoomCount;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	
	

	
	
}
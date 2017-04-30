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
import org.hibernate.annotations.Where;

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
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="room_cost")
	private Double  roomCost;
	
	@Column(name="food_cost")
	private Double  foodCost;
	
	@Column(name="security_deposit")
	private Double  securityDeposit;
	
	@Column(name="additional_cost")
	private Double  additionalCost;
	
	@Column(name="address")
	private String address;
	
	@Column(name="location_id")
	private Long locationId;
	
	
	@Column(name="latitute")
	private BigDecimal latitude;
	
	@Column(name="longitude")
	private BigDecimal longitude;
	
	@Column(name="total_room_count",nullable = false , columnDefinition = "boolean default 0")
	private Integer totalRoomCount;
	
	@Column(name="current_room_count",nullable = false , columnDefinition = "integer default 0")
	private Integer currentRoomCount;
	
	@Column(name="type_id")
	private Long typeId;
	
	@Column(name = "cover_photo")
	private Long coverPhoto;
	
	
	@Column(name = "is_food_mandatory" ,nullable = false , columnDefinition = "boolean default false")
	private Boolean isFoodMandatory;
	
	@Column(name="gender_availability_id")
	private Long genderAvailabilityId;
	
	
	@Column(name="sub_type_id")
	private Long subTypeId;
	
	
	@Column(name = "is_deleted" ,nullable = false , insertable=false,columnDefinition = "boolean default false")
	private Boolean isDeleted;
		
		
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	

	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AccomodationTypes.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private AccomodationTypes accomodationType;	
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Uploads.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_photo", referencedColumnName = "id",insertable=false ,updatable=false)
	private Uploads coverPhotoBean;	
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AccomodationSubTypes.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_type_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private AccomodationSubTypes subType;	
	
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AccomodationGenders.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_availability_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private AccomodationGenders gender;	



	public Boolean getIsFoodMandatory() {
		return isFoodMandatory;
	}

	public void setIsFoodMandatory(Boolean isFoodMandatory) {
		this.isFoodMandatory = isFoodMandatory;
	}

	public Long getGenderAvailabilityId() {
		return genderAvailabilityId;
	}

	public void setGenderAvailabilityId(Long genderAvailabilityId) {
		this.genderAvailabilityId = genderAvailabilityId;
	}

	public Long getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(Long subTypeId) {
		this.subTypeId = subTypeId;
	}

	public AccomodationSubTypes getSubType() {
		return subType;
	}

	public void setSubType(AccomodationSubTypes subType) {
		this.subType = subType;
	}

	public AccomodationGenders getGender() {
		return gender;
	}

	public void setGender(AccomodationGenders gender) {
		this.gender = gender;
	}


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



	public AccomodationTypes getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationTypes accomodationType) {
		this.accomodationType = accomodationType;
	}

	public Long getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(Long coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public Uploads getCoverPhotoBean() {
		return coverPhotoBean;
	}

	public void setCoverPhotoBean(Uploads coverPhotoBean) {
		this.coverPhotoBean = coverPhotoBean;
	}

	public Double getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(Double roomCost) {
		this.roomCost = roomCost;
	}

	public Double getFoodCost() {
		return foodCost;
	}

	public void setFoodCost(Double foodCost) {
		this.foodCost = foodCost;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public Double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(Double additionalCost) {
		this.additionalCost = additionalCost;
	}

	
	

	
	
}
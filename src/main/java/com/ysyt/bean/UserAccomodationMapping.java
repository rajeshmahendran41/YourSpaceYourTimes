package com.ysyt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.constants.CommonConstants;
import com.ysyt.wrapper.AccomodationsWrapper;

@Entity
@Table(name = "user_accomodations_mapping", schema = CommonConstants.SCHEMA)
public class UserAccomodationMapping  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="accomodation_id")
	private Long accomodationId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="order_id")
	private String  orderId;
	
	@Column(name="join_date")
	private Timestamp  joinDate;
	
	@Column(name="vacate_date")
	private Timestamp vacateDate;
	
		
	@Column(name="room_cost")
	private Double roomCost;
	
	@Column(name="food_cost")
	private Double foodCost;
	
	@Column(name="additional_cost")
	private Double additionalCost;
	
	@Column(name="total_cost")
	private Double totalCost;
	
	@Column(name="security_deposit")
	private Double securityDeposit;
		
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isDeleted;
		
	@Column(name = "is_food_selected" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isFoodSelected;
		
		
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Accomodations.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "accomodation_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private Accomodations  accomodationDetails;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccomodationId() {
		return accomodationId;
	}

	public void setAccomodationId(Long accomodationId) {
		this.accomodationId = accomodationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public Timestamp getVacateDate() {
		return vacateDate;
	}

	public void setVacateDate(Timestamp vacateDate) {
		this.vacateDate = vacateDate;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public Double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(Double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public Boolean getIsFoodSelected() {
		return isFoodSelected;
	}

	public void setIsFoodSelected(Boolean isFoodSelected) {
		this.isFoodSelected = isFoodSelected;
	}

	public Accomodations getAccomodationDetails() {
		return accomodationDetails;
	}

	public void setAccomodationDetails(Accomodations accomodationDetails) {
		this.accomodationDetails = accomodationDetails;
	}

	
}
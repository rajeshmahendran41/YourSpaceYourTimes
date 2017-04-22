package com.ysyt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.constants.CommonConstants;
import com.ysyt.wrapper.AccomodationsWrapper;

@Entity
@Table(name = "transactions", schema = CommonConstants.SCHEMA)
public class Transactions  implements Serializable {
	
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
	private String orderId;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="description")
	private String description;
	
	@Column(name="currency")
	private String currencty;
	
	@Column(name="status")
	private String status;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="room_cost")
	private Double roomCost;
	
	@Column(name="food_cost")
	private Double foodCost;
	
	@Column(name="additional_cost")
	private Double additionalCost;
	
	@Column(name="total_room_cost")
	private Double totalRoomCost;
	
	
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="redirect_url")
	private String redirectUrl;
	
	
	@Column(name="payment_url")
	private String paymentUrl;
	
	@Column(name="deposit_type")
	private String depositType;
	
	@Column(name="gson_response")
	private String gsonResponse;
	
	@Column(name = "join_date" )
	private Timestamp joinDate;
		
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrencty() {
		return currencty;
	}

	public void setCurrencty(String currencty) {
		this.currencty = currencty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getGsonResponse() {
		return gsonResponse;
	}

	public void setGsonResponse(String gsonResponse) {
		this.gsonResponse = gsonResponse;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Double getTotalRoomCost() {
		return totalRoomCost;
	}

	public void setTotalRoomCost(Double totalRoomCost) {
		this.totalRoomCost = totalRoomCost;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	
	
}
package com.ysyt.bean;

import java.io.Serializable;
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

@Entity
@Table(name = "accomodations_details", schema = CommonConstants.SCHEMA)
public class AccomodationsDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="attribute_id")
	private Long attributeId;
	
	@Column(name="parent_id")
	private Long parentId;
	
	@Column(name="value")
	private String  value;
	
	@Column(name="source_id")
	private Long  sourceId;
	
	@Column(name="source_type")
	private String sourceType;
	
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="is_amenities",nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isAmenities;
	
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
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AttributesMaster.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private AttributesMaster attributes;	

	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = AttributesMaster.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private AttributesMaster parentAttributes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Boolean getIsAmenities() {
		return isAmenities;
	}

	public void setIsAmenities(Boolean isAmenities) {
		this.isAmenities = isAmenities;
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
	
	
	
}
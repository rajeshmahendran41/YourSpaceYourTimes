package com.ysyt.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.constants.CommonConstants;
import com.ysyt.wrapper.AmenitiesWrapper;

@Entity
@Table(name = "attribute_options", schema = CommonConstants.SCHEMA)
public class AttributeOptions  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="attribute_id")
	private Long attributeId;
	
	@Column(name="attribute_values")
	private String attributeValues;
	
	@Column(name="entity_id")
	private Long entityId;
	
	@Column(name="entity_type")
	private String entityType;
	
	@Column(name="is_deleted",nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isDeleted;

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

	public String getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(String attributeValues) {
		this.attributeValues = attributeValues;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
}
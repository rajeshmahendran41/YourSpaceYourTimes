package com.ysyt.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;

import com.Util.Util;
import com.constants.CommonConstants;

@Entity
@Table(name = "accomodation_subtypes", schema = CommonConstants.SCHEMA)
public class AccomodationSubTypes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	
	@Column(name = "sharable_count" )
	private Integer sharableCount ;
	
    @Column(name = "sharable_type" )
	private String sharableType ;
    
    @Column(name = "accomodation_type_id" )
   	private Long accomodationTypeId ;
	
	
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isDeleted ;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getSharableCount() {
		return sharableCount;
	}


	public void setSharableCount(Integer sharableCount) {
		this.sharableCount = sharableCount;
	}


	public String getSharableType() {
		return sharableType;
	}


	public void setSharableType(String sharableType) {
		this.sharableType = sharableType;
	}


	public Long getAccomodationTypeId() {
		return accomodationTypeId;
	}


	public void setAccomodationTypeId(Long accomodationTypeId) {
		this.accomodationTypeId = accomodationTypeId;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
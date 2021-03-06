 package com.ysyt.bean;

import java.io.Serializable;
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

import com.constants.CommonConstants;
import com.ysyt.wrapper.AmenitiesWrapper;

@Entity
@Table(name = "attribute_master", schema = CommonConstants.SCHEMA)
public class AttributesMaster extends AmenitiesWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_amenities")
	private Boolean isAmenities;
	
	@Column(name="is_deleted",nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean isDeleted;
	
	@Column(name="parent_id")
	private Long parentId;
	
	@Column(name="logo_path")
	private Long logoPath;
	
	
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Uploads.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "logo_path", referencedColumnName = "id",insertable=false ,updatable=false)
	private Uploads logoDetails;
	

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(Long logoPath) {
		this.logoPath = logoPath;
	}

	public Uploads getLogoDetails() {
		return logoDetails;
	}

	public void setLogoDetails(Uploads logoDetails) {
		this.logoDetails = logoDetails;
	}


	
	
}
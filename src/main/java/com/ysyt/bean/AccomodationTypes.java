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
@Table(name = "accomodation_types", schema = CommonConstants.SCHEMA)
public class AccomodationTypes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	
	@Column(name = "title" )
	private String title ;
	
	
	
	@Column(name = "is_default" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean is_default ;



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



	public Boolean getIs_default() {
		return is_default;
	}



	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}

	
	

}
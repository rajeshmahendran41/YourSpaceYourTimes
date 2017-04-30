package com.ysyt.bean;

import java.io.Serializable;
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

import com.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user_login", schema = CommonConstants.SCHEMA)
@Filter(name = "loginCredentialsFilter" )
@FilterDefs({
    @FilterDef(name = "loginCredentialsFilter", defaultCondition = " is_deleted = 'false' ")})
public class LoginCredentials implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Id
	@Column(name = "user_id")
	@NotNull
	private Long userId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password")
	@NotNull
	private String password;
	
	@Column(name = "email")
	private String email;
	
    @JsonIgnore
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean is_deleted ;
	
    @JsonIgnore
	@Column(name = "created_at")
	private Timestamp createdAt;
	
    @JsonIgnore
	@Column(name = "created_by")
	private Long createdBy;
	
    @JsonIgnore
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
    @JsonIgnore
	@Column(name = "updated_by")
	private Long updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
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
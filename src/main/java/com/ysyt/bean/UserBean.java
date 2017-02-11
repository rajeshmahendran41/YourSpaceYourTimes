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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Formula;

import com.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users", schema = CommonConstants.SCHEMA)
@Filter(name = "usersFilter" )
@FilterDefs({
    @FilterDef(name = "usersFilter", defaultCondition = " is_deleted = 'false' ")})
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "first_name")
	@Pattern (regexp = "^.*[A-Z0-9a-z].*$" , message = "First should contain atleast one alphanumberic")
    @Size(min = 5, max = 5000, message = "First Name should not be less than 5 characters and greater than 50 characters") 
	@NotNull
	private String firstName;
		
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "mobile_number")
	private BigInteger mobileNumber;
	
	@Column(name = "secondary_contact")
	private BigInteger secondaryContact;
	
	@Column(name = "photo_path")
	private String photoPath;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private BigInteger pinCode;
	
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean is_deleted;
	
	@Column(name = "mail_subscripton" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean mailSubscription;
	
	@JsonIgnore
	@Formula("( concat(first_name,' ',last_name) )")
	private String userFullname;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "created_by")
	private BigInteger createdBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "updated_by")
	private BigInteger updatedBy;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BigInteger getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(BigInteger secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getPinCode() {
		return pinCode;
	}

	public void setPinCode(BigInteger pinCode) {
		this.pinCode = pinCode;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Boolean getMailSubscription() {
		return mailSubscription;
	}

	public void setMailSubscription(Boolean mailSubscription) {
		this.mailSubscription = mailSubscription;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public BigInteger getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigInteger updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
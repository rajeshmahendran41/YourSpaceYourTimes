package com.ysyt.bean;

import java.io.Serializable;
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
	private Long id;
	
	@Column(name = "first_name")
	@Pattern (regexp = "^.*[A-Z0-9a-z].*$" , message = "First should contain atleast one alphanumberic")
    @Size(min = 5, max = 5000, message = "First Name should not be less than 5 characters and greater than 50 characters") 
	@NotNull
	private String firstName;
		
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "mobile_number")
	private Long mobileNumber;
	
	@Column(name = "secondary_contact")
	private Long secondaryContact;
	
	@Column(name = "photo_path")
	private String photoPath;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private Long pinCode;
	
	@Column(name = "is_deleted" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean is_deleted;
	
	@Column(name = "mail_subscripton" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean mailSubscription;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "marital_status")
	private String martialStatus;
	
	@Column(name = "permanent_address1")
	private String permanentAddress1;
	
	@Column(name = "permanent_address2")
	private String permanentAddress2;
	
	@Column(name = "permanent_landmark")
	private String permanentLandmark;
	
	@Column(name = "permanent_state")
	private String permanentState;
	
	@Column(name = "permanent_city")
	private String permanentCity;
	
	@Column(name = "permanent_pincode")
	private String permanentPincode;
	
	@Column(name = "billing_address1")
	private String billingAddress1;
	
	@Column(name = "billing_address2")
	private String billingAddress2;
	
	@Column(name = "billing_landmark")
	private String billingLandmark;
	
	@Column(name = "billing_state")
	private String billingState;
	
	@Column(name = "billing_city")
	private String billingCity;
	
	@Column(name = "billing_pincode")
	private String billingPincode;
	
	@Column(name = "permanent_billing_replica" ,nullable = false ,insertable=false, columnDefinition = "boolean default false")
	private Boolean billingAddressReplica;
	
	@JsonIgnore
	@Formula("( concat(first_name,' ',last_name) )")
	private String userFullname;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	

	@OneToOne(fetch = FetchType.EAGER,targetEntity = Roles.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private Roles roles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(Long secondaryContact) {
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

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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


	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getPermanentAddress1() {
		return permanentAddress1;
	}

	public void setPermanentAddress1(String permanentAddress1) {
		this.permanentAddress1 = permanentAddress1;
	}

	public String getPermanentAddress2() {
		return permanentAddress2;
	}

	public void setPermanentAddress2(String permanentAddress2) {
		this.permanentAddress2 = permanentAddress2;
	}

	public String getPermanentLandmark() {
		return permanentLandmark;
	}

	public void setPermanentLandmark(String permanentLandmark) {
		this.permanentLandmark = permanentLandmark;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingLandmark() {
		return billingLandmark;
	}

	public void setBillingLandmark(String billingLandmark) {
		this.billingLandmark = billingLandmark;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingPincode() {
		return billingPincode;
	}

	public void setBillingPincode(String billingPincode) {
		this.billingPincode = billingPincode;
	}

	public Boolean getBillingAddressReplica() {
		return billingAddressReplica;
	}

	public void setBillingAddressReplica(Boolean billingAddressReplica) {
		this.billingAddressReplica = billingAddressReplica;
	}

	
	
}
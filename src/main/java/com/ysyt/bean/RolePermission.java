package com.ysyt.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.constants.CommonConstants;

@Entity
@Table(name = "roles_permission", schema = CommonConstants.SCHEMA)
public class RolePermission implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="permission_name")
	private String permissionName;
	
	@Column(name="has_permission")
	private Boolean hasPermission;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Boolean getHasPermission() {
		return hasPermission;
	}

	public void setHasPermission(Boolean hasPermission) {
		this.hasPermission = hasPermission;
	}
	
	
}
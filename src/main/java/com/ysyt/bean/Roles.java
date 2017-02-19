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
import javax.persistence.Table;

import com.constants.CommonConstants;

@Entity
@Table(name = "roles", schema = CommonConstants.SCHEMA)
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	

	@OneToMany(fetch = FetchType.EAGER,targetEntity = RolePermission.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id",insertable=false ,updatable=false)
	private List<RolePermission> rolesPermission;

	

	public List<RolePermission> getRolesPermission() {
		return rolesPermission;
	}

	public void setRolesPermission(List<RolePermission> rolesPermission) {
		this.rolesPermission = rolesPermission;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
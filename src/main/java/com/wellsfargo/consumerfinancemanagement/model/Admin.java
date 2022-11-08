package com.wellsfargo.consumerfinancemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long AdminId;
	
	
	@Column(unique=true)
	private String adminuName;
	
	private String password;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAdminuName() {
		return adminuName;
	}

	public void setAdminuName(String adminuName) {
		this.adminuName = adminuName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
package com.wellsfargo.consumerfinancemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	private static final boolean True = false;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	private String name;

	@Column(unique=True)
	private String phoneNo;
	
	@Column(unique=True)
	private String emailId;
	
	@Column(unique=True)
	private String userName;
	
	private String password;
	
	private String address;
	
	private String cardType;
	
	private String bank;
	
	private String ifscCode;
	
	private String accountNo;
	
	private String docUpload;

	private String actvnStatus;
	
	

	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	/**
	 * @return the docUpload
	 */
	public String getDocUpload() {
		return docUpload;
	}

	/**
	 * @param docUpload the docUpload to set
	 */
	public void setDocUpload(String docUpload) {
		this.docUpload = docUpload;
	}

	/**
	 * @return the actvnStatus
	 */
	public String getActvnStatus() {
		return actvnStatus;
	}

	/**
	 * @param actvnStatus the actvnStatus to set
	 */
	public void setActvnStatus(String actvnStatus) {
		this.actvnStatus = actvnStatus;
	}
	
}

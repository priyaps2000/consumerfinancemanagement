package com.wellsfargo.consumerfinancemanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int saleId;

	private String userName;

	private String productId;
	
	private String productName;
	
	private int tenurePeriod;
	
	private int totalAmount;
	
	private int amountpaid;
	
	private Date purchaseDate;

	public Sale() {
		super();
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public String getUserName() {
		return userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getTenurePeriod() {
		return tenurePeriod;
	}

	public void setTenurePeriod(int tenurePeriod) {
		this.tenurePeriod = tenurePeriod;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getAmountpaid() {
		return amountpaid;
	}

	public void setAmountpaid(int amountpaid) {
		this.amountpaid = amountpaid;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}

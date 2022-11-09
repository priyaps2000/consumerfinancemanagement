package com.wellsfargo.consumerfinancemanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="productID", unique=true)
	private Long ProductId;
	
	private String ProductName;
	
	private String ProductDetails;
	
	private String ProductURL;
	
	private int Cost;
	
	private int purchaseCount;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getProductId() {
		return ProductId;
	}

	public void setProductId(Long productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductDetails() {
		return ProductDetails;
	}

	public void setProductDetails(String productDetails) {
		ProductDetails = productDetails;
	}

	public String getProductURL() {
		return ProductURL;
	}

	public void setProductURL(String productURL) {
		ProductURL = productURL;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}
	
	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	
}

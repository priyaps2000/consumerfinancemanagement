package com.wellsfargo.consumerfinancemanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Card")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	private Long cardNo;
	
	private String validity;
	
	private String cardType;
	
	private int cardLimit;
	
	private int totalCredit;
	
	private int creditUsed;
	
	@Column(unique=true)
	private String userName;

	public Card() {
		super();
	}

	/**
	 * @return the cardNo
	 */
	public Long getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the validity
	 */
	public String getValidity() {
		return validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(String validity) {
		this.validity = validity;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the limit
	 */
	public Integer getCardLimit() {
		return cardLimit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setCardLimit(Integer limit) {
		this.cardLimit = limit;
	}

	/**
	 * @return the totalCredit
	 */
	public Integer getTotalCredit() {
		return totalCredit;
	}

	/**
	 * @param totalCredit the totalCredit to set
	 */
	public void setTotalCredit(Integer totalCredit) {
		this.totalCredit = totalCredit;
	}

	/**
	 * @return the creditUsed
	 */
	public Integer getCreditUsed() {
		return creditUsed;
	}

	/**
	 * @param creditUsed the creditUsed to set
	 */
	public void setCreditUsed(Integer creditUsed) {
		this.creditUsed = creditUsed;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
package com.wellsfargo.consumerfinancemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	private String name;

	@Column(unique=true)
	private String phoneNo;
	
	@Column(unique=true)
	private String emailId;
	
	@Column(unique=true)
	private String userName;
	
	private String password;
	
	private String address;
	
	private String cardType;
	
	private String bank;
	
	private String ifscCode;
	
	private String accountNo;
	
	private String docUpload;

	private String actvnStatus;
	
	private String resetToken;

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
		return decrypt(password);
	}

	public void setPassword(String password) {
		this.password = encrypt(password);
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
	
	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	
	private static final String SECRET_KEY = "we_are_team_1";
	private static final String SALT = "demolishers";
	
	public static String encrypt(String strToEncrypt) {
	    try {
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	      SecretKey tmp = factory.generateSecret(spec);
	      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	      return Base64.getEncoder()
	          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
	    } catch (Exception e) {
	      System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	  }
	
	public static String decrypt(String strToDecrypt) {
	    try {
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	      SecretKey tmp = factory.generateSecret(spec);
	      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    } catch (Exception e) {
	      System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	  }
	
}

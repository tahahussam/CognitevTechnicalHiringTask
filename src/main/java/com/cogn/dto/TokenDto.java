package com.cogn.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenDto {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean isValid;
	private String firstName;
	private String phone;
	private String token;

	public TokenDto(boolean isValid, String firstName, String phone, String token) {
		super();
		this.isValid = isValid;
		this.firstName = firstName;
		this.setPhone(phone);
		this.token = token;

		logger.info("TokenDto created with valid status = " + this.isValid);
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenDto [isValid=" + isValid + ", firstName=" + firstName + ", phone=" + phone + ", token=" + token
				+ "]";
	}

}
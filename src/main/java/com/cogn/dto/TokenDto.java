package com.cogn.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenDto {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String status;
	private String firstName;
	private String phone;
	private String token;

	public TokenDto(String status, String firstName, String phone, String token) {
		super();
		this.setStatus(status);
		this.firstName = firstName;
		this.setPhone(phone);
		this.token = token;

		logger.info("TokenDto created with valid status = " + this.status);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenDto [status=" + status + ", firstName=" + firstName + ", phone=" + phone + ", token=" + token
				+ "]";
	}

}
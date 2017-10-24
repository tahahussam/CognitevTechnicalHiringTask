package com.cong.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenDto {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean isValid;
	private String firstName;
	private String lastName;
	private String token;

	public TokenDto(boolean isValid, String firstName, String lastName, String token) {
		super();
		this.isValid = isValid;
		this.firstName = firstName;
		this.lastName = lastName;
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
	 * @return the userName
	 */
	public String getUserName() {
		return firstName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.firstName = userName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return lastName;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.lastName = userId;
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
		return "TokenDto [isValid=" + isValid + ", userName=" + firstName + ", userId=" + lastName + ", token=" + token
				+ "]";
	}

}
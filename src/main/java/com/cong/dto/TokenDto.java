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
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
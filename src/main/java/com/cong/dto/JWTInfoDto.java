package com.cong.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTInfoDto {
	public boolean isValid;
	public boolean isExpired;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public JWTInfoDto(boolean isValid, boolean isExpired) {
		super();
		this.isValid = isValid;
		this.isExpired = isExpired;

		logger.info("JWTValidStatusDto Created with isValid = " + isValid);
	}
}
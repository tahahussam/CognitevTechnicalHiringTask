package com.cogn.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cogn.entity.User;

public class StatusDto {
	public boolean isValid;
	public User user;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public StatusDto(boolean isValid, User user) {
		super();
		this.isValid = isValid;
		this.user = user;

		logger.info("JWTValidStatusDto Created with isValid = " + isValid);
	}
}
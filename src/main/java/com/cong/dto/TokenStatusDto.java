package com.cong.dto;

public class TokenStatusDto {
	public String statusDiscription;
	public int statusCode;

	public TokenStatusDto(String statusDiscription, int statusCode) {
		super();
		this.statusDiscription = statusDiscription;
		this.statusCode = statusCode;
	}
}
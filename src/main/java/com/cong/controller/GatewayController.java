package com.cong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cong.dto.TokenDto;
import com.cong.dto.TokenStatusDto;
import com.cong.service.UserService;

@RestController
public class GatewayController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam(value = "first_name", required = true) String firstName,
			@RequestParam(value = "last_name", required = true) String lastName,
			@RequestParam(value = "country_code", required = true) String countryCode,
			@RequestParam(value = "phone_number", required = true) String phoneNumber,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "birthdate", required = true) String birthdate,
			@RequestParam(value = "avatar", required = true) MultipartFile avatar,
			@RequestParam(value = "email", required = false) String email) {

		logger.info("/addUser Called");

		return UserService.addUser(firstName, lastName, countryCode, phoneNumber, gender, birthdate, avatar, email);
	}

	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	public TokenDto getToken(@RequestHeader(value = "phone_number", required = true) String phoneNumber,
			@RequestHeader(value = "password", required = true) String password) {
		logger.info("/getToken Called");
		return UserService.generateToken(phoneNumber, password);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public TokenDto authenticate(@RequestHeader(value = "phone_number", required = true) String phoneNumber,
			@RequestHeader(value = "password", required = true) String password) {
		logger.info("/authenticate Called");
		return null;
		// return UserService.authenticate(phoneNumber, password);
	}

	@RequestMapping(value = "/notAuthorized", method = { RequestMethod.GET, RequestMethod.POST })
	public TokenStatusDto notAuthorized() {
		logger.info("/notAuthorized Called");
		return new TokenStatusDto("Not Authorized Token", HttpStatus.NOT_ACCEPTABLE.value());
	}

	@RequestMapping(value = "/expiredToken", method = { RequestMethod.GET, RequestMethod.POST })
	public TokenStatusDto expiredToken() {
		logger.info("/expiredToken Called");
		return new TokenStatusDto("Expired Token", HttpStatus.NOT_ACCEPTABLE.value());
	}
}
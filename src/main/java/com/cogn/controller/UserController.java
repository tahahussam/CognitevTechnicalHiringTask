package com.cogn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cogn.dto.TokenDto;
import com.cogn.service.UserService;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

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

		return userService.addUser(firstName, lastName, countryCode, phoneNumber, gender, birthdate, avatar, email);
	}

	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	public TokenDto getToken(@RequestHeader(value = "phone_number", required = true) String phoneNumber,
			@RequestHeader(value = "password", required = true) String password) {
		logger.info("/getToken Called");
		return userService.generateToken(phoneNumber, password);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> authenticate(
			@RequestHeader(value = "phone_number", required = true) String phoneNumber,
			@RequestHeader(value = "auth_token", required = true) String authToken) {
		logger.info("/authenticate Called");
		return userService.authenticate(phoneNumber, authToken);
	}
}
package com.cong.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cong.dto.TokenDto;

@Service
public class UserService {

	public static ResponseEntity<String> addUser(String firstName, String lastName, String countryCode,
			String phoneNumber, String gender, String birthdate, MultipartFile avatar, String email) {
		return new ResponseEntity<>("Okaaaay", HttpStatus.CREATED);
	}

	public static TokenDto generateToken(String phoneNumber, String password) {
		return new TokenDto(true, "test", "test", "test");
	}

}

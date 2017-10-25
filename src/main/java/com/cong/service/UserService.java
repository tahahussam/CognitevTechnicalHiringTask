package com.cong.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cong.dao.UserRepository;
import com.cong.dto.TokenDto;
import com.cong.util.ValidationUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepository userRepository;

	public static ResponseEntity<String> addUser(String firstName, String lastName, String countryCode,
			String phoneNumber, String gender, String birthdate, MultipartFile avatar, String email) {

		Map<String, List<Map<String, List<Map<String, String>>>>> errors = ValidationUtility
				.validateParameters(firstName, lastName, countryCode, phoneNumber, gender, birthdate, avatar, email);
		if (!errors.get("errors").isEmpty()) {
			try {
				return new ResponseEntity<>(new ObjectMapper().writeValueAsString(errors),
						HttpStatus.UNPROCESSABLE_ENTITY);
			} catch (Exception e) {
				return new ResponseEntity<>("Un expected error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>("ok", HttpStatus.CREATED);
	}

	public static TokenDto generateToken(String phoneNumber, String password) {
		if (!ValidationUtility.isValidPhoneNumber(phoneNumber) || !isExistsInDB(phoneNumber))
			return new TokenDto(false, null, null, null);

		return new TokenDto(true, "test", "test", "test");
	}

	public static ResponseEntity<String> authenticate(String phoneNumber, String authToken) {

		if (!ValidationUtility.isValidPhoneNumber(phoneNumber))
			return new ResponseEntity<>("Not valid phoneNumber", HttpStatus.BAD_REQUEST);

		if (!ValidationUtility.isValidToken(authToken) || !isAuthorized(phoneNumber, authToken))
			return new ResponseEntity<>("Not Authorized", HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	private static boolean isAuthorized(String phoneNumber, String authToken) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean isExistsInDB(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String jsonInString = mapper
				.writeValueAsString(ValidationUtility.validateParameters("", "", "", "", "", "", null, ""));
		System.out.println(jsonInString);

	}

}

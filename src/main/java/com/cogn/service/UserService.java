package com.cogn.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cogn.dao.UserRepository;
import com.cogn.dto.StatusDto;
import com.cogn.dto.TokenDto;
import com.cogn.entity.User;
import com.cogn.util.DateUtility;
import com.cogn.util.PasswordUtility;
import com.cogn.util.ValidationUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<String> addUser(String firstName, String lastName, String countryCode, String phoneNumber,
			String gender, String birthdate, MultipartFile avatar, String email) {

		Map<String, List<Map<String, List<Map<String, String>>>>> errors = ValidationUtility
				.validateParameters(firstName, lastName, countryCode, phoneNumber, gender, birthdate, avatar, email);
		if (!errors.get("errors").isEmpty()) {
			try {
				return new ResponseEntity<>(new ObjectMapper().writeValueAsString(errors),
						HttpStatus.UNPROCESSABLE_ENTITY);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return new ResponseEntity<>("Un expected error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		User user = saveUser(firstName, lastName, countryCode, phoneNumber, gender, birthdate, avatar, email);
		if (user == null)
			return new ResponseEntity<>("User not Saved", HttpStatus.UNPROCESSABLE_ENTITY);

		try {
			return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>("Un expected error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional(readOnly = true)
	public TokenDto generateToken(String phoneNumber, String password) {
		if (!ValidationUtility.isValidPhoneNumber(phoneNumber) || !ValidationUtility.isValidPassword(password))
			return TokenHandler.generateInvalidToken();

		User user = userRepository.findByPhoneNumber(phoneNumber);
		if (user != null) {
			String encPassword = PasswordUtility.encrypt(password);
			userRepository.updateUser(encPassword, phoneNumber);
			return TokenHandler.generateToken(phoneNumber, user.getFirstName(), encPassword);
		}
		return TokenHandler.generateInvalidToken();
	}

	public ResponseEntity<String> authenticate(String phoneNumber, String authToken) {
		if (!ValidationUtility.isValidPhoneNumber(phoneNumber))
			return new ResponseEntity<>("Phone number not valid", HttpStatus.BAD_REQUEST);

		User user = userRepository.findByPhoneNumber(phoneNumber);

		if (user == null)
			return new ResponseEntity<>("Phone number not exist", HttpStatus.BAD_REQUEST);

		if (!TokenHandler.isValidToken(authToken, user.getPassword(), phoneNumber))
			return new ResponseEntity<>("Not Authorized", HttpStatus.UNAUTHORIZED);

		try {
			return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new StatusDto(true, user)),
					HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<>("Un expected error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private User saveUser(String firstName, String lastName, String countryCode, String phoneNumber, String gender,
			String birthdate, MultipartFile avatar, String email) {
		try {
			return userRepository.save(new User(firstName, lastName, countryCode, phoneNumber, gender,
					DateUtility.convertFromStrToDate(birthdate), avatar.getName(), avatar.getBytes(), email));
		} catch (Exception e) {
			return null;
		}
	}

//	public static void main(String[] args) throws JsonProcessingException {
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		String jsonInString = mapper
//				.writeValueAsString(ValidationUtility.validateParameters("", "", "", "", "", "", null, ""));
//		System.out.println(jsonInString);
//
//	}

}

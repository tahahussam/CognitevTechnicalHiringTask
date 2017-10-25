package com.cogn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class ValidationUtility {
	private static final Set<String> ISO_COUNTRIES = new HashSet<>(Arrays.asList(Locale.getISOCountries()));
	private static final Set<String> GENDER_TYPES = new HashSet<>(Arrays.asList(new String[] { "male", "female" }));

	private static boolean isValidISOCountry(String countryCode) {
		return ISO_COUNTRIES.contains(countryCode);
	}

	private static boolean isValidGender(String gender) {
		return GENDER_TYPES.contains(gender.toLowerCase());
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Map<String, List<Map<String, List<Map<String, String>>>>> validateParameters(String firstName,
			String lastName, String countryCode, String phoneNumber, String gender, String birthdate,
			MultipartFile avatar, String email) {

		Map<String, List<Map<String, List<Map<String, String>>>>> errors = new LinkedHashMap<>();
		errors.put("errors", new ArrayList<>());

		errors.get("errors").add(validatName(firstName, "first_name"));
		errors.get("errors").add(validatName(lastName, "last_name"));
		errors.get("errors").add(validatCountryCode(countryCode));
		errors.get("errors").add(validatPhoneNumber(phoneNumber));
		errors.get("errors").add(validatGender(gender));
		errors.get("errors").add(validatBirthdate(birthdate));
		errors.get("errors").add(validatAvatar(avatar));
		errors.get("errors").add(validatEmail(email));

		return errors;
	}

	private static Map<String, List<Map<String, String>>> validatEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Map<String, List<Map<String, String>>> validatAvatar(MultipartFile avatar) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Map<String, List<Map<String, String>>> validatPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Map<String, List<Map<String, String>>> validatBirthdate(String birthdate) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isValidPassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	private static Map<String, List<Map<String, String>>> validatGender(String gender) {
		// Map<String, String> error = validatBlank(gender);
		if (!ValidationUtility.isValidGender(gender)) {
			return getErrorsMap("gender", getErrorsList(getError("error", "inclusion")));
		}
		return null;
	}

	private static Map<String, List<Map<String, String>>> validatCountryCode(String countryCode) {
		// Map<String, String> error = validatBlank(countryCode);
		if (!ValidationUtility.isValidISOCountry(countryCode)) {
			return getErrorsMap("country_code", getErrorsList(getError("error", "inclusion")));
		}
		return null;
	}

	private static Map<String, List<Map<String, String>>> validatName(String name, String fieldName) {
		Map<String, String> error = validatBlank(name);
		if (error != null) {
			return getErrorsMap(fieldName, getErrorsList(error));
		}
		return null;
	}

	private static Map<String, String> validatBlank(String field) {
		if (field == null || field.trim().isEmpty()) {
			return getError("error", "blank");
		}
		return null;
	}

	@SafeVarargs
	private static List<Map<String, String>> getErrorsList(Map<String, String>... errorsArray) {
		List<Map<String, String>> errorsList = new ArrayList<>();

		for (Map<String, String> error : errorsArray) {
			errorsList.add(error);
		}
		return errorsList;
	}

	private static Map<String, List<Map<String, String>>> getErrorsMap(String fieldName,
			List<Map<String, String>> errorsList) {
		Map<String, List<Map<String, String>>> errorsMap = new HashMap<>();
		errorsMap.put(fieldName, errorsList);
		return errorsMap;
	}

	private static Map<String, String> getError(String errorStr, String cause) {
		HashMap<String, String> error = new HashMap<String, String>();
		error.put(errorStr, cause);
		return error;
	}
}

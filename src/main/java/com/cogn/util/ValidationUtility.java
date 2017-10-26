package com.cogn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cogn.dao.JDBCUserRepo;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class ValidationUtility {

	private final static Logger logger = LoggerFactory.getLogger(ValidationUtility.class);

	private static final Set<String> ISO_COUNTRIES = new HashSet<>(Arrays.asList(Locale.getISOCountries()));
	private static final Set<String> GENDER_TYPES = new HashSet<>(Arrays.asList(new String[] { "male", "female" }));
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static Map<String, List<Map<String, List<Map<String, String>>>>> validateParameters(String firstName,
			String lastName, String countryCode, String phoneNumber, String gender, String birthdate,
			MultipartFile avatar, String email) {

		Map<String, List<Map<String, List<Map<String, String>>>>> errors = new LinkedHashMap<>();
		errors.put("errors", new ArrayList<>());

		Map<String, List<Map<String, String>>> error;

		error = validatName(firstName, "first_name");
		if (error != null)
			errors.get("errors").add(error);

		error = validatName(lastName, "last_name");
		if (error != null)
			errors.get("errors").add(error);

		error = validatCountryCode(countryCode);
		if (error != null)
			errors.get("errors").add(error);

		error = validatPhoneNumber(phoneNumber, countryCode);
		if (error != null)
			errors.get("errors").add(error);

		error = validatGender(gender);
		if (error != null)
			errors.get("errors").add(error);

		error = validatBirthdate(birthdate);
		if (error != null)
			errors.get("errors").add(error);

		error = validatAvatar(avatar);
		if (error != null)
			errors.get("errors").add(error);

		error = validatEmail(email);
		if (error != null)
			errors.get("errors").add(error);

		return errors;
	}

	private static Map<String, List<Map<String, String>>> validatName(String name, String fieldName) {
		Map<String, String> error = validatBlank(name);
		if (error != null)
			return getErrorsMap(fieldName, getErrorsList(error));

		return null;
	}

	private static Map<String, List<Map<String, String>>> validatCountryCode(String countryCode) {
		if (!ValidationUtility.isValidISOCountry(countryCode))
			return getErrorsMap("country_code", getErrorsList(getError("error", "inclusion")));

		return null;
	}

	private static Map<String, List<Map<String, String>>> validatPhoneNumber(String phoneNumber, String countryCode) {
		List<Map<String, String>> errorsList = new ArrayList<>();

		Map<String, String> error = validatBlank(phoneNumber);
		if (error != null)
			errorsList.add(error);

		if (!StringUtils.isNumeric(phoneNumber.substring(1)))
			errorsList.add(getError("error", "not_a_number"));

		if (phoneNumber.length() < 10)
			errorsList.add(getError("error", "too_short, count:" + phoneNumber.length()));

		if (phoneNumber.length() > 15)
			errorsList.add(getError("error", "too_long, count:" + phoneNumber.length()));

		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, null);

			String output = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

			if (!output.equals(phoneNumber))
				errorsList.add(getError("error", "Not in E164 format"));

			if (!phoneUtil.isValidNumber(phoneNumberProto))
				errorsList.add(getError("error", "invalid"));

			if (!phoneUtil.isValidNumberForRegion(phoneNumberProto, countryCode))
				errorsList.add(getError("error", "invalid for country code " + countryCode));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorsList.add(getError("error", "invalid"));
		}

		if (JDBCUserRepo.findByPhoneNumber(phoneNumber) != null)
			errorsList.add(getError("error", "taken"));

		return errorsList.isEmpty() ? null : getErrorsMap("phone_number", errorsList);
	}

	private static Map<String, List<Map<String, String>>> validatGender(String gender) {
		if (!ValidationUtility.isValidGender(gender))
			return getErrorsMap("gender", getErrorsList(getError("error", "inclusion")));

		return null;
	}

	private static Map<String, List<Map<String, String>>> validatBirthdate(String birthdate) {
		List<Map<String, String>> errorsList = new ArrayList<>();

		Map<String, String> error = validatBlank(birthdate);
		if (error != null)
			errorsList.add(error);

		error = getErrorDate(birthdate);

		if (error != null)
			errorsList.add(error);

		return errorsList.isEmpty() ? null : getErrorsMap("birthdate", errorsList);
	}

	private static Map<String, String> getErrorDate(String birthdate) {
		Date date = DateUtility.convertFromStrToDate(birthdate);
		if (date == null)
			return getError("error", "Not valid date format, valid format is yyyy-MM-dd");

		if (date.after(new Date()))
			return getError("error", "in_the_future");

		return null;
	}

	private static Map<String, List<Map<String, String>>> validatAvatar(MultipartFile avatar) {
		List<Map<String, String>> errorsList = new ArrayList<>();

		if (avatar == null || avatar.isEmpty() || avatar.getSize() == 0)
			errorsList.add(getError("error", "blank"));

		String contentType = avatar.getContentType();

		if (!contentType.contains("image/jpg") && !contentType.contains("image/jpeg")
				&& !contentType.contains("image/png"))
			errorsList.add(getError("error", "invalid_content_type"));

		if (avatar.getSize() > 1024000)
			errorsList.add(getError("error", "Maximum File size is 1MB"));

		return errorsList.isEmpty() ? null : getErrorsMap("avatar", errorsList);
	}

	private static Map<String, List<Map<String, String>>> validatEmail(String email) {
		List<Map<String, String>> errorsList = new ArrayList<>();

		if (!validateEmail(email))
			errorsList.add(getError("error", "invalid"));

		if (isTakenEmail(email))
			errorsList.add(getError("error", "taken"));

		return errorsList.isEmpty() ? null : getErrorsMap("email", errorsList);
	}

	private static boolean isTakenEmail(String email) {
		return JDBCUserRepo.findByUserByEmail(email) != null;
	}

	private static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static boolean isValidPassword(String password) {
		if (password == null || password.trim().isEmpty() || password.trim().length() < 6)
			return false;
		return true;
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {

		if (!StringUtils.isNumeric(phoneNumber.substring(1)))
			return false;

		if (phoneNumber.length() < 10 || phoneNumber.length() > 15)
			return false;

		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, null);
			if (!phoneUtil.isValidNumber(phoneNumberProto))
				return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	private static boolean isValidISOCountry(String countryCode) {
		return ISO_COUNTRIES.contains(countryCode);
	}

	private static boolean isValidGender(String gender) {
		return GENDER_TYPES.contains(gender.toLowerCase());
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

package com.cogn.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 5425628681542795167L;

	public User(String firstName, String lastName, String countryCode, String phoneNumber, String gender,
			Date birthDate, String avatarName, byte[] avatar, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthDate = birthDate;
		this.avatarName = avatarName;
		this.avatar = avatar;
		this.email = email;
	}

	private Long id;

	private String firstName;

	private String lastName;

	private String countryCode;

	private String phoneNumber;

	private String gender;

	private Date birthDate;

	private String avatarName;

	private byte[] avatar;

	private String email;

	private String password;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", countryCode=" + countryCode
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", birthDate=" + birthDate + ", avatarName="
				+ avatarName + ", email=" + email + "]";
	}

}

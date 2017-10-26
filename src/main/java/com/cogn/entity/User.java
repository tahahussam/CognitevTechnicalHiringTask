package com.cogn.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	public User(long id, String firstName, String lastName, String countryCode, String phoneNumber, String gender,
			Date birthDate, String avatarName, byte[] avatar, String email, String password) {
		this(firstName, lastName, countryCode, phoneNumber, gender, birthDate, avatarName, avatar, email);
		this.id = id;
		this.password = password;
	}

	private Long id;

	private String firstName;

	private String lastName;

	private String countryCode;

	private String phoneNumber;

	private String gender;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthDate;

	@JsonIgnore
	private String avatarName;
	@JsonIgnore
	private byte[] avatar;
	@JsonIgnore
	private String email;
	@JsonIgnore
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

	public String getCountryCode() {
		return countryCode;
	}

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

	public Long getId() {
		return id;
	}

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

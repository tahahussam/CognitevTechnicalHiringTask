package com.cogn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

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

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@Column(name = "phone_number", unique = true, nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private Date birthDate;

	@Column(name = "avatar_name", nullable = false)
	private String avatarName;

	@Column(nullable = false)
	private byte[] avatar;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

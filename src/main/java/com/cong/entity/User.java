package com.cong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "code_country", nullable = false)
	private String codeCountry;

	@Column(name = "phone_number", unique = true, nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String birthDate;

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

	public String getCodeCountry() {
		return codeCountry;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthDate() {
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

	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(String birthDate) {
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

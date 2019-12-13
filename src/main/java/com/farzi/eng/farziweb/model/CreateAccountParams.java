package com.farzi.eng.farziweb.model;

import com.google.gson.annotations.SerializedName;

public class CreateAccountParams {

	@SerializedName("username")
	private String username;

	@SerializedName("password")
	private String password;

	@SerializedName("email")
	private String email;

	@SerializedName("mobileNumber")
	private String mobileNumber;

	@SerializedName("otp")
	private String otp;

	public void setUsername(String message) {
		this.username = message;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
package com.farzi.eng.farziweb.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

	@SerializedName("message")
	private String message;

	@SerializedName("code")
	private int code = -1;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

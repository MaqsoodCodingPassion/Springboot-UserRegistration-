package com.farzi.eng.farziweb.model;

import com.google.gson.annotations.SerializedName;

public class OTPParams{

	@SerializedName("number")
	private String number;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	@Override
 	public String toString(){
		return 
			"OTPParams{" + 
			"number = '" + number + '\'' + 
			"}";
		}
}
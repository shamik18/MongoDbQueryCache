package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bathrooms{

	@JsonProperty("$numberDecimal")
	private String numberDecimal;

	public void setNumberDecimal(String numberDecimal){
		this.numberDecimal = numberDecimal;
	}

	public String getNumberDecimal(){
		return numberDecimal;
	}
}
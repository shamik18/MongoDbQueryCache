package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtraPeople{

	@JsonProperty("$numberDecimal")
	private String numberDecimal;

	public void setNumberDecimal(String numberDecimal){
		this.numberDecimal = numberDecimal;
	}

	public String getNumberDecimal(){
		return numberDecimal;
	}
}
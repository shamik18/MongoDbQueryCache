package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstReview{

	@JsonProperty("$date")
	private String date;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}
}
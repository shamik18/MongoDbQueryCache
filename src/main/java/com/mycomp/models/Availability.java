package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Availability{

	@JsonProperty("availability_365")
	private int availability365;

	@JsonProperty("availability_30")
	private int availability30;

	@JsonProperty("availability_60")
	private int availability60;

	@JsonProperty("availability_90")
	private int availability90;

	public void setAvailability365(int availability365){
		this.availability365 = availability365;
	}

	public int getAvailability365(){
		return availability365;
	}

	public void setAvailability30(int availability30){
		this.availability30 = availability30;
	}

	public int getAvailability30(){
		return availability30;
	}

	public void setAvailability60(int availability60){
		this.availability60 = availability60;
	}

	public int getAvailability60(){
		return availability60;
	}

	public void setAvailability90(int availability90){
		this.availability90 = availability90;
	}

	public int getAvailability90(){
		return availability90;
	}
}
package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price{

	@JsonProperty("$numberDecimal")
	@Column(name = "price")
	private String numberDecimal;

	public void setNumberDecimal(String numberDecimal){
		this.numberDecimal = numberDecimal;
	}

	public String getNumberDecimal(){
		return numberDecimal;
	}
}
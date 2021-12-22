package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("market")
	private String market;

	@JsonProperty("country")
	private String country;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("street")
	private String street;

	@JsonProperty("suburb")
	private String suburb;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("government_area")
	private String governmentArea;

	public void setMarket(String market){
		this.market = market;
	}

	public String getMarket(){
		return market;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setSuburb(String suburb){
		this.suburb = suburb;
	}

	public String getSuburb(){
		return suburb;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setGovernmentArea(String governmentArea){
		this.governmentArea = governmentArea;
	}

	public String getGovernmentArea(){
		return governmentArea;
	}
}
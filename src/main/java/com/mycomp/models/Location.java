package com.mycomp.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location{

	@JsonProperty("coordinates")
	private List<Double> coordinates;

	@JsonProperty("type")
	private String type;

	@JsonProperty("is_location_exact")
	private boolean isLocationExact;

	public void setCoordinates(List<Double> coordinates){
		this.coordinates = coordinates;
	}

	public List<Double> getCoordinates(){
		return coordinates;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setIsLocationExact(boolean isLocationExact){
		this.isLocationExact = isLocationExact;
	}

	public boolean isIsLocationExact(){
		return isLocationExact;
	}
}
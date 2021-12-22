package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images{

	@JsonProperty("picture_url")
	private String pictureUrl;

	@JsonProperty("xl_picture_url")
	private String xlPictureUrl;

	@JsonProperty("medium_url")
	private String mediumUrl;

	@JsonProperty("thumbnail_url")
	private String thumbnailUrl;

	public void setPictureUrl(String pictureUrl){
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl(){
		return pictureUrl;
	}

	public void setXlPictureUrl(String xlPictureUrl){
		this.xlPictureUrl = xlPictureUrl;
	}

	public String getXlPictureUrl(){
		return xlPictureUrl;
	}

	public void setMediumUrl(String mediumUrl){
		this.mediumUrl = mediumUrl;
	}

	public String getMediumUrl(){
		return mediumUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}
}
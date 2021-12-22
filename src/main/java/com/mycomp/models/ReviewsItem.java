package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewsItem{

	@JsonProperty("date")
	private Date date;

	@JsonProperty("comments")
	private String comments;

	@JsonProperty("listing_id")
	private String listingId;

	@JsonProperty("reviewer_name")
	private String reviewerName;

	@JsonProperty("reviewer_id")
	private String reviewerId;

	@JsonProperty("_id")
	private String id;

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setListingId(String listingId){
		this.listingId = listingId;
	}

	public String getListingId(){
		return listingId;
	}

	public void setReviewerName(String reviewerName){
		this.reviewerName = reviewerName;
	}

	public String getReviewerName(){
		return reviewerName;
	}

	public void setReviewerId(String reviewerId){
		this.reviewerId = reviewerId;
	}

	public String getReviewerId(){
		return reviewerId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}
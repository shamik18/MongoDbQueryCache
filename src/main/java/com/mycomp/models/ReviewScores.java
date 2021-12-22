package com.mycomp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewScores{

	@JsonProperty("review_scores_checkin")
	private int reviewScoresCheckin;

	@JsonProperty("review_scores_communication")
	private int reviewScoresCommunication;

	@JsonProperty("review_scores_rating")
	private int reviewScoresRating;

	@JsonProperty("review_scores_accuracy")
	private int reviewScoresAccuracy;

	@JsonProperty("review_scores_location")
	private int reviewScoresLocation;

	@JsonProperty("review_scores_value")
	private int reviewScoresValue;

	@JsonProperty("review_scores_cleanliness")
	private int reviewScoresCleanliness;

	public void setReviewScoresCheckin(int reviewScoresCheckin){
		this.reviewScoresCheckin = reviewScoresCheckin;
	}

	public int getReviewScoresCheckin(){
		return reviewScoresCheckin;
	}

	public void setReviewScoresCommunication(int reviewScoresCommunication){
		this.reviewScoresCommunication = reviewScoresCommunication;
	}

	public int getReviewScoresCommunication(){
		return reviewScoresCommunication;
	}

	public void setReviewScoresRating(int reviewScoresRating){
		this.reviewScoresRating = reviewScoresRating;
	}

	public int getReviewScoresRating(){
		return reviewScoresRating;
	}

	public void setReviewScoresAccuracy(int reviewScoresAccuracy){
		this.reviewScoresAccuracy = reviewScoresAccuracy;
	}

	public int getReviewScoresAccuracy(){
		return reviewScoresAccuracy;
	}

	public void setReviewScoresLocation(int reviewScoresLocation){
		this.reviewScoresLocation = reviewScoresLocation;
	}

	public int getReviewScoresLocation(){
		return reviewScoresLocation;
	}

	public void setReviewScoresValue(int reviewScoresValue){
		this.reviewScoresValue = reviewScoresValue;
	}

	public int getReviewScoresValue(){
		return reviewScoresValue;
	}

	public void setReviewScoresCleanliness(int reviewScoresCleanliness){
		this.reviewScoresCleanliness = reviewScoresCleanliness;
	}

	public int getReviewScoresCleanliness(){
		return reviewScoresCleanliness;
	}
}
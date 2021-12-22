package com.mycomp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "HomeProperty")
public class HomeProperty{

	@JsonProperty("amenities")
	@Transient
	private List<String> amenities;

	@JsonProperty("notes")
	@Column(length = 5000)
	private String notes;

	@JsonProperty("access")
	@Column(length = 3000)
	private String access;

	@JsonProperty("house_rules")
	@Column(length = 2000)
	private String houseRules;

	@JsonProperty("description")
	@Column(length = 7000)
	private String description;

	@JsonProperty("calendar_last_scraped")
	@Transient
	private CalendarLastScraped calendarLastScraped;

	@JsonProperty("first_review")
	@Transient
	private FirstReview firstReview;

	@JsonProperty("neighborhood_overview")
	@Column(length = 5000)
	private String neighborhoodOverview;

	@JsonProperty("number_of_reviews")
	private int numberOfReviews;

	@JsonProperty("availability")
	@Transient
	private Availability availability;

	@JsonProperty("space")
	@Column(length = 5000)
	private String space;

	@JsonProperty("review_scores")
	@Transient
	private ReviewScores reviewScores;

	@JsonProperty("cleaning_fee")
	@Transient
	private CleaningFee cleaningFee;

	@JsonProperty("reviews")
	@Transient
	private List<ReviewsItem> reviews;

	@JsonProperty("price")
	@Embedded
	private Price price;

	@JsonProperty("host")
	@Transient
	private Host host;

	@JsonProperty("property_type")
	@Column(length = 2000)
	private String propertyType;

	@JsonProperty("summary")
	@Column(length = 5000)
	private String summary;

	@JsonProperty("security_deposit")
	@Transient
	private SecurityDeposit securityDeposit;

	@JsonProperty("images")
	@Transient
	private Images images;

	@JsonProperty("address")
	@Transient
	private Address address;

	@JsonProperty("bed_type")
	private String bedType;

	@JsonProperty("listing_url")
	private String listingUrl;

	@JsonProperty("guests_included")
	@Transient
	private GuestsIncluded guestsIncluded;

	@JsonProperty("maximum_nights")
	private String maximumNights;

	@JsonProperty("bathrooms")
	@Transient
	private Bathrooms bathrooms;

	@JsonProperty("bedrooms")
	private int bedrooms;

	@JsonProperty("extra_people")
	@Transient
	private ExtraPeople extraPeople;

	@JsonProperty("minimum_nights")
	private String minimumNights;

	@JsonProperty("last_review")
	@Transient
	private LastReview lastReview;

	@JsonProperty("transit")
	@Column(length = 3000)
	private String transit;

	@JsonProperty("accommodates")
	private int accommodates;

	@JsonProperty("name")
	private String name;

	@JsonProperty("interaction")
	@Column(length = 2000)
	private String interaction;

	@JsonProperty("cancellation_policy")
	@Transient
	private String cancellationPolicy;

	@JsonProperty("_id")
	@Id
	private String id;

	@JsonProperty("beds")
	private int beds;

	@JsonProperty("last_scraped")
	@Transient
	private LastScraped lastScraped;

	@JsonProperty("room_type")
	private String roomType;

	public void setAmenities(List<String> amenities){
		this.amenities = amenities;
	}

	public List<String> getAmenities(){
		return amenities;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setAccess(String access){
		this.access = access;
	}

	public String getAccess(){
		return access;
	}

	public void setHouseRules(String houseRules){
		this.houseRules = houseRules;
	}

	public String getHouseRules(){
		return houseRules;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCalendarLastScraped(CalendarLastScraped calendarLastScraped){
		this.calendarLastScraped = calendarLastScraped;
	}

	public CalendarLastScraped getCalendarLastScraped(){
		return calendarLastScraped;
	}

	public void setFirstReview(FirstReview firstReview){
		this.firstReview = firstReview;
	}

	public FirstReview getFirstReview(){
		return firstReview;
	}

	public void setNeighborhoodOverview(String neighborhoodOverview){
		this.neighborhoodOverview = neighborhoodOverview;
	}

	public String getNeighborhoodOverview(){
		return neighborhoodOverview;
	}

	public void setNumberOfReviews(int numberOfReviews){
		this.numberOfReviews = numberOfReviews;
	}

	public int getNumberOfReviews(){
		return numberOfReviews;
	}

	public void setAvailability(Availability availability){
		this.availability = availability;
	}

	public Availability getAvailability(){
		return availability;
	}

	public void setSpace(String space){
		this.space = space;
	}

	public String getSpace(){
		return space;
	}

	public void setReviewScores(ReviewScores reviewScores){
		this.reviewScores = reviewScores;
	}

	public ReviewScores getReviewScores(){
		return reviewScores;
	}

	public void setCleaningFee(CleaningFee cleaningFee){
		this.cleaningFee = cleaningFee;
	}

	public CleaningFee getCleaningFee(){
		return cleaningFee;
	}

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
	}

	public void setPrice(Price price){
		this.price = price;
	}

	public Price getPrice(){
		return price;
	}

	public void setHost(Host host){
		this.host = host;
	}

	public Host getHost(){
		return host;
	}

	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
	}

	public String getPropertyType(){
		return propertyType;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setSecurityDeposit(SecurityDeposit securityDeposit){
		this.securityDeposit = securityDeposit;
	}

	public SecurityDeposit getSecurityDeposit(){
		return securityDeposit;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Images getImages(){
		return images;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setBedType(String bedType){
		this.bedType = bedType;
	}

	public String getBedType(){
		return bedType;
	}

	public void setListingUrl(String listingUrl){
		this.listingUrl = listingUrl;
	}

	public String getListingUrl(){
		return listingUrl;
	}

	public void setGuestsIncluded(GuestsIncluded guestsIncluded){
		this.guestsIncluded = guestsIncluded;
	}

	public GuestsIncluded getGuestsIncluded(){
		return guestsIncluded;
	}

	public void setMaximumNights(String maximumNights){
		this.maximumNights = maximumNights;
	}

	public String getMaximumNights(){
		return maximumNights;
	}

	public void setBathrooms(Bathrooms bathrooms){
		this.bathrooms = bathrooms;
	}

	public Bathrooms getBathrooms(){
		return bathrooms;
	}

	public void setBedrooms(int bedrooms){
		this.bedrooms = bedrooms;
	}

	public int getBedrooms(){
		return bedrooms;
	}

	public void setExtraPeople(ExtraPeople extraPeople){
		this.extraPeople = extraPeople;
	}

	public ExtraPeople getExtraPeople(){
		return extraPeople;
	}

	public void setMinimumNights(String minimumNights){
		this.minimumNights = minimumNights;
	}

	public String getMinimumNights(){
		return minimumNights;
	}

	public void setLastReview(LastReview lastReview){
		this.lastReview = lastReview;
	}

	public LastReview getLastReview(){
		return lastReview;
	}

	public void setTransit(String transit){
		this.transit = transit;
	}

	public String getTransit(){
		return transit;
	}

	public void setAccommodates(int accommodates){
		this.accommodates = accommodates;
	}

	public int getAccommodates(){
		return accommodates;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setInteraction(String interaction){
		this.interaction = interaction;
	}

	public String getInteraction(){
		return interaction;
	}

	public void setCancellationPolicy(String cancellationPolicy){
		this.cancellationPolicy = cancellationPolicy;
	}

	public String getCancellationPolicy(){
		return cancellationPolicy;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBeds(int beds){
		this.beds = beds;
	}

	public int getBeds(){
		return beds;
	}

	public void setLastScraped(LastScraped lastScraped){
		this.lastScraped = lastScraped;
	}

	public LastScraped getLastScraped(){
		return lastScraped;
	}

	public void setRoomType(String roomType){
		this.roomType = roomType;
	}

	public String getRoomType(){
		return roomType;
	}
}
package com.mycomp.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Host{

	@JsonProperty("host_verifications")
	private List<String> hostVerifications;

	@JsonProperty("host_url")
	private String hostUrl;

	@JsonProperty("host_response_time")
	private String hostResponseTime;

	@JsonProperty("host_about")
	private String hostAbout;

	@JsonProperty("host_picture_url")
	private String hostPictureUrl;

	@JsonProperty("host_has_profile_pic")
	private boolean hostHasProfilePic;

	@JsonProperty("host_id")
	private String hostId;

	@JsonProperty("host_listings_count")
	private int hostListingsCount;

	@JsonProperty("host_total_listings_count")
	private int hostTotalListingsCount;

	@JsonProperty("host_location")
	private String hostLocation;

	@JsonProperty("host_is_superhost")
	private boolean hostIsSuperhost;

	@JsonProperty("host_thumbnail_url")
	private String hostThumbnailUrl;

	@JsonProperty("host_neighbourhood")
	private String hostNeighbourhood;

	@JsonProperty("host_response_rate")
	private int hostResponseRate;

	@JsonProperty("host_name")
	private String hostName;

	@JsonProperty("host_identity_verified")
	private boolean hostIdentityVerified;

	public void setHostVerifications(List<String> hostVerifications){
		this.hostVerifications = hostVerifications;
	}

	public List<String> getHostVerifications(){
		return hostVerifications;
	}

	public void setHostUrl(String hostUrl){
		this.hostUrl = hostUrl;
	}

	public String getHostUrl(){
		return hostUrl;
	}

	public void setHostResponseTime(String hostResponseTime){
		this.hostResponseTime = hostResponseTime;
	}

	public String getHostResponseTime(){
		return hostResponseTime;
	}

	public void setHostAbout(String hostAbout){
		this.hostAbout = hostAbout;
	}

	public String getHostAbout(){
		return hostAbout;
	}

	public void setHostPictureUrl(String hostPictureUrl){
		this.hostPictureUrl = hostPictureUrl;
	}

	public String getHostPictureUrl(){
		return hostPictureUrl;
	}

	public void setHostHasProfilePic(boolean hostHasProfilePic){
		this.hostHasProfilePic = hostHasProfilePic;
	}

	public boolean isHostHasProfilePic(){
		return hostHasProfilePic;
	}

	public void setHostId(String hostId){
		this.hostId = hostId;
	}

	public String getHostId(){
		return hostId;
	}

	public void setHostListingsCount(int hostListingsCount){
		this.hostListingsCount = hostListingsCount;
	}

	public int getHostListingsCount(){
		return hostListingsCount;
	}

	public void setHostTotalListingsCount(int hostTotalListingsCount){
		this.hostTotalListingsCount = hostTotalListingsCount;
	}

	public int getHostTotalListingsCount(){
		return hostTotalListingsCount;
	}

	public void setHostLocation(String hostLocation){
		this.hostLocation = hostLocation;
	}

	public String getHostLocation(){
		return hostLocation;
	}

	public void setHostIsSuperhost(boolean hostIsSuperhost){
		this.hostIsSuperhost = hostIsSuperhost;
	}

	public boolean isHostIsSuperhost(){
		return hostIsSuperhost;
	}

	public void setHostThumbnailUrl(String hostThumbnailUrl){
		this.hostThumbnailUrl = hostThumbnailUrl;
	}

	public String getHostThumbnailUrl(){
		return hostThumbnailUrl;
	}

	public void setHostNeighbourhood(String hostNeighbourhood){
		this.hostNeighbourhood = hostNeighbourhood;
	}

	public String getHostNeighbourhood(){
		return hostNeighbourhood;
	}

	public void setHostResponseRate(int hostResponseRate){
		this.hostResponseRate = hostResponseRate;
	}

	public int getHostResponseRate(){
		return hostResponseRate;
	}

	public void setHostName(String hostName){
		this.hostName = hostName;
	}

	public String getHostName(){
		return hostName;
	}

	public void setHostIdentityVerified(boolean hostIdentityVerified){
		this.hostIdentityVerified = hostIdentityVerified;
	}

	public boolean isHostIdentityVerified(){
		return hostIdentityVerified;
	}
}
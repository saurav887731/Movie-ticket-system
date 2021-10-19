package com.example.demo.requestmodel;

import java.util.List;

// Request model to create a new Booking
public class NewBooking {
	private String userId;
	private String showId;
	private List<String> seatsIds;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public List<String> getSeatsIds() {
		return seatsIds;
	}
	public void setSeatsIds(List<String> seatsIds) {
		this.seatsIds = seatsIds;
	}

}

package com.example.demo.requestmodel;

// Request model payload to create a screen
public class CreateScreen {
	private String screenName;
	private String theatreId;
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}

}


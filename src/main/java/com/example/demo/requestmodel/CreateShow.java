package com.example.demo.requestmodel;

// Request model payload to create a show
public class CreateShow {
	private String movieId;
	private String screenId;
	private String startTime;
    private Integer durationInSeconds;
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getDurationInSeconds() {
		return durationInSeconds;
	}
	public void setDurationInSeconds(Integer durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
    
    

}

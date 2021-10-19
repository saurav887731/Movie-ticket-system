package com.example.demo.model;

public class Show {
	
	private final String id; // This can't be changed
    private Movie movie;
    private Screen screen;
    private String startTime;
    private Integer durationInSeconds;
    
	public Show(String id, Movie movie, Screen screen, String startTime, Integer durationInSeconds) {
		super();
		this.id = id;
		this.movie = movie;
		this.screen = screen;
		this.startTime = startTime;
		this.durationInSeconds = durationInSeconds;
	}

	public String getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
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

package com.example.demo.model;

public class Movie {
	
	private final String id;
    private final String name;
    
    public Movie(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}

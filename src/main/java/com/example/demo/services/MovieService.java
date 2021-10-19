package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Movie;

@Service
public class MovieService {

	Logger logger=	LoggerFactory.getLogger(MovieService.class);
	private final Map<String, Movie> movies;

	public MovieService() {
		super();
		this.movies = new HashMap<>();
	}
	
	// To get the movie with particular id 
	public Movie getMovie(@NonNull final String movieId) {
        if (!movies.containsKey(movieId)) {
        	logger.error("Given movieId is missing");
            throw new RuntimeException("Movie Id not found");
        }
        return movies.get(movieId);
    }
	
	// To get all the list of movies running
	public List<Movie> getAllMovies()
	{
		logger.trace("getAllMovies method to get all movies");
		List<Movie> list=new ArrayList<Movie>();
		for(Map.Entry<String, Movie> entry : movies.entrySet())
			list.add(entry.getValue());
		return list;
	}
	
	// To create a particular movie with particular id
    public Movie createMovie( final Movie movieName) {
    	logger.trace("createMovie method with movieName accessed:"+movieName);
        Movie movie = new Movie(movieName.getId(), movieName.getName());
        movies.put(movieName.getId(), movie);
        logger.info("Movie with movieId:"+movieName.getId()+" is created");
        return movie;
    }
	
}

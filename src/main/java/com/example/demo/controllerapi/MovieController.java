package com.example.demo.controllerapi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.*;

import com.example.demo.services.MovieService;

@RestController
public class MovieController {
	
	Logger logger=	LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieService movieService;

//	This would create a movie with particular id
	@RequestMapping(method=RequestMethod.POST,value="/createmovie")
    public Movie createMovie(@RequestBody @NonNull final Movie movieName) {
		logger.trace("createMovie method accessed");
        return movieService.createMovie(movieName);
    }
	
//	This would show the list of all the movies 
	@RequestMapping("/getAllMovies")
	public List<Movie> getAllMovies(){
		logger.trace("getAllMovies method accessed");
		return movieService.getAllMovies();	
	}

}

package com.example.demo.services;

import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ShowService {
	
	Logger logger=	LoggerFactory.getLogger(ShowService.class);
			
	private final Map<String, Show> shows;
	
	  public ShowService() {
	        this.shows = new HashMap<>();
	    }

	  // This will get the show with particular showId
	    public Show getShow(@NonNull final String showId) {
	        if (!shows.containsKey(showId)) {
	        	logger.error("showId given is missing");
	            throw new RuntimeException("Show Id not found");
	        }
	        return shows.get(showId);
	    }

	    // This will create a show in a screen with a particular 
	    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final String startTime,
	                           @NonNull final Integer durationInSeconds) {
	    	logger.trace("createshow method with movie:"+movie +" screen :"+screen +" startTime:"+startTime +" duration:"+durationInSeconds);
	        String showId = UUID.randomUUID().toString();
	        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
	        this.shows.put(showId, show);
	        logger.info("Show is created with screenId:"+showId);
	        return show;
	    }

	    // We could get multiple show for a particular screen
	    public List<Show> getShowsForScreen(final Screen screen) {
	    	logger.trace("getShowsForScreen method for screen:"+screen);
	        final List<Show> response = new ArrayList<>();
	        for (Show show : shows.values()) {
	            if (show.getScreen().equals(screen)) {
	                response.add(show);
	            }
	        }
	        return response;
	    }
}

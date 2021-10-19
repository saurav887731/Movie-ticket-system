package com.example.demo.controllerapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.model.Screen;
import com.example.demo.model.Show;
import com.example.demo.requestmodel.CreateShow;
import com.example.demo.services.MovieService;
import com.example.demo.services.ShowService;
import com.example.demo.services.TheatreService;

@RestController
public class ShowController {
	
	Logger logger=	LoggerFactory.getLogger(ShowController.class);
	
	
	@Autowired
    private ShowService showService;
	@Autowired
    private TheatreService theatreService;
	@Autowired
    private MovieService movieService;

	// This would create a show in a theatre with a particular movie
    @RequestMapping(method=RequestMethod.POST , value="/createshow")
    public Show createShow(@RequestBody @NonNull CreateShow createshow) {
    	logger.trace("CreateShow method accessed");
        final Screen screen = theatreService.getScreen(createshow.getScreenId());
        final Movie movie = movieService.getMovie(createshow.getMovieId());
        return showService.createShow(movie, screen, createshow.getStartTime(), createshow.getDurationInSeconds());
    }


}

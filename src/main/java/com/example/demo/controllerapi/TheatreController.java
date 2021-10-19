package com.example.demo.controllerapi;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Screen;
import com.example.demo.model.Seat;
import com.example.demo.model.Theatre;
import com.example.demo.requestmodel.CreateScreen;
import com.example.demo.requestmodel.CreateSeat;
import com.example.demo.services.TheatreService;

@RestController
public class TheatreController {
	
	Logger logger=	LoggerFactory.getLogger(TheatreController.class);
	
	@Autowired
	 private TheatreService theatreService;
	
	// This would create a threatre with all relevant information
	@RequestMapping(method=RequestMethod.POST,value="/createtheatre")
    public Theatre createTheatre(@RequestBody @NonNull final Theatre theatre) {
		logger.trace("createTheatre method accessed");
        return theatreService.createTheatre(theatre.getId(),theatre.getName(),theatre.getCity(),theatre.getAddress());
    }

	// Gets all the theatre in a particular city
	@RequestMapping(method=RequestMethod.GET,value="/searchcity")
	public List<Theatre> getTheatreByCity(@RequestParam @NonNull final String city){
		logger.trace("GetTheatreByCity method accessed");
		return theatreService.getTheatreByCity(city);
	}
	
	// Get all the cities where theatre are present
	@RequestMapping("/getallcities")
	public Set <String> getAllCities(){
		logger.trace("getallCities method accessed");
		return theatreService.getAllCities();
	}
	
	// Create Screen in a particular theatre
	@RequestMapping(method=RequestMethod.POST,value="/createscreen")
    public Screen createScreenInTheatre(@RequestBody @NonNull final CreateScreen createscreen) {
		logger.trace("createScreenInTheatre method accessed");
        final Theatre theatre = theatreService.getTheatre(createscreen.getTheatreId());
        return theatreService.createScreenInTheatre(createscreen.getScreenName(), theatre);
    }
	
	// Create a seat in a particular screen in a theatre
	@RequestMapping(method=RequestMethod.POST , value="/createseat")
    public Seat createSeatInScreen(@RequestBody @NonNull final CreateSeat createseat) {
		logger.trace("createSeatInScreen method accessed");
        final Screen screen = theatreService.getScreen(createseat.getScreenId());
        return theatreService.createSeatInScreen(createseat.getSeatNo(), screen);
    }

}

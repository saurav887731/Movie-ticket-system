package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;

@Service
public class TheatreService {
	Logger logger=	LoggerFactory.getLogger(TheatreService.class);
	
	// In memory uses otherwise could be from database 
	private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;
    
    public TheatreService() {
    	super();
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }
    
    public Seat getSeat(@NonNull final String seatId) {
        if (!seats.containsKey(seatId)) {
        	logger.error(seatId +": This seatId is missing");
            throw new  RuntimeException("Seat is not present");
        }
        return seats.get(seatId);
    }
    
    public Theatre getTheatre(@NonNull final String theatreId) {
        if (!theatres.containsKey(theatreId)) {
        	logger.error(theatreId+":This theatreId is missing");
            throw new RuntimeException("This theatre is not present");
        }
        return theatres.get(theatreId);
    }
    
    public Screen getScreen(@NonNull final String screenId) {
        if (!screens.containsKey(screenId)) {
        	logger.error(screenId+":This screenId is missing");
            throw new RuntimeException("This screen is not present");
        }
        return screens.get(screenId);
    }
    
    public Theatre createTheatre(@NonNull final String theatreId,@NonNull final String theatreName , @NonNull final String city , @NonNull final String address) {
        Theatre theatre = new Theatre(theatreId, theatreName , city , address);
        theatres.put(theatreId, theatre);
        logger.info("Theatre with theatreId:"+theatreId+" created");
        return theatre;
    }
    
    public Screen createScreenInTheatre(@NonNull final String screenName, @NonNull final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        logger.info("Screen added to theatre with theatreId :"+theatre.getId());
        return screen;
    }
    
    public Seat createSeatInScreen( @NonNull final Integer seatNo, @NonNull final Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, seatNo);
        seats.put(seatId, seat);
        logger.info("Seat created with seatId:"+seatId);
        screen.addSeat(seat);
        return seat;
    }
    
    private Screen createScreen(final String screenName, final Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre.getId());
        screens.put(screenId, screen);
        logger.info("Screen created with screenId:"+screenId);
        return screen;
    }
    
    public List<Theatre> getTheatreByCity(@NonNull final String city)
    {
    	logger.trace("getTheatreByCity method with city:"+city);
    	List<Theatre> list=new ArrayList<Theatre>();
		for(Map.Entry<String, Theatre> entry : theatres.entrySet())
		{
			if(entry.getValue().getCity().equals(city))
				list.add(entry.getValue());
		}
		return list;
    }
    
    public Set<String> getAllCities()
    {
    	logger.trace("getAllCities method in TheatreService accessed");
    	Set<String> list=new HashSet<String>();
    	for(Map.Entry<String, Theatre> entry : theatres.entrySet())
    		list.add(entry.getValue().getCity());
    	return list;
    }
    
}

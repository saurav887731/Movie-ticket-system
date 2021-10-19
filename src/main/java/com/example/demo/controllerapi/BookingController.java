package com.example.demo.controllerapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Booking;
import com.example.demo.model.Seat;
import com.example.demo.model.Show;
import com.example.demo.requestmodel.NewBooking;
import com.example.demo.services.BookingService;
import com.example.demo.services.ShowService;
import com.example.demo.services.TheatreService;

@RestController
public class BookingController {
	
	Logger logger=	LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private ShowService showService;
	@Autowired
    private BookingService bookingService;
	@Autowired
    private TheatreService theatreService;
	
	// This will create a Newbooking for the particular show with the given available seats
    @RequestMapping(method=RequestMethod.POST , value="/createbooking")
    public Booking createBooking(@RequestBody @NonNull NewBooking newBooking) {
    	logger.trace("createBooking method accessed");
        final Show show = showService.getShow(newBooking.getShowId());
        final List<Seat> seats = newBooking.getSeatsIds().stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(newBooking.getUserId(), show, seats);
    }
    
    // This will give all the bookings for a particular show
    @RequestMapping(method=RequestMethod.GET,value="/getallbookingsforshow")
    public List<Booking> getAllBookings(@RequestParam @NonNull final String showId)
    {
    	logger.trace("getAllBookings method accessed for a show ");
    	final Show show = showService.getShow(showId);
    	return bookingService.getAllBookings(show);
    }
    
    // This will give the no of available seats for the particular show
    @RequestMapping(method=RequestMethod.GET, value="/availableseatsforshow")
	public List<Seat> getAvailableSeats(@RequestParam @NonNull final String showId)
	{
    	logger.trace("getAvailableSeats method accessed for show");
		Show show = showService.getShow(showId);
		 final List<Seat> allSeats = show.getScreen().getSeats();
	        final List<Seat> unavailableSeats = getUnavailableSeats(show);
	        final List<Seat> availableSeats = new ArrayList<>(allSeats);
	        availableSeats.removeAll(unavailableSeats);
	        return availableSeats;
		
	}
    
    // This will create a confirm a booking for the particular booking i.e could use after the payment would be done
    @RequestMapping(method=RequestMethod.GET , value="/confirmbooking")
    public void confirmBooking(@RequestParam (name="BookingId") @NonNull final String BookingId , @RequestParam (name="User") @NonNull final String User) {
    	logger.trace("confirmBooking method accessed");
    	Booking booking = bookingService.getBooking(BookingId);
    	bookingService.confirmBooking(booking, User);

    }
    
    private List<Seat> getUnavailableSeats(@NonNull final Show show) {
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        return unavailableSeats;
    }
}

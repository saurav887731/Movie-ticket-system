package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Seat;
import com.example.demo.model.Show;

@Service
public class BookingService {
	
	Logger logger=	LoggerFactory.getLogger(BookingService.class);
	private final Map<String, Booking> showBookings;
	
	public BookingService()
	{
        this.showBookings = new HashMap<>();
	}
	
	// To get booking with respect to a booking id
    public Booking getBooking(@NonNull final String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
        	logger.error("Invalid bookingId:"+bookingId);
            throw new RuntimeException("BookingId not found");
        }
        return showBookings.get(bookingId);
    }
    
    // To get all the bookings for the particular show
    public List<Booking> getAllBookings(@NonNull final Show show) {
    	logger.trace("getAllBookings method with show:"+show);
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow().equals(show)) {
                response.add(booking);
            }
        }
        return response;
    }
    
    // To create a booking for a user for a show with the preferred seats
    public Booking createBooking(@NonNull final String userId, @NonNull final Show show,
                                 @NonNull final List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
        	logger.error("Seat is already booked");
            throw new RuntimeException("Seat permanently not available");
        }
        final String bookingId = UUID.randomUUID().toString();
        final Booking newBooking = new Booking(bookingId, show, userId, seats);
        showBookings.put(bookingId, newBooking);
        logger.info("Booking successfully created :"+bookingId);
        return newBooking;
    }

    // List of all the seats booked and booking status is confirmed
    public List<Seat> getBookedSeats(@NonNull final Show show) {
    	logger.trace("getBookedSeats Method accessed with show"+show);
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    
    // This method would confirm a booking for the particular user after all the payment and relevant checks are completed
    public void confirmBooking(@NonNull final Booking booking, @NonNull final String user) {
        if (!booking.getUser().equals(user)) {
        	logger.error("Invalid user :"+user);
            throw new RuntimeException("Bad Request Exception");
        }
        logger.info("Booking is confirmed");
        booking.confirmBooking();
    }

    // To check a seat is already booked for the particular seat
    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
    	logger.info("isAnySeatAlreadyBooked method accessed");
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }
}

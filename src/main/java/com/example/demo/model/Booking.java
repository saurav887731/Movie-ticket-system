package com.example.demo.model;

import java.util.List;

import org.springframework.lang.NonNull;


public class Booking {
	 	private final String id;
	    private final Show show;
	    private final List<Seat> seatsBooked; // This could be alter as per the requirement
	    private final String user;
	    private BookingStatus bookingStatus; // This could be changed if cancellation is the criteria and the seat would be available

	    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final String user,
	                   @NonNull final List<Seat> seatsBooked) {
	        this.id = id;
	        this.show = show;
	        this.seatsBooked = seatsBooked;
	        this.user = user;
	        this.bookingStatus = BookingStatus.Created;
	    }

	    public boolean isConfirmed() {
	        return this.bookingStatus == BookingStatus.Confirmed;
	    }
	    
	    // To confirm a Booking if the status of the booking is created 
	    public void confirmBooking() {
	        if (this.bookingStatus != BookingStatus.Created) {
	            throw new RuntimeException("Booking not created");
	        }
	        this.bookingStatus = BookingStatus.Confirmed;
	    }
	    
	    // To expire a Booking
	    public void expireBooking() {
	        if (this.bookingStatus != BookingStatus.Created) {
	        	throw new RuntimeException("Booking not created");
	        }
	        this.bookingStatus = BookingStatus.Expired;
	    }

	    // To get the status of the booking
		public BookingStatus getBookingStatus() {
			return bookingStatus;
		}

		public void setBookingStatus(BookingStatus bookingStatus) {
			this.bookingStatus = bookingStatus;
		}

		public String getId() {
			return id;
		}

		public Show getShow() {
			return show;
		}

		public List<Seat> getSeatsBooked() {
			return seatsBooked;
		}

		public String getUser() {
			return user;
		}

}

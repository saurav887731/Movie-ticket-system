package com.example.demo.model;

public class Seat {
	
	private String id;
    private int seatNo;
    
	public Seat(String id, int seatNo) {
		super();
		this.id = id;
		this.seatNo = seatNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	

}

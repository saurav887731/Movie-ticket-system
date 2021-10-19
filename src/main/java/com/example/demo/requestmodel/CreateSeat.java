package com.example.demo.requestmodel;

// Request model payload to create a seat
public class CreateSeat {
	private Integer seatNo;
	private String screenId;
	
	public Integer getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	

}

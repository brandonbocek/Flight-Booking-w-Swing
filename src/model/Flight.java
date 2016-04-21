package model;

public class Flight {
	
	private int numOfPassengers=0;
	private int totalNumOfSeats=200;
	private String destinationTo;
	private String destinationFrom;
	private String departureDate;
	
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public int getTotalNumOfSeats() {
		return totalNumOfSeats;
	}
	public void setTotalNumOfSeats(int totalNumOfSeats) {
		this.totalNumOfSeats = totalNumOfSeats;
	}
	public String getDestinationTo() {
		return destinationTo;
	}
	public void setDestinationTo(String destinationTo) {
		this.destinationTo = destinationTo;
	}
	public String getDestinationFrom() {
		return destinationFrom;
	}
	public void setDestinationFrom(String destinationFrom) {
		this.destinationFrom = destinationFrom;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

}

package model;

import java.util.ArrayList;

public class FlightModel {

	//many users can have many flights
	
	private ArrayList<Flight> allFlights = new ArrayList<Flight>();
	private ArrayList<User> allUsers = new ArrayList<User>();
	
	public void addAFlight(Flight flight){
		this.allFlights.add(flight);
	}
	public Flight getAFlight(int index){
		return this.allFlights.get(index);
	}
	
	public void addAUser(User user){
		this.allUsers.add(user);
	}
	public User getAUser(int index){
		return this.allUsers.get(index);
	}
	
	public ArrayList<Flight> getFlightsList(){
		return allFlights;
	}
	
}

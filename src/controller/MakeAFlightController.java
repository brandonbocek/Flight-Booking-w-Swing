package controller;

import model.Flight;
import model.FlightModel;
import view.FlightView;

//when the admin successfully makes a new flight, an object of this class is created and a method sets info
//into the main model's arraylist of flights
public class MakeAFlightController {

	Flight flight;
	FlightModel fm;
	FlightView view;
	
	public MakeAFlightController(Flight flight, FlightModel fm, FlightView view){
		this.flight=flight;
		this.fm=fm;
		this.view=view;
	}
	public void setFlightInfo(){
		flight.setDestinationFrom(view.getTravelFrom());
		flight.setDestinationTo(view.getTravelTo());
		flight.setDepartureDate(view.getTravelDate());
		fm.addAFlight(flight);
	}
	
}

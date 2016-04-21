package controller;

import model.FlightModel;
import view.FlightView;

public class FlightDemo {

	public static void main(String[] args) {
		FlightModel pass = new FlightModel();
		FlightView view = new FlightView();
		FlightController controller = new FlightController(pass, view);
	}

}

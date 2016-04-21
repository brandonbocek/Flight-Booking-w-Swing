package controller;

import model.User;
import view.FlightView;

//when a new user is registered, everything is set from the view to the user model
public class MakeAUserController {

	User user;
	FlightView view;
	
	public MakeAUserController(User user, FlightView view){
		this.user = user;
		this.view=view;
	}
	public void assignAttributesToUser(){
		user.setFirstName(view.getFirstName());
		user.setLastName(view.getLastName());
		user.setEmail(view.getEmail());
		user.setAge(view.getAge());
		user.setUserName(view.getUserName());
		user.setPassword(view.getPassword());
		
	}
}

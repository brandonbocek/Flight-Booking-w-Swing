package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Flight;
import model.FlightModel;
import model.User;
import view.FlightView;



public class FlightController {
	
	private FlightView theView;
	private FlightModel theModel;
	//
	private FlightModel aFlight = new FlightModel();
	public FlightController(FlightModel theModel, FlightView theView){
		this.theView=theView;
		this.theModel=theModel;
		
		//I create all the action listeners for the button up here, classes are below this constructor
		this.theView.addSubmitRegisterListener(new registerSubmitListener());
		this.theView.addSubmitLoginListener(new loginSubmitListener());
		this.theView.addSubmitCreateUserListener(new userCreationSubmitListener());
		this.theView.addGoBackToStartWindow(new goBackToStartSubmitListener());
		this.theView.addBookNewFlightListener(new seeAllFlightsListener());
		this.theView.addSubmitSelectedFlightListener(new addSelectedFlightListener());
		
		this.theView.addGoToAdminViewListener(new adminViewSubmitListener());
		this.theView.addNewFlightAsAdminListener(new addFlightAsAdminListener());
	}
	private void createAllFlights(){
		
	}
	
	//the action when register button is clicked
	class registerSubmitListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				//go to the register page when the button is clicked, pass nothing and just do it
					theView.goToRegisterPage();
				
			}catch(NullPointerException exc){
				theView.displayErrorMessage("Enter names in both fields");
			}	
		}
	}
	//the login button sends the user to the welcome page
	class loginSubmitListener implements ActionListener {
		//SendInfoController info = new SendInfoController();

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!blankUserName()){
				if(!blankPassword()){
					readSql rsql = new readSql();
					//getting 
					int id = rsql.validateLogin(theView.getUserName(), theView.getPassword());
					System.out.println(id);
					if(id==-1){	//the password is incorrect
						theView.displayErrorMessage("Password incorrect for this username");
					}else if(id==0){	//the username wasn't found
							theView.displayErrorMessage("Username does not exist");
							theView.goToRegisterPage();
					}else{	//the username and password matched
						String firstN = rsql.getFirstName(id);
							theView.goToSuccessLogin(firstN);	//getting the first name from the table based on the id num
					}
				}else{
					theView.displayErrorMessage("Enter your password");
				}
			}else{
				theView.displayErrorMessage("Enter your username");
			}
		}
		private boolean blankUserName(){
			if(theView.getUserName().equals("")){
				return true;
			}
			return false;
		}
		private boolean blankPassword(){
			if(theView.getPassword().equals("")){
				return true;
			}
			return false;
		}
	}
	//creates a user when clicked, so it adds all the info to the table if the info  was filled out correctly
	class userCreationSubmitListener implements ActionListener{
		/*
		 * When creating a new user, you can't have the same username or email as a previous user
		 * also none of the fields should be blank or combo box value set as default select
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!checkForRegistrationBlanks()){	//if everything is filled in and the passwords match
				readSql rsql = new readSql();	//check to see if username and email are unique to the table
				if(rsql.validateNewUser(theView.getUserName(), theView.getEmail())){
					writeUser wu = new writeUser(theView.getUserName(), theView.getPassword(), 
							theView.getEmail(), theView.getFirstName(), theView.getLastName(), theView.getAge());
					User someUser = new User();
					MakeAUserController muc = new MakeAUserController(someUser, theView);
					muc.assignAttributesToUser();
					theView.goToSuccessLogin(theView.getFirstName());
				}else{
					theView.displayErrorMessage("Sorry, username or email already taken");
				}
			}
			
		}
		//if the user didn't fill out the registration form correctly, returns true
		private boolean checkForRegistrationBlanks(){
			if(theView.getUserName().equals("")){
				theView.displayErrorMessage("Enter a new username for yourself");
				return true;
			}else if(theView.getPassword().equals("")){
				theView.displayErrorMessage("Enter a new password for yourself");
				return true;
			}else if(!theView.getRePassword().equals(theView.getPassword())){
				theView.displayErrorMessage("Passwords don't match");
				return true;
			}else if(theView.getFirstName().equals("")){
				theView.displayErrorMessage("Enter your first name");
				return true;
			}else if(theView.getLastName().equals("")){
				theView.displayErrorMessage("Enter your last name");
				return true;
			}else if(theView.getEmail().equals("")){
				theView.displayErrorMessage("Enter your email");
				return true;
			}else if(theView.getAge()==0){
				theView.displayErrorMessage("Select your age");
				return true;
			}else{
				return false;
			}
		}
		
	}
	//acts like a sign out button
	class goBackToStartSubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			theView.createInitialPanel();
		}
	}
	//takes the user to a display that shows a list of all the flights
	class seeAllFlightsListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			readSql userReader =new readSql();
			String[] allFlightsSoFar = userReader.getAllFlightsAsStringArray();
			theView.goToPickAFlightWindow(allFlightsSoFar);
		}
	}
	//when the user selects a flight to book they click the button that either adds them to a waiting list and tells them
	//in a separate jpanel or they go to a panel that shows the airline seats they can choose from
	//the seats are to be image Jbuttons
	class addSelectedFlightListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			readSql rs = new readSql();
			
			//theView.pickYourSeatWindow(rs.getSeatInfoFromCorrectFlight(theView.getFlightsToBook(), theModel));
			
		}
		
		
	}
	//takes you to the admin view, does it no matter what for now
	class adminViewSubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			readSql adminReader =new readSql();
			System.out.println(adminReader.getAllFlightsAsStringArray());
			String[] allFlightsSoFar = adminReader.getAllFlightsAsStringArray();
			theView.adminView(allFlightsSoFar);
			
		}
		
	}
	//the admin adds a new flight to the table of all_flights
	class addFlightAsAdminListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(adminFlightInputIsValid()){	//write the new flight to the table and then read and display all the flights
				Flight someFlight = new Flight();
				MakeAFlightController mfc = new MakeAFlightController(someFlight, theModel, theView);
				mfc.setFlightInfo();
				writeFlight wf = new writeFlight(theView.getTravelFrom(), theView.getTravelTo(), theView.getTravelDate());
				readSql adminReader =new readSql();
				String[] allFlightsSoFar = adminReader.getAllFlightsAsStringArray();
				theView.adminView(allFlightsSoFar);
			}
		}
		private boolean adminFlightInputIsValid(){
			if(theView.getTravelFrom().equals("")){
				theView.displayErrorMessage("Enter a city the flight is leaving from");
				return false;
			}else if(theView.getTravelTo().equals("")){
				theView.displayErrorMessage("Enter a city the flight is going to");
				return false;
			}else if(theView.getTravelDate().equals("")){
				theView.displayErrorMessage("Enter a (Month Day, Year) the flight is leaving");
				return false;
			}else{
				return true;
			}
		}
	}
}

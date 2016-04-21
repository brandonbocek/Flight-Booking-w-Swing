package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FlightView{

	//user creating their new booking profile
	private JTextField userName = new JTextField(10);
	private JTextField password = new JTextField(10);
	private JTextField rePassword = new JTextField(10);
	private JTextField firstName = new JTextField(10);
	private JTextField lastName = new JTextField(10);
	private JTextField email = new JTextField(30);
	
	private JLabel userNameLabel = new JLabel();
	private JLabel passwordLabel = new JLabel();
	private JLabel rePasswordLabel = new JLabel();
	private JLabel firstNameLabel = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel ageLabel = new JLabel();
	private JLabel emailLabel = new JLabel();
	
	//admin tools to create a new flight
	private JTextField leavingFrom = new JTextField(10);
	private JTextField goingTo = new JTextField(10);
	private JTextField departureDate = new JTextField(10);
	private JLabel leavingFromLabel = new JLabel("Starts at this city");
	private JLabel GoingToLabel = new JLabel("Lands at this city");
	private JLabel departureDateLabel = new JLabel("Leaves on this date");
	
	private String[] months = {"-select-","January","February","March","April","May","June","July","August","September","October","November","December"};
	private String[] travelDates = {"-select-","April 30, 2016","May 30, 2016"};
	
	private JComboBox age;
	private JComboBox flightsToBook;
	
	
	private String[] populateArray(String[] array, int startAt){
		array[0]="-select-";
		for(int i=0; i<(array.length)-1; i++){
			array[i+1] = String.valueOf(startAt+i);
		}
		return array;
	}
	private JButton signInAsAdminButton = new JButton("Administrator Sign In");
	private JButton addNewFlightAsAdminButton = new JButton("Create new Flight");
	private JButton goToRegisterButton = new JButton("New User");
	private JButton loginSubmitButton = new JButton("Login");
	private JButton newUserSubmitButton = new JButton("Finish Profile");
	private JButton goBackToInitialWindowButton = new JButton("Sign out");
	private JButton seeMyBookedFlightsButton = new JButton("View my flights");
	private JButton seeAllFlightsButton = new JButton("Book New Flight");
	private JButton addSelectedFlightButton = new JButton("Book this flight");
	
	JFrame jf = new JFrame();
	FlowLayout fL = new FlowLayout();
	
	//I guess the panels should be class variable so that different methods can create/remove them from the frame
	JPanel initialPanel = new JPanel(fL);
	JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel welcomePanel = new JPanel();
	JPanel flightsToBookPanel = new JPanel();
	JPanel tempPanel = new JPanel();
	JPanel pickYourSeatPanel = new JPanel();
	
	public JFrame getWindow(){
		return jf;
	}
	//constructor
	public FlightView(){
		createInitialPanel();
	}
	public void createInitialPanel(){
		//the first window the user sees, register or login
		jf.remove(adminPanel);
		jf.remove(registerPanel);
		jf.remove(welcomePanel);
		jf.remove(flightsToBookPanel);
		jf.remove(pickYourSeatPanel);
		jf.setVisible(false);
		fL.setAlignment(0);
		jf.setLayout(fL);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(700, 500);
		jf.setTitle("Welcome to Flight Booking");
		userNameLabel.setText("Username: ");
		passwordLabel.setText("Password: ");
		initialPanel.setPreferredSize(new Dimension(200, 300));
		initialPanel.add(userNameLabel);
		initialPanel.add(userName);
		initialPanel.add(userNameLabel);
		initialPanel.add(userName);
		initialPanel.add(passwordLabel);
		initialPanel.add(password);
		initialPanel.add(new JLabel());
		initialPanel.add(loginSubmitButton);
		initialPanel.add(new JLabel());
		initialPanel.add(new JLabel());
		initialPanel.add(new JLabel());
		initialPanel.add(new JLabel());
		initialPanel.add(new JLabel());
		initialPanel.add(goToRegisterButton);
		initialPanel.add(signInAsAdminButton);
		jf.add(initialPanel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	public void adminView(String[] allFlights){
		jf.remove(initialPanel);
		jf.remove(adminPanel);
		
		adminPanel.removeAll();
		adminPanel.setPreferredSize(new Dimension(600, 900));
		JLabel message = new JLabel();
		message.setText("Here are all the flights                                                                    ");
		adminPanel.add(message);
		for(int i=0; i<allFlights.length; i++){			//displaying the flights
			adminPanel.add(new JLabel(allFlights[i]));
		}
		adminPanel.add(new JLabel());
		adminPanel.add(new JLabel());
		adminPanel.add(leavingFromLabel);				//write in the flight details here
		adminPanel.add(leavingFrom);
		adminPanel.add(GoingToLabel);
		adminPanel.add(goingTo);
		adminPanel.add(departureDateLabel);
		adminPanel.add(departureDate);
		adminPanel.add(addNewFlightAsAdminButton);		//create the new flight button
		adminPanel.add(goBackToInitialWindowButton);	//leaving the admin view button
		jf.add(adminPanel);
		jf.setVisible(true);
	}
	//used for login and registering?
	public String getUserName(){
		return String.valueOf(userName.getText());
	}
	public String getPassword(){
		return String.valueOf(password.getText());
	}
	
	//used for registering
	public String getRePassword(){
		return String.valueOf(rePassword.getText());
	}
	public String getFirstName(){
		return String.valueOf(firstName.getText());
	}
	public String getLastName(){
		return String.valueOf(lastName.getText());
	}
	public String getEmail(){
		return String.valueOf(email.getText());
	}
	public int getAge(){
		try{
			return Integer.parseInt(age.getSelectedItem().toString());
		}catch(NumberFormatException exc){
			return 0;
		}
	}
	public String getFlightsToBook(){
		return String.valueOf(flightsToBook.getSelectedItem());
	}
	//admin getters
	public String getTravelFrom(){
		return String.valueOf(leavingFrom.getText());
	}
	public String getTravelTo(){
		return String.valueOf(goingTo.getText());
	}
	public String getTravelDate(){
		return String.valueOf(departureDate.getText());
	}
	//actions
	public void addSubmitRegisterListener(ActionListener listenForSubmitInfo){
		goToRegisterButton.addActionListener(listenForSubmitInfo);
	}
	public void addSubmitLoginListener(ActionListener listenForSubmitInfo){
		loginSubmitButton.addActionListener(listenForSubmitInfo);
	}
	public void addSubmitCreateUserListener(ActionListener listenForSubmitInfo){
		newUserSubmitButton.addActionListener(listenForSubmitInfo);
	}
	public void addGoBackToStartWindow(ActionListener listenForSubmitInfo){
		goBackToInitialWindowButton.addActionListener(listenForSubmitInfo);
	}
	public void addBookNewFlightListener(ActionListener listenForSubmitInfo){
		seeAllFlightsButton.addActionListener(listenForSubmitInfo);
	}
	public void addSubmitSeeUserFlightsListener(ActionListener listenForSubmitInfo){
		seeMyBookedFlightsButton.addActionListener(listenForSubmitInfo);
	}
	public void addSubmitSelectedFlightListener(ActionListener listenForSubmitInfo){
		addSelectedFlightButton.addActionListener(listenForSubmitInfo);
	}
	public void addGoToAdminViewListener(ActionListener listenForSubmitInfo){
		signInAsAdminButton.addActionListener(listenForSubmitInfo);
	}
	public void addNewFlightAsAdminListener(ActionListener listenForSubmitInfo){
		addNewFlightAsAdminButton.addActionListener(listenForSubmitInfo);
	}
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(jf, errorMessage);
	}
	
	//the register page
	public void goToRegisterPage(){
		jf.remove(initialPanel);
		jf.setVisible(false);
		
		JLabel message = new JLabel();
		message.setText("To register a new account enter your info below                                                           ");
		
		//all the stuff in the new user registration window
		String[] days = new String[32];
		days = populateArray(days,1);
		String[] birthYears = new String[100];
		birthYears = populateArray(birthYears, 1917);
		String[] ages = new String[100];
		ages = populateArray(ages, 0);
		age = new JComboBox(ages);
		
		userNameLabel.setText("Enter a User Name: ");
		passwordLabel.setText("Enter a Password: ");
		rePasswordLabel.setText("Enter the Password Again: ");
		firstNameLabel.setText("First Name: ");
		lastNameLabel.setText("Last Name: ");
		emailLabel.setText("Email Address");
		ageLabel.setText("Age: ");
		registerPanel.setPreferredSize(new Dimension(600, 500));
		
		registerPanel.add(message);
		registerPanel.add(userNameLabel);
		registerPanel.add(userName);
		registerPanel.add(passwordLabel);
		registerPanel.add(password);
		registerPanel.add(rePasswordLabel);
		registerPanel.add(rePassword);
		registerPanel.add(firstNameLabel);
		registerPanel.add(firstName);
		registerPanel.add(lastNameLabel);
		registerPanel.add(lastName);
		registerPanel.add(emailLabel);
		registerPanel.add(email);
		registerPanel.add(ageLabel);
		registerPanel.add(age);
		registerPanel.add(newUserSubmitButton);
		registerPanel.add(goBackToInitialWindowButton);
		
		jf.add(registerPanel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	
	//after the user inputs a correct user name and matching password, goes to this screen
	public void goToSuccessLogin(String first_name){
		jf.remove(registerPanel);
		jf.remove(initialPanel);
		jf.setVisible(false);

		/*
		 * Add a list of flights to join
		 * Also display flights you are in and any you're on a waiting list for
		 * It will probably be more complex than just squeezing it all in this method
		 */
		
		JLabel message = new JLabel();
		message.setText("Welcome "+first_name);
		welcomePanel.add(message);
		welcomePanel.add(goBackToInitialWindowButton);
		welcomePanel.add(seeMyBookedFlightsButton);
		welcomePanel.add(seeAllFlightsButton);
		jf.add(welcomePanel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	//a view that displays a list of flights to choose and a select button
	public void goToPickAFlightWindow(String[] flights){
		jf.remove(welcomePanel);
		jf.setVisible(false);
		JLabel message = new JLabel();
		message.setText("Please select a flight and submit it.");
		flightsToBook = new JComboBox(flights);
		flightsToBookPanel.add(flightsToBook);
		flightsToBookPanel.add(addSelectedFlightButton);
		flightsToBookPanel.add(goBackToInitialWindowButton);
		jf.add(flightsToBookPanel);
		jf.setVisible(true);
	}
	//the view with all the seats either red or blue
	public void pickYourSeatWindow(String seatInfo){
		jf.remove(flightsToBookPanel);
		jf.setVisible(false);
		JLabel message = new JLabel();
		message.setText("Please select your seat");
		BufferedImage takenSeat=null;
		BufferedImage openSeat=null;
		try {
			takenSeat = ImageIO.read(new File("/Users/brandonbocek/Documents/workspace/Flight/images/chair.jpeg"));
			openSeat = ImageIO.read(new File("/Users/brandonbocek/Documents/workspace/Flight/images/openChair.jpeg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton takenButton = new JButton(new ImageIcon(takenSeat));
		JButton openButton = new JButton(new ImageIcon(openSeat));
		pickYourSeatPanel.add(message);
		//change this later?
		for(int i=0; i<10; i++){}
			pickYourSeatPanel.add(new JButton(new ImageIcon(takenSeat)));
			pickYourSeatPanel.add(new JButton(new ImageIcon(openSeat)));
			pickYourSeatPanel.add(new JButton(new ImageIcon(takenSeat)));
			pickYourSeatPanel.add(new JButton(new ImageIcon(openSeat)));
		
		
		pickYourSeatPanel.add(goBackToInitialWindowButton);
		jf.add(pickYourSeatPanel);
		jf.setVisible(true);
		
	}
	
}

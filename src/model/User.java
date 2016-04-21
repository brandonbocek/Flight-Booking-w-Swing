package model;

import java.util.ArrayList;

public class User {

	/*String firstName, String lastName,
	int age, int date_of_birth, String dest_to, 
	String dest_from, int date_of_travel*/
	
	private ArrayList<Flight> flightsBooked = new ArrayList<Flight>();
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private int age;
	private String dateOfBirth;
	private String goingTo;
	private String startingAt;
	private String travelDate;
	
	private int birthDay;
	private String birthMonth;
	private int birthYear;
	
	private boolean wasCreated=false;
	private boolean onWaitingList;
	private boolean confirmed;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public String getGoingTo() {
		return goingTo;
	}
	public void setGoingTo(String goingTo) {
		this.goingTo = goingTo;
	}
	public String getStartingAt() {
		return startingAt;
	}
	public void setStartingAt(String startingAt) {
		this.startingAt = startingAt;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public boolean isOnWaitingList() {
		return onWaitingList;
	}
	public void setOnWaitingList(boolean onWaitingList) {
		this.onWaitingList = onWaitingList;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public boolean isWasCreated() {
		return wasCreated;
	}
	public void setWasCreated(boolean wasCreated) {
		this.wasCreated = wasCreated;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Flight getBookedFlight(int i) {
		return flightsBooked.get(i);
	}
	public void addNewBookedFlight(Flight flight) {
		this.flightsBooked.add(flight);
	}
}

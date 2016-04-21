package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import model.User;

public class SendInfoController {
	
	public boolean userIsNew(User person){
		readSql rsql = new readSql();
		return rsql.validateNewUser(person.getUserName(), person.getEmail());
	}
	
	public void sendToFile(User person){
		
		/*writeFlight wsql = new writeFlight(person.getFirstName(), person.getLastName(), person.getAge());*/
		
		
	}
	
}

package sailingclub.client.gui.controllers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sailingclub.common.structures.User;

public class EmployeeGuiController {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
		System.out.println(this.loggedUser.getUsername());
	}
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}
}

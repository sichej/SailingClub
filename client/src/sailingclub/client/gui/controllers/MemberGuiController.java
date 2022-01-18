package sailingclub.client.gui.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.User;

public class MemberGuiController {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
		System.out.println(this.loggedUser.getUsername());
		System.out.println(this.loggedUser.getMembershipFee());
	}
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
		
		Boat bt = new Boat(3);
		Response rs = null;
    	/*try {
			out.writeObject(new Request(Constants.INSERT, bt));
			rs = (Response)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/
		
		try {
			out.writeObject(new Request(Constants.DELETE, bt));
			rs = (Response)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());
	}
}

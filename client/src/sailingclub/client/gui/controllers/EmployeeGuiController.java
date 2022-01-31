package sailingclub.client.gui.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.CreditCard;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.User;

public class EmployeeGuiController {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	
	private Map<String, String> btnTabAssoc;
	
	@FXML private AnchorPane tabUserManagment;
	@FXML private Button btnSelectUser;
	@FXML private ComboBox<Object> cmBoxUsers;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.btnTabAssoc = new HashMap<String,String>();
		this.btnTabAssoc.put("tabUserManagment", "tabUserManagment");
	}
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
		System.out.println(this.loggedUser.getUsername());
	}
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}
	
	@SuppressWarnings("unchecked")
	private void getMembersList() throws IOException, ClassNotFoundException {
		out.writeObject(new Request(Constants.GET_MEMBERS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	ArrayList<String> members = (ArrayList<String>)r.getPayload();
    	
    	this.cmBoxUsers.getItems().clear();
    	
    	for(int i = 0; i < members.size(); i++)
    		this.cmBoxUsers.getItems().add(members.get(i));
	}
	
	
	public void onBtnSelectUser(ActionEvent event) throws Exception {
		getMembersList();
	}
}

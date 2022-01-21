package sailingclub.client.gui.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.User;

public class LoginGuiController{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	
	@FXML private Button btnLogin;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}
	
	public void OnBtnLoginClick(ActionEvent event) throws Exception {
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();
		
		out.writeObject(new Request(Constants.LOGIN, new User(username, Utils.stringToDigest(password))));
    	Response r = (Response)in.readObject();
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
    		String gui = "";
    		this.loggedUser = (User)r.getPayload();
    		
    		if(this.loggedUser.getUserType().equals("member")) gui = "../fxml/MemberGui.fxml";
    		else if(this.loggedUser.getUserType().equals("employee")) gui = "../fxml/EmployeeGui.fxml";
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(gui));
    		Parent userGui = loader.load();
    		Object controller = loader.getController();
    		
    		if(this.loggedUser.getUserType().equals("member")) {
    			((MemberGuiController)controller).setStreams(this.out, this.in);
    			((MemberGuiController)controller).setLoggedUser(this.loggedUser);
    		} else if(this.loggedUser.getUserType().equals("employee")) {
    			((EmployeeGuiController)controller).setStreams(this.out, this.in);
    			((EmployeeGuiController)controller).setLoggedUser(this.loggedUser);
    		}
    		
    		Scene scene = new Scene(userGui);
    		scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.setTitle(this.loggedUser.getUsername() + " - DASHBOARD'S");
    		stage.setScene(scene);
    		stage.centerOnScreen();
    		stage.show();
    	}else if(r.getStatusCode() == Constants.BAD_REQUEST) {
    		System.out.println("WRONG USER OR PASSWORD " + r.getPayload());
    	}else if(r.getStatusCode() == Constants.INTERNAL_SERVER_ERROR) {
    		System.out.println("INTERNAL SERVER ERROR!");
    	}
	}
}

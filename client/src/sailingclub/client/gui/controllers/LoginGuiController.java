package sailingclub.client.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sailingclub.client.RequestController;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.User;

public class LoginGuiController{
	private RequestController requestController;
	private User loggedUser;
	
	@FXML private Button btnLogin;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblStatus;
	
	public void setRequestController(RequestController controller) {
		this.requestController = controller;
	}
	
	public void setLoggedUser(User user) throws Exception{
		this.loggedUser = user;
	}
		
	public void OnBtnLoginClick(ActionEvent event) throws Exception {
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();
		this.lblStatus.setText("");
    	Response r = this.requestController.makeRequest(Constants.LOGIN, new User(username, Utils.stringToDigest(password)));
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
    		String gui = "";
    		loggedUser = (User)r.getPayload();
    		
    		if(loggedUser.getUserType().equals("member")) gui = "../fxml/MemberGui.fxml";
    		else if(loggedUser.getUserType().equals("employee")) gui = "../fxml/EmployeeGui.fxml";
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(gui));
    		Parent userGui = loader.load();
    		Object controller = loader.getController();
    		Scene scene = new Scene(userGui);
    		scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		
    		if(loggedUser.getUserType().equals("member")) {
    			((MemberGuiController)controller).setRequestController(this.requestController);
    			((MemberGuiController)controller).setLoggedUser(loggedUser);
    		} else if(loggedUser.getUserType().equals("employee")) {
    			((EmployeeGuiController)controller).setRequestController(this.requestController);
    			((EmployeeGuiController)controller).setLoggedUser(loggedUser);
    		}
    		
    		stage.setTitle(loggedUser.getUsername() + " - DASHBOARD'S");
    		stage.setScene(scene);
    		stage.centerOnScreen();
    		stage.show();
    		
    		if(loggedUser.getUserType().equals("member")) ((MemberGuiController)controller).onStageShow();
    		else if(loggedUser.getUserType().equals("employee")) ((EmployeeGuiController)controller).onStageShow();
    	}else if(r.getStatusCode() == Constants.BAD_REQUEST) {
    		this.lblStatus.setText("Wrong username or password!");
    	}else if(r.getStatusCode() == Constants.INTERNAL_SERVER_ERROR) {
    		this.lblStatus.setText("Server error!");
    	}
	}
}

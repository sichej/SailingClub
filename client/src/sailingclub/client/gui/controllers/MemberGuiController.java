package sailingclub.client.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.User;
import java.util.ArrayList;

public class MemberGuiController implements Initializable{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	private ArrayList<Boat> boats;
	private Map<String, String> btnTabAssoc;
	
	@FXML Button btnToggleMenu;
	@FXML VBox vbMenu;
	@FXML ImageView imgBtnToggleMenu;
	@FXML Button btnProfileManagment;
	@FXML Button btnBoatsManagment;
	@FXML Button btnRaceManagment;
	@FXML StackPane stckBody;
	@FXML AnchorPane tabProfileManagment;
	@FXML AnchorPane tabBoatsManagment;
	@FXML AnchorPane tabRaceManagment;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		vbMenu.setVisible(false);
		this.btnTabAssoc = new HashMap<String,String>();
		this.btnTabAssoc.put("btnProfileManagment", "tabProfileManagment");
		this.btnTabAssoc.put("btnBoatsManagment", "tabBoatsManagment");
		this.btnTabAssoc.put("btnRaceManagment", "tabRaceManagment");
	}
	
	public void setLoggedUser(User user) throws Exception {
		this.loggedUser = user;
		//getBoats();
		System.out.println(this.loggedUser.getUsername());
		System.out.println(this.loggedUser.getMembershipFee());
	}
	
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
		
		Boat bt = new Boat(3);
		Response rs = null;
	}
	
	public void OnBtnToggleMenuClick(ActionEvent event) throws IOException {
		
		if(this.vbMenu.isVisible()) {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
			vbMenu.setVisible(false);
		}
		else {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_opened.png"));
			vbMenu.setVisible(true);
		}
		
	}
	
	public void OnBtnMenuClick(ActionEvent event) throws Exception {
		// when a member selects a specific menu option, automatically close the meu to show the full application screen
		imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		vbMenu.setVisible(false);
		
		String pressedBtn = ((Button) event.getSource()).getId();
		String tab = this.btnTabAssoc.get(pressedBtn);
		
		if (tab.toString() == "tabBoatsManagment") {
			getBoats();
		}
		
		ObservableList<Node> tabs = FXCollections.observableArrayList(this.stckBody.getChildren());
		
		int index;
		for(index = 0; index < tabs.size(); index++)
			if(tabs.get(index).getId().equals(tab)) break;
			
		Collections.swap(tabs, index, tabs.size() - 1);
		this.stckBody.getChildren().setAll(tabs);
		
	}
	
	private void getBoats() throws Exception {
		out.writeObject(new Request(Constants.GET_BOATS, loggedUser));
    	Response r = (Response)in.readObject();
    	//System.out.print("ciao");
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
    		// print boats
    		boats = new ArrayList<Boat>();
    		boats = ((ArrayList<Boat>)r.getPayload());
    		for(int i = 0; i < boats.size(); i++) {
    			System.out.println(boats.get(i).getName());
    		}
    		
    	}
	}
}

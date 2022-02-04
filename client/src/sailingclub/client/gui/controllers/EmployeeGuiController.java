package sailingclub.client.gui.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sailingclub.client.gui.fxml.PaymentModel;
import sailingclub.client.gui.fxml.RaceModel;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Payment;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;

public class EmployeeGuiController implements Initializable{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	private User userToManage = null;
	private ObservableList<RaceModel> raceModels;
	private ObservableList<PaymentModel> paymentsModels;
	
	@FXML private ComboBox<String> cmbSelectedUser;
	@FXML private TableView<RaceModel> tblRaces;
	@FXML private TableColumn<RaceModel, Integer> colRaceId;
	@FXML private TableColumn<RaceModel, String> colRaceName;
	@FXML private TableColumn<RaceModel, LocalDate> colRaceDate;
	@FXML private TableColumn<RaceModel, Double> colRacePrice;
	@FXML private TableColumn<RaceModel, Button> colRaceAction;
	@FXML private TableColumn<RaceModel, Integer> colSubscriptions;
	@FXML private TableView<PaymentModel> tblPayment;
	@FXML private TableColumn<PaymentModel, Double> colPaymentsAmount;
	@FXML private TableColumn<PaymentModel, String> colPaymentsFrom;
	@FXML private TableColumn<PaymentModel, String> colPaymentsMethod;
	@FXML private TableColumn<PaymentModel, String> colPaymentsDetails;
	@FXML private TableColumn<PaymentModel, LocalDate> colPaymentsDate;
	@FXML private TableColumn<PaymentModel, String> colPaymentsPurpose;
	@FXML private TextField txtRaceName;
	@FXML private DatePicker dtpRaceDate;
	@FXML private Spinner<Double> spnRacePrice;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cmbSelectedUser.setVisible(false);
		this.cmbSelectedUser.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			this.onCmbSelectedUserSelectionChanged(newValue);
		}); 
		
		this.colRaceId.setCellValueFactory(new PropertyValueFactory<RaceModel,Integer>("raceId"));
		this.colRaceName.setCellValueFactory(new PropertyValueFactory<RaceModel,String>("raceName"));
		this.colRaceDate.setCellValueFactory(new PropertyValueFactory<RaceModel,LocalDate>("raceDate"));
		this.colRacePrice.setCellValueFactory(new PropertyValueFactory<RaceModel,Double>("racePrice"));
		this.colRaceAction.setCellValueFactory(new PropertyValueFactory<RaceModel,Button>("btnAction"));
		this.colSubscriptions.setCellValueFactory(new PropertyValueFactory<RaceModel,Integer>("subs"));
		
		this.colPaymentsAmount.setCellValueFactory(new PropertyValueFactory<PaymentModel,Double>("amount"));
		this.colPaymentsFrom.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("memberId"));
		this.colPaymentsMethod.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("method"));
		this.colPaymentsDetails.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("details"));
		this.colPaymentsDate.setCellValueFactory(new PropertyValueFactory<PaymentModel,LocalDate>("date"));
		this.colPaymentsPurpose.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("purpose"));
		
		SpinnerValueFactory<Double> spnFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(50, 99999999, 10);
		this.spnRacePrice.setValueFactory(spnFactory);
	}
	
	public void onTabRacesSelected() {
		if(this.cmbSelectedUser == null) return;
		
		this.cmbSelectedUser.setVisible(false);
		try {
			this.displayRaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTabMembersSelected() {
		this.cmbSelectedUser.setVisible(true);
	}
	
	public void onTabPaymentsTrackingSelected() {
		this.cmbSelectedUser.setVisible(false);
		try {
			this.displayPayments();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLoggedUser(User user) throws Exception{
		this.loggedUser = user;
	}
	
	public void onBtnLogoutClick(ActionEvent event) throws Exception {
		out.writeObject(new Request(Constants.LOGOUT, new EmptyPayload()));
    	Response rs = (Response)in.readObject();
		if(rs.getStatusCode() != Constants.SUCCESS) return;
			
		setLoggedUser(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/LoginGui.fxml"));
		Parent userGui = loader.load();
		Object controller = loader.getController();
		((LoginGuiController)controller).setLoggedUser(null);
		((LoginGuiController)controller).setStreams(out, in);
		Scene scene = new Scene(userGui);
		scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	public void onBtnAddRaceClick(ActionEvent event) throws Exception {
		String rName = this.txtRaceName.getText();
		LocalDate rDate = this.dtpRaceDate.getValue();
		double rPrice = Double.parseDouble(this.spnRacePrice.getEditor().getText());
		out.writeObject(new Request(Constants.INSERT, new Race(rDate,rPrice,rName)));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	this.txtRaceName.setText("");
    	this.dtpRaceDate.setValue(null);
    	this.spnRacePrice.getEditor().setText("50");
    	this.displayRaces();
	}

	public void setStreams(ObjectOutputStream out, ObjectInputStream in)  {
		this.out = out;
		this.in = in;
	}
	
	@SuppressWarnings("unchecked")
	public void onStageShow() throws Exception {
		out.writeObject(new Request(Constants.GET_MEMBERS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<String> members = (ArrayList<String>)r.getPayload();
		for(String u: members) this.cmbSelectedUser.getItems().add(u);
		cmbSelectedUser.getSelectionModel().selectFirst();
		this.cmbSelectedUser.setVisible(false);
		
		this.displayRaces();
	}
	
	private void onCmbSelectedUserSelectionChanged(String selectedUser) {
		try {
			out.writeObject(new Request(Constants.GET_MEMBER_BY_USERNAME, selectedUser));
	    	Response r = (Response)in.readObject();
	    	if(r.getStatusCode() != Constants.SUCCESS) return;
			this.userToManage = (User)r.getPayload();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onBtnRaceActionClick(Button btn, Race race)  {
		try {
			out.writeObject(new Request(Constants.DELETE, race));
	    	Response r = (Response)in.readObject();
	    	if(r.getStatusCode() != Constants.SUCCESS) return;
	    	
	    	this.displayRaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void displayPayments() throws Exception{
		out.writeObject(new Request(Constants.GET_PAYMENTS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	System.out.println(r.getStatusCode());
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	ArrayList<Payment> payments = (ArrayList<Payment>)r.getPayload();
    	this.paymentsModels = FXCollections.observableArrayList();
    	
    	for(Payment p: payments) {
    		this.paymentsModels.add(new PaymentModel(p));
    	}
    	
    	this.tblPayment.setItems(paymentsModels);
	}
	
	@SuppressWarnings("unchecked")
	private void displayRaces() throws Exception {
		out.writeObject(new Request(Constants.GET_RACES, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	ArrayList<Race> allRaces = (ArrayList<Race>)r.getPayload();
    	
		this.raceModels = FXCollections.observableArrayList();
		
		for(int i = 0; i < allRaces.size(); i++) {
			Button btnAction = new Button();
			btnAction.setId(Integer.toString(allRaces.get(i).getId()));
			btnAction.setText("Remove");
			final Race race = allRaces.get(i);
			btnAction.setOnAction(event -> onBtnRaceActionClick(btnAction, race));
						
			if(allRaces.get(i).getDate().isBefore(LocalDate.now())) {  //se gara finita
				btnAction.setText("Race ended\nRemove");
			}
			
			out.writeObject(new Request(Constants.GET_RACES_SUB, allRaces.get(i).getId().toString()));
	    	r = (Response)in.readObject();
			
			this.raceModels.add(new RaceModel(allRaces.get(i),btnAction,Integer.parseInt((String)r.getPayload())));
		}
		this.tblRaces.setItems(raceModels);
	}
}

package sailingclub.client.gui.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sailingclub.client.RequestController;
import sailingclub.client.gui.fxml.BoatModel;
import sailingclub.client.gui.fxml.PaymentModel;
import sailingclub.client.gui.fxml.RaceModel;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.*;

/**
 * Is the controller for the Employee gui, contains all the handlers
 * that manage all the javafx components
 * @see sailingclub.client.gui.fxml.EmployeeGui
 */
public class EmployeeGuiController implements Initializable{
	private final double BTN_NOTIFY_MAX_W = 50;
	private RequestController requestController;
	private User userFilter;
	private Race selectedRace;
	private Boat selectedBoat;
	private User loggedUser;
	private ObservableList<RaceModel> raceModels;
	private ObservableList<PaymentModel> paymentsModels;
	private ObservableList<BoatModel> boatsModels;
	private ObservableList<BoatModel> boatsMemberModels;
	
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
	@FXML private Label lblSelectedRace;
	@FXML private TableView<BoatModel> tblBoats;
	@FXML private TableColumn<BoatModel, Integer> colBoatId;
	@FXML private TableColumn<BoatModel, String> colBoatName;
	@FXML private TableColumn<BoatModel, Double> colBoatLength;
	@FXML private TableColumn<BoatModel, String> colBoatOwner;
	@FXML private TableColumn<BoatModel, LocalDate> colBoatFeeExpDate;
	@FXML private TableColumn<BoatModel, Double> colBoatFeeAmount;
	@FXML private TextField txtBoatName;
	@FXML private Spinner<Double> spnBoatLength;
	@FXML private DatePicker dtpBoatFeeExpDate;
	@FXML private Spinner<Double> spnBoatFeeAmount;
	@FXML private Label lblSelectedBoat;
	@FXML private TableView<BoatModel> tblMemberBoatNotifications;
	@FXML private TableColumn<BoatModel, String> colMemberBoatName;
	@FXML private TableColumn<BoatModel, LocalDate> colMemberBoatFee;
	@FXML private TableColumn<BoatModel, Button> colMemberBoatNotify;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtNewPassword;
	@FXML private TextField txtName;
	@FXML private TextField txtSurname;
	@FXML private TextField txtAddress;
	@FXML private TextField txtFiscalCode;
	@FXML private Label lblSelectedMember;
	@FXML private DatePicker dtpMembershipFeeDate;
	@FXML private Spinner<Double> spnMembershipFeePrice;
	@FXML private Button btnUpdateRace;
	@FXML private Button btnAddRace;
	@FXML private Button btnMemberNotification;
	@FXML private Button btnUpdateMember;
	@FXML private Button btnAddMember;
	@FXML private Button btnDeleteMember;
	@FXML private Button btnUpdateBoat;
	@FXML private Button btnLogout;
	
	/**
	 * the initialization method of the class
	 * @Override
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		raceModels = FXCollections.observableArrayList();
		paymentsModels = FXCollections.observableArrayList();
		boatsModels = FXCollections.observableArrayList();
		boatsMemberModels = FXCollections.observableArrayList();
		
		this.cmbSelectedUser.setVisible(false);
		this.cmbSelectedUser.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			if(newValue!= null) this.onCmbSelectedUserSelectionChanged(newValue);
    		this.btnMemberNotification.setDisable(false);
    		this.btnUpdateMember.setDisable(false);
    		this.btnAddMember.setDisable(true);
            this.btnDeleteMember.setDisable(false);
		}); 
		
		this.colRaceId.setCellValueFactory(new PropertyValueFactory<RaceModel,Integer>("raceId"));
		this.colRaceName.setCellValueFactory(new PropertyValueFactory<RaceModel,String>("raceName"));
		this.colRaceDate.setCellValueFactory(new PropertyValueFactory<RaceModel,LocalDate>("raceDate"));
		this.colRacePrice.setCellValueFactory(new PropertyValueFactory<RaceModel,Double>("racePrice"));
		this.colRaceAction.setCellValueFactory(new PropertyValueFactory<RaceModel,Button>("btnAction"));
		this.colSubscriptions.setCellValueFactory(new PropertyValueFactory<RaceModel,Integer>("subs"));
		this.tblRaces.setRowFactory( tv -> {
		    TableRow<RaceModel> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	RaceModel rowData = row.getItem();
		            this.txtRaceName.setText(rowData.getRaceName());
		            this.spnRacePrice.getEditor().setText(Double.toString(rowData.getRacePrice()));
		            this.dtpRaceDate.setValue(rowData.getRaceDate());
		            this.lblSelectedRace.setText("Selected race id: " + rowData.getRaceId());
		            this.selectedRace = new Race(rowData.getRaceId(),rowData.getRaceDate(), rowData.getRacePrice(), rowData.getRaceName());
		            this.btnUpdateRace.setDisable(false);
		            this.btnAddRace.setDisable(true);
		        }
		    });
		    return row ;
		});
		
		this.colBoatId.setCellValueFactory(new PropertyValueFactory<BoatModel,Integer>("boatId"));
		this.colBoatName.setCellValueFactory(new PropertyValueFactory<BoatModel,String>("boatName"));
		this.colBoatLength.setCellValueFactory(new PropertyValueFactory<BoatModel,Double>("boatLength"));
		this.colBoatFeeExpDate.setCellValueFactory(new PropertyValueFactory<BoatModel,LocalDate>("boatFeeExpDate"));
		this.colBoatFeeAmount.setCellValueFactory(new PropertyValueFactory<BoatModel,Double>("boatFeeAmount"));
		this.colBoatOwner.setCellValueFactory(new PropertyValueFactory<BoatModel,String>("boatOwner"));
		this.tblBoats.setRowFactory( tv -> {
		    TableRow<BoatModel> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (!row.isEmpty()) {
		        	BoatModel rowData = row.getItem();
		        	txtBoatName.setText(rowData.getBoatName());
		        	spnBoatLength.getEditor().setText(Double.toString(rowData.getBoatLength()));
		        	dtpBoatFeeExpDate.setValue(rowData.getBoatFeeExpDate());
		        	spnBoatFeeAmount.getEditor().setText(Double.toString(rowData.getBoatFeeAmount()));
		        	lblSelectedBoat.setText("Selected boat id: " + rowData.getBoatId());
		        	this.selectedBoat = new Boat(rowData.getBoatId());
		        	this.btnUpdateBoat.setDisable(false);
		        }
		    });
		    return row ;
		});
		
		this.colPaymentsAmount.setCellValueFactory(new PropertyValueFactory<PaymentModel,Double>("amount"));
		this.colPaymentsFrom.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("memberId"));
		this.colPaymentsMethod.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("method"));
		this.colPaymentsDetails.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("details"));
		this.colPaymentsDate.setCellValueFactory(new PropertyValueFactory<PaymentModel,LocalDate>("date"));
		this.colPaymentsPurpose.setCellValueFactory(new PropertyValueFactory<PaymentModel,String>("purpose"));

		this.colMemberBoatFee.setCellValueFactory(new PropertyValueFactory<BoatModel,LocalDate>("boatFeeExpDate"));
		this.colMemberBoatName.setCellValueFactory(new PropertyValueFactory<BoatModel,String>("boatName"));
		this.colMemberBoatNotify.setCellValueFactory(new PropertyValueFactory<BoatModel,Button>("btnNotify"));
		
		this.spnRacePrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(50, 99999999, 10));
		this.spnBoatLength.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 99999999, 10));
		this.spnBoatFeeAmount.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10, 99999999, 10));
		this.spnMembershipFeePrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10, 99999999, 10));
	}
	
	/**
	 * handles the click on the races tab of the employee gui
	 */
	public void onTabRacesSelected() {
		if(this.cmbSelectedUser == null) return;
		this.cmbSelectedUser.setVisible(false);
	}
	
	/**
	 * handles the click on the Member tab of the employee gui
	 */
	public void onTabMembersSelected() {
		this.cmbSelectedUser.setVisible(true);
	}
	
	/**
	 * handles the click on the payments tab of the employee gui
	 */
	public void onTabPaymentsTrackingSelected() {
		this.cmbSelectedUser.setVisible(false);
	}
	
	/**
	 * handles the click on the boats tab of the employee gui
	 */
	public void onTabBoatsSelected() {
		this.cmbSelectedUser.setVisible(false);
	}
	
	/**
	 * allows to pass the logged user between scenes
	 * @param user the logged {@code User}
	 * @throws Exception
	 */
	public void setLoggedUser(User user) throws Exception{
		this.loggedUser = user;
		this.btnLogout.setText(this.loggedUser.getUsername() + " - Logout");
	}
	
	/**
	 * handles the logout button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnLogoutClick(ActionEvent event) throws Exception {
    	Response rs = this.requestController.makeRequest(Constants.LOGOUT, new EmptyPayload());
		if(rs.getStatusCode() != Constants.SUCCESS) return;
			
		this.loggedUser = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/LoginGui.fxml"));
		Parent userGui = loader.load();
		Object controller = loader.getController();
		((LoginGuiController)controller).setLoggedUser(null);
		((LoginGuiController)controller).setRequestController(this.requestController);
		Scene scene = new Scene(userGui);
		scene.getStylesheets().add("sailingclub/client/gui/css/custom.css");
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	/**
	 * handles the update member button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnUpdateMemberClick(ActionEvent event) throws Exception {
		if(this.txtUsername.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a valid username!");
			alert.showAndWait();
			return;
		}
				
		MembershipFee fee = new MembershipFee(this.userFilter.getMembershipFee().getPaymentDate(),dtpMembershipFeeDate.getValue(),
				this.spnMembershipFeePrice.getValue(), this.txtUsername.getText());
		User upUser = new User(this.userFilter.getUsername() + "," + this.txtUsername.getText(), this.txtName.getText(), this.txtSurname.getText(), 
                this.txtAddress.getText(), this.txtFiscalCode.getText(), "member", Utils.stringToDigest(this.txtNewPassword.getText()), fee);
    	Response r = this.requestController.makeRequest(Constants.UPDATE_MEMBER, upUser);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	this.onBtnClearMemberClick(null);
    	this.fillUserCmbFilters();
    	this.displayBoats();
    	this.displayPayments();
	}
	
	/**
	 * handles the delete member button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnDeleteMemberClick(ActionEvent event) throws Exception {
    	Response r = this.requestController.makeRequest(Constants.DELETE, this.userFilter);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	this.onBtnClearMemberClick(null);
    	this.fillUserCmbFilters();
	}
	
	/**
	 * handles the add member button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnAddMemberClick(ActionEvent event) throws Exception {
		if(this.txtUsername.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a valid username!");
			alert.showAndWait();
			return;
		}
		
		for(String user: this.cmbSelectedUser.getItems()) {
			if(this.userFilter.getUsername().equals(user)) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning alert");
				alert.setHeaderText("Invilid values");
				alert.setContentText("Username already exists!");
				alert.showAndWait();
				return;
			}
		}
		
		MembershipFee fee = new MembershipFee(LocalDate.now(), LocalDate.now().plusYears(1), this.spnMembershipFeePrice.getValue(), this.txtUsername.getText());
		User newUser = new User(this.txtUsername.getText(), this.txtName.getText(), this.txtSurname.getText(), 
				                this.txtAddress.getText(), this.txtFiscalCode.getText(), "member", Utils.stringToDigest(this.txtNewPassword.getText()), fee);
    	Response r = this.requestController.makeRequest(Constants.INSERT, newUser);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	r = this.requestController.makeRequest(Constants.INSERT, fee);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	fillUserCmbFilters();
    	this.onBtnClearMemberClick(null);
	}
	
	/**
	 * handles the update boat button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnUpdateBoatClick(ActionEvent event) throws Exception{
    	Response r = this.requestController.makeRequest(Constants.GET_BOAT_BY_ID, this.selectedBoat);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	double amm = 0;
    	double len = 0;
    	
    	try {
    		amm = Double.parseDouble(this.spnBoatFeeAmount.getEditor().getText());
    		len = Double.parseDouble(this.spnBoatLength.getEditor().getText());
    	}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a integer or a floating point into Boat length and fee price!");
			alert.showAndWait();
    		return;
    	}
    	
    	if(this.txtBoatName.getText().equals("") || this.dtpBoatFeeExpDate.getValue() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a boat name and a valid date!");
			alert.showAndWait();
    		return;
    	}

    	BoatStorageFee uBsf = new BoatStorageFee(((Boat)r.getPayload()).getBoatStorageFee().getId(), 
    						  null,this.dtpBoatFeeExpDate.getValue(),amm,this.selectedBoat.getId());
    	
		Boat uBoat = new Boat(this.selectedBoat.getId(),this.txtBoatName.getText(), len, "", uBsf);
		
    	r = this.requestController.makeRequest(Constants.UPDATE_BOAT, uBoat);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	this.onBtnClearBoatClick(null);
    	displayBoats();
	}
	
	/**
	 * allow to send notification to a specific user
	 * @param user the user to be notified
	 * @param text the notification text
	 */
	private void sendNotification(User user, String text) {
		try {
			this.requestController.makeRequest(Constants.INSERT, new Notification(user.getUsername(),text, LocalDateTime.now()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * allows to display the table that contains all the boats
	 * for the selected member for the fee notification
	 * @throws Exception  exeption
	 */
	private void displayMemberBoats() throws Exception{
    	Response r = this.requestController.makeRequest(Constants.GET_ALL_BOATS, new EmptyPayload());
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Boat> boats = (ArrayList<Boat>)r.getPayload();
    	this.boatsMemberModels = FXCollections.observableArrayList();
    	
    	for(Boat b: boats) {
    		Button btnNotify = new Button();
    		ImageView btnGraphic = new ImageView(new Image("sailingclub/client/gui/images/notify.png"));
    		btnGraphic.setFitWidth(BTN_NOTIFY_MAX_W);
    		btnGraphic.setPreserveRatio(true);
    		btnNotify.setGraphic(btnGraphic);
    		btnNotify.getStyleClass().add("openbutton");
    		btnNotify.setOnAction(event -> this.sendNotification(this.userFilter, "REMINDER:\nYou have to pay the storage fee for\n" + b.getName()));
    		if(b.getIdMember().equals(this.userFilter.getUsername()))
    			this.boatsMemberModels.add(new BoatModel(b, btnNotify));
    	}
    	
    	this.tblMemberBoatNotifications.setItems(boatsMemberModels);
	}
	/**
	 * handles the clear notification button click
	 * @param event the click event
	 */
	public void btnNotifyMemberClick(ActionEvent event) {
		this.sendNotification(this.userFilter, "REMINDER:\nYou have to pay the annual membership fee");
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * display all the boats in the table on the tab boats management
	 * @throws Exception  exeption
	 */
	private void displayBoats() throws Exception{
    	Response r = this.requestController.makeRequest(Constants.GET_ALL_BOATS, new EmptyPayload());
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Boat> boats = (ArrayList<Boat>)r.getPayload();
    	this.boatsModels = FXCollections.observableArrayList();
    	
    	for(Boat b: boats) {
    		this.boatsModels.add(new BoatModel(b));
    	}
    	
    	this.tblBoats.setItems(boatsModels);
	}
	
	/**
	 * handles the clear race button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnClearRace(ActionEvent event) {
    	this.txtRaceName.clear();
        this.spnRacePrice.getEditor().setText("50");
        this.dtpRaceDate.setValue(null);
        this.lblSelectedRace.setText("");
        this.selectedRace = null;
        this.btnUpdateRace.setDisable(true);
        this.btnAddRace.setDisable(false);
	}
	
	/**
	 * handles the clear member button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnClearMemberClick(ActionEvent event) {
		txtUsername.setText("");
		txtNewPassword.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtAddress.setText("");
		txtFiscalCode.setText("");
		lblSelectedMember.setText("");
		this.spnMembershipFeePrice.getEditor().setText("50");
		this.dtpMembershipFeeDate.setValue(null);
		this.userFilter = null;
		this.boatsMemberModels.clear();
		this.tblMemberBoatNotifications.setItems(boatsMemberModels);
		this.btnMemberNotification.setDisable(true);
		this.btnUpdateMember.setDisable(true);
		this.btnDeleteMember.setDisable(true);
		this.btnAddMember.setDisable(false);
	}
	
	/**
	 * handles the clear boat button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnClearBoatClick(ActionEvent event) {
    	txtBoatName.setText("");
    	spnBoatLength.getEditor().setText("50");
    	dtpBoatFeeExpDate.setValue(null);
    	spnBoatFeeAmount.getEditor().setText("50");
    	lblSelectedBoat.setText("");
    	this.selectedBoat = null;
    	this.btnUpdateBoat.setDisable(true);
	}
	
	/**
	 * handles the update race button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnUpdateRaceClick(ActionEvent event) throws Exception{
		LocalDate rDate = this.dtpRaceDate.getValue();
		String rName = this.txtRaceName.getText();
		
		if(rName.equals("") || rDate == null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a date and a name!");
			alert.showAndWait();
			return;
		}
		
		double rPrice = 0;
		try {
			rPrice = Double.parseDouble(this.spnRacePrice.getEditor().getText());
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid price");
			alert.setContentText("You must insert an integer or a floating point value!");
			alert.showAndWait();
			return;
		}
		
		Race upRace = new Race(this.selectedRace.getId(),rDate, rPrice, rName);
    	Response r = this.requestController.makeRequest(Constants.UPDATE_RACE, upRace);
    	if(r.getStatusCode() != Constants.SUCCESS) return;
        this.displayRaces();
	}	
	
	/**
	 * handles the add race button click
	 * @param event the click event
	 * @throws Exception  exeption
	 */
	public void onBtnAddRaceClick(ActionEvent event) throws Exception {
		String rName = this.txtRaceName.getText();
		LocalDate rDate = this.dtpRaceDate.getValue();
		
		if(rName.equals("") || rDate == null){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid values");
			alert.setContentText("You must insert a date and a name!");
			alert.showAndWait();
			return;
		}
		
		double rPrice = 0;
		try {
			rPrice = Double.parseDouble(this.spnRacePrice.getEditor().getText());
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning alert");
			alert.setHeaderText("Invilid price");
			alert.setContentText("You must insert an integer or a floating point value!");
			alert.showAndWait();
			return;
		}
    	Response r = this.requestController.makeRequest(Constants.INSERT, new Race(rDate,rPrice,rName));
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	this.onBtnClearRace(null);
    	this.displayRaces();
	}

	/**
	 * allow to set pass the request controller between scenes
	 * @param controller the request controller
	 * @see sailingclub.client.RequestController
	 */
	public void setRequestController(RequestController controller)  {
		this.requestController = controller;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * allows to fill the combo box that allows the member selection and filtering
	 * @throws Exception
	 */
	private void fillUserCmbFilters() throws Exception{
		Response r = this.requestController.makeRequest(Constants.GET_MEMBERS, new EmptyPayload());
		if(r.getStatusCode() != Constants.SUCCESS) return;
		
		this.cmbSelectedUser.getItems().clear();
		ArrayList<String> members = (ArrayList<String>)r.getPayload();
		for(String u: members) this.cmbSelectedUser.getItems().add(u);
		cmbSelectedUser.getSelectionModel().selectFirst();
	}
	
	/**
	 * method called on the stage show
	 * it calls all the method that downloads all the info
	 */
	public void onStageShow() {
		try {
			this.displayPayments();
			this.fillUserCmbFilters();
			this.displayRaces();
			this.displayMemberBoats();
			this.displayBoats();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * handles the change of the user selection in the combobox
	 * @param selectedUser the new user selected
	 */
	private void onCmbSelectedUserSelectionChanged(String selectedUser) {
		try {
	    	Response r = this.requestController.makeRequest(Constants.GET_MEMBER_BY_USERNAME, selectedUser);
	    	if(r.getStatusCode() != Constants.SUCCESS) return;
			this.userFilter = (User)r.getPayload();
			txtUsername.setText(this.userFilter.getUsername());
			txtName.setText(this.userFilter.getName());
			this.txtNewPassword.setText("");
			txtSurname.setText(this.userFilter.getSurname());
			txtAddress.setText(this.userFilter.getAddress());
			txtFiscalCode.setText(this.userFilter.getFiscalCode());
			this.spnMembershipFeePrice.getEditor().setText(Double.toString(userFilter.getMembershipFee().getPrice()));
			this.dtpMembershipFeeDate.setValue(userFilter.getMembershipFee().getExpirationDate());
			lblSelectedMember.setText("Selected user for update: " + this.userFilter.getUsername());
			displayMemberBoats();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * handles the click of the table race button click
	 * @param btn the button in a specific row
	 * @param race the race selected
	 */
	private void onBtnRaceActionClick(Button btn, Race race)  {
		try {
	    	Response r = this.requestController.makeRequest(Constants.DELETE, race);
	    	if(r.getStatusCode() != Constants.SUCCESS) return;
	    	
	    	this.displayRaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * it displays the payments list on the payments tab
	 * @throws Exception  exeption
	 */
	private void displayPayments() throws Exception{
    	Response r = this.requestController.makeRequest(Constants.GET_PAYMENTS, new EmptyPayload());
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	ArrayList<Payment> payments = (ArrayList<Payment>)r.getPayload();
    	this.paymentsModels = FXCollections.observableArrayList();
    	
    	for(Payment p: payments) {
    		this.paymentsModels.add(new PaymentModel(p));
    	}
    	
    	this.tblPayment.setItems(paymentsModels);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * it displays the races table on the race tab
	 * @throws Exception  exeption
	 */
	private void displayRaces() throws Exception {
    	Response r = this.requestController.makeRequest(Constants.GET_RACES, new EmptyPayload());
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
			
	    	r = this.requestController.makeRequest(Constants.GET_RACES_SUB, allRaces.get(i).getId().toString());
			
			this.raceModels.add(new RaceModel(allRaces.get(i),btnAction,Integer.parseInt((String)r.getPayload())));
		}
		this.tblRaces.setItems(raceModels);
	}
}

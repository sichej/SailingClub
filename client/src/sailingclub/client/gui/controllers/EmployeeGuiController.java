package sailingclub.client.gui.controllers;

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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sailingclub.client.gui.fxml.BoatModel;
import sailingclub.client.gui.fxml.PaymentModel;
import sailingclub.client.gui.fxml.RaceModel;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Payment;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;

public class EmployeeGuiController implements Initializable{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	private User userToManage;
	private Race selectedRace;
	private Boat selectedBoat;
	private ObservableList<RaceModel> raceModels;
	private ObservableList<PaymentModel> paymentsModels;
	private ObservableList<BoatModel> boatsModels;
	
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
	@FXML private TextField txtRaceNameUR;
	@FXML private DatePicker dtpRaceDateUR;
	@FXML private Spinner<Double> spnRacePriceUR;
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

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		raceModels = FXCollections.observableArrayList();
		paymentsModels = FXCollections.observableArrayList();
		boatsModels = FXCollections.observableArrayList();
		
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
		this.tblRaces.setRowFactory( tv -> {
		    TableRow<RaceModel> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty()) {
		        	RaceModel rowData = row.getItem();
		            this.txtRaceNameUR.setText(rowData.getRaceName());
		            this.spnRacePriceUR.getEditor().setText(Double.toString(rowData.getRacePrice()));
		            this.dtpRaceDateUR.setValue(rowData.getRaceDate());
		            this.lblSelectedRace.setText("Selected race id: " + rowData.getRaceId());
		            this.selectedRace = new Race(rowData.getRaceId(),rowData.getRaceDate(), rowData.getRacePrice(), rowData.getRaceName());
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

		
		SpinnerValueFactory<Double> spnFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(50, 99999999, 10);
		SpinnerValueFactory<Double> spnFactoryUR = new SpinnerValueFactory.DoubleSpinnerValueFactory(50, 99999999, 10);
		this.spnRacePrice.setValueFactory(spnFactory);
		this.spnRacePriceUR.setValueFactory(spnFactoryUR);
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
	
	public void onTabBoatsSelected() {
		this.cmbSelectedUser.setVisible(false);
		try {
			displayBoats();
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
	
	public void onBtnUpdateBoatClick(ActionEvent event) throws Exception{
		out.writeObject(new Request(Constants.GET_BOAT_BY_ID, this.selectedBoat));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;

    	BoatStorageFee uBsf = new BoatStorageFee(((Boat)r.getPayload()).getBoatStorageFee().getId(), 
    						  null,this.dtpBoatFeeExpDate.getValue(),Double.parseDouble(this.spnBoatFeeAmount.getEditor().getText()),this.selectedBoat.getId());
    	
		Boat uBoat = new Boat(this.selectedBoat.getId(),this.txtBoatName.getText(), Double.parseDouble(this.spnBoatLength.getEditor().getText()), "", uBsf);
		
		out.writeObject(new Request(Constants.UPDATE_BOAT, uBoat));
    	r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	txtBoatName.setText("");
    	spnBoatLength.getEditor().setText("50");
    	dtpBoatFeeExpDate.setValue(null);
    	spnBoatFeeAmount.getEditor().setText("50");
    	lblSelectedBoat.setText("Select a boat");
    	displayBoats();
	}
	
	private void displayBoats() throws Exception{
		out.writeObject(new Request(Constants.GET_ALL_BOATS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Boat> boats = (ArrayList<Boat>)r.getPayload();
    	this.boatsModels = FXCollections.observableArrayList();
    	
    	for(Boat b: boats) {
    		this.boatsModels.add(new BoatModel(b));
    	}
    	
    	this.tblBoats.setItems(boatsModels);
	}
	
	public void onBtnUpdateRaceClick(ActionEvent event) throws Exception{
		Race upRace = new Race(this.selectedRace.getId(),this.dtpRaceDateUR.getValue(), Double.parseDouble(this.spnRacePriceUR.getEditor().getText()), this.txtRaceNameUR.getText());
		out.writeObject(new Request(Constants.UPDATE_RACE, upRace));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	this.txtRaceNameUR.clear();
        this.spnRacePriceUR.getEditor().setText("50");
        this.dtpRaceDateUR.setValue(null);
        this.lblSelectedRace.setText("");
        this.displayRaces();
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

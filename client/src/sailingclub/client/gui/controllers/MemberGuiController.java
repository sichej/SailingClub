package sailingclub.client.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sailingclub.client.gui.fxml.NotificationModel;
import sailingclub.client.gui.fxml.RaceModel;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.BankTransfer;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.CreditCard;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Notification;
import sailingclub.common.structures.Payment;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;
import sailingclub.common.structures.RaceParticipation;
import java.util.ArrayList;

public class MemberGuiController implements Initializable{
	private final String REGISTERED_STATE = "Unsubscribe";
	private final String NOT_REGISTERED_STATE = "Subscribe";
	private final Double BOAT_FEE_MULTIPLIER = 20.5;
	private final int GRD_PADDING = 10;
	private final int COLS_PER_ROW = 4;
	private final double SCROLL_SIZE = 19.5;
	private final int N_ROWS_VISIBLE = 2;
	private final int COLS_GAP = 10;
	private final int ROWS_GAP = 10;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private User loggedUser;
	private Map<String, String> btnTabAssoc;
	private Boat selectedBoat;
	private ObservableList<RaceModel> raceModels;
	private ObservableList<NotificationModel> notificationsModels;
	private double boatFeeNewPrice;
	private double boatNewLenght;
	private File boatNewImage;
	private ToggleGroup toggleGroup;
	private ColorAdjust gray;
	private ColorAdjust norm;
	
	@FXML private Button btnToggleMenu;
	@FXML private VBox vbMenu;
	@FXML private ImageView imgBtnToggleMenu;
	@FXML private Button btnProfileManagment;
	@FXML private Button btnBoatsManagment;
	@FXML private Button btnRaceManagment;
	@FXML private StackPane stckBody;
	@FXML private AnchorPane tabProfileManagment;
	@FXML private AnchorPane tabBoatsManagment;
	@FXML private AnchorPane tabRaceManagment;
	@FXML private AnchorPane tabAddBoat;
	@FXML private AnchorPane tabNotifications;
	@FXML private GridPane grdBoats;
	@FXML private ScrollPane scrContainer;
	@FXML private AnchorPane tabBoatOptions;
	@FXML private Label lblTitle;
	@FXML private Label lblBoatInfo;
	@FXML private Label lblUserInfo;
	@FXML private AnchorPane pnlBackdrop;
	@FXML private Button btnDeleteBoat;
	@FXML private ImageView imgBoatInfo;
	@FXML private Button btnLogout;
	@FXML private TableView<RaceModel> tblRaces;
	@FXML private TableColumn<RaceModel, Integer> colRaceId;
	@FXML private TableColumn<RaceModel, String> colRaceName;
	@FXML private TableColumn<RaceModel, LocalDate> colRaceDate;
	@FXML private TableColumn<RaceModel, Double> colRacePrice;
	@FXML private TableColumn<RaceModel, Button> colRaceAction;
	@FXML private TableColumn<RaceModel, ComboBox<String>> colRaceBoat;
	@FXML private Button btnLoadBoatImg;
	@FXML private TextField txtBoatName;
	@FXML private Spinner<Double> spnBoatLenght;
	@FXML private Button btnInsertBoat;
	@FXML private Label lblFeeAmount;
	@FXML private ImageView imgNewBoat;
	@FXML private ComboBox<Object> cmBoxBoatPaymentMethod;
	@FXML private ComboBox<Object> cmBoxMemberPaymentMethod;
	@FXML private ComboBox<Object> cmBoxRacePaymentMethod; 
	@FXML private Button btnPayBoatStorageFee;
	@FXML private Label lblBoatPaymentDescription;
	@FXML private Label lblMemberPaymentDescription;
	@FXML private Button btnPayMembershipFee;
	@FXML private Label lblUsername;
	@FXML private RadioButton radCard;
	@FXML private RadioButton radBank;
	@FXML private DatePicker dtpCardExpDate;
	@FXML private TextField txtPaymentFirstAttribute;
	@FXML private TextField txtPaymentSecondAttribute;
	@FXML private ImageView imgNotification;
	@FXML private Button btnNotification;
	@FXML private TableView<NotificationModel> tblNotifications;
	@FXML private TableColumn<NotificationModel, String> colNotificationDateTime;
	@FXML private TableColumn<NotificationModel, String> colNotificationText;
	@FXML private TableColumn<NotificationModel, Button> colNotificationAction;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		raceModels = FXCollections.observableArrayList();
		notificationsModels = FXCollections.observableArrayList();
		this.cmBoxRacePaymentMethod.setVisible(false);
        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		this.btnTabAssoc = new HashMap<String,String>();
		this.btnTabAssoc.put("btnProfileManagment", "tabProfileManagment");
		this.btnTabAssoc.put("btnBoatsManagment", "tabBoatsManagment");
		this.btnTabAssoc.put("btnRaceManagment", "tabRaceManagment");
		
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setVgrow(Priority.NEVER);
		this.grdBoats = new GridPane();  
		this.grdBoats.getRowConstraints().add(rowConstraints);
		this.grdBoats.setHgap(COLS_GAP);
		this.grdBoats.setVgap(ROWS_GAP);
		this.grdBoats.setPadding(new Insets(10, 0, 0, 0));
		this.grdBoats.setAlignment(Pos.CENTER);
		this.scrContainer.setContent(grdBoats);
		
		this.colRaceId.setCellValueFactory(new PropertyValueFactory<RaceModel,Integer>("raceId"));
		this.colRaceName.setCellValueFactory(new PropertyValueFactory<RaceModel,String>("raceName"));
		this.colRaceDate.setCellValueFactory(new PropertyValueFactory<RaceModel,LocalDate>("raceDate"));
		this.colRacePrice.setCellValueFactory(new PropertyValueFactory<RaceModel,Double>("racePrice"));
		this.colRaceAction.setCellValueFactory(new PropertyValueFactory<RaceModel,Button>("btnAction"));
		this.colRaceBoat.setCellValueFactory(new PropertyValueFactory<RaceModel,ComboBox<String>>("cmbBoat"));
		this.tblRaces.setPlaceholder(new Label("No races available!"));
		
		SpinnerValueFactory<Double> spnFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 600, 5);
		this.spnBoatLenght.setValueFactory(spnFactory);
		this.spnBoatLenght.setEditable(true);
		this.boatFeeNewPrice = (5 * this.BOAT_FEE_MULTIPLIER);
		this.lblFeeAmount.setText("Storage fee amount: " + this.boatFeeNewPrice);
		this.boatNewImage = null;
		
		spnBoatLenght.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			try { this.boatNewLenght = Double.parseDouble(newValue); }
			catch(Exception e) {this.boatNewLenght = 0.1;};
			this.boatFeeNewPrice = boatNewLenght *  this.BOAT_FEE_MULTIPLIER;
			this.lblFeeAmount.setText("Storage fee amount: " + this.boatFeeNewPrice);
	    });
		
		toggleGroup = new ToggleGroup();
		this.radBank.setToggleGroup(toggleGroup);
		this.radCard.setToggleGroup(toggleGroup);
		toggleGroup.selectedToggleProperty().addListener((event,oldv,newv) -> this.onRadPaymentMethodClick((RadioButton)newv));
		
		this.gray = new ColorAdjust();
		gray.setSaturation(-0.7);
		this.norm = new ColorAdjust();
		norm.saturationProperty().set(1);
		this.btnNotification.setVisible(false);
		
		this.colNotificationDateTime.setCellValueFactory(new PropertyValueFactory<NotificationModel,String>("dateTime"));
		this.colNotificationText.setCellValueFactory(new PropertyValueFactory<NotificationModel,String>("text"));
		this.colNotificationAction.setCellValueFactory(new PropertyValueFactory<NotificationModel,Button>("btnAction"));
	}
	
	public void setLoggedUser(User user) throws Exception{
		this.loggedUser = user;
	}
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}
	
	public void onStageShow() {
		try {
			this.displayBoats();
			displayNotifications();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onBtnToggleMenuClick(ActionEvent event) throws IOException {
		if(this.vbMenu.isVisible()) {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
	        pnlBackdrop.setVisible(false);
			vbMenu.setVisible(false);
			this.btnNotification.setVisible(false);
		}
		else {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_opened.png"));
	        pnlBackdrop.setVisible(true);
			vbMenu.setVisible(true);
			this.btnNotification.setVisible(true);
		}
	}
	
	public void onBtnViewNotificationsClick(ActionEvent event) {
		try {
			displayNotifications();
	        pnlBackdrop.setVisible(false);
			vbMenu.setVisible(false);
			this.btnNotification.setVisible(false);
			this.tabNotifications.toFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void displayNotifications() throws Exception {
		out.writeObject(new Request(Constants.GET_NOTIFICATIONS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Notification> notifications = (ArrayList<Notification>)r.getPayload();
    	
    	if(notifications.size() == 0) {
    		this.imgNotification.setEffect(gray);
    		return;
    	}
    	
    	this.imgNotification.setEffect(norm);
    	notificationsModels = FXCollections.observableArrayList();
    	
    	for(Notification n: notifications) {
    		Button btn = new Button("Mark as read");
    		btn.setOnAction(event -> {
    			try {
	    			out.writeObject(new Request(Constants.DELETE, n));
	    	    	in.readObject();
	    	    	this.displayNotifications();
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		});
    		notificationsModels.add(new NotificationModel(n,btn));
    	}
    	
    	this.tblNotifications.setItems(notificationsModels);
	}
	
	public void onBtnMenuClick(ActionEvent event) throws Exception {
		imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		vbMenu.setVisible(false);
		pnlBackdrop.setVisible(false);
		this.btnNotification.setVisible(false);
	
		String pressedBtn = ((Button) event.getSource()).getId();
		String tab = this.btnTabAssoc.get(pressedBtn);
		
		if (tab.toString().equals("tabBoatsManagment")) {
			this.cmBoxRacePaymentMethod.setVisible(false);
			displayBoats();
			this.lblTitle.setText("Boats management");
		}else if(tab.toString().equals("tabProfileManagment")) {
			this.cmBoxRacePaymentMethod.setVisible(false);
			this.lblTitle.setText("Profile management");
			displayInfo();
		}else if(tab.toString().equals("tabRaceManagment")){
			this.cmBoxRacePaymentMethod.setVisible(true);
			this.lblTitle.setText("Race management");
			displayRaces();
		}
		
		ObservableList<Node> tabs = FXCollections.observableArrayList(this.stckBody.getChildren());
		
		int index;
		for(index = 0; index < tabs.size(); index++)
			if(tabs.get(index).getId().equals(tab)) break;
			
		Collections.swap(tabs, index, tabs.size() - 1);
		this.stckBody.getChildren().setAll(tabs);
		
	}
	
	@SuppressWarnings("unchecked")
	private void onBtnBoatsGridClick(Boat clickedBoat) throws Exception  {
		this.selectedBoat = clickedBoat;
		this.tabBoatOptions.toFront();
		String info = "Boat id: " + this.selectedBoat.getId() + "\n" +
				"Boat name: " + this.selectedBoat.getName() + "\n" +
				"Boat length: " + this.selectedBoat.getLength() + " mt.\n" +
				"Boat owner: " + this.selectedBoat.getIdMember() + "\n" +
				"Storage fee exp. date: " + this.selectedBoat.getBoatStorageFee().getExpirationDate() + "\n" +
				"Storage fee amount: " + this.selectedBoat.getBoatStorageFee().getAmount() + " $";
		this.lblBoatInfo.setText(info);
				
		try {
			this.imgBoatInfo.setImage(SwingFXUtils.toFXImage(Utils.toBufferedImage(clickedBoat.getPicture()), null));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.lblBoatPaymentDescription.setText("Lenght: " + this.selectedBoat.getLength() + " mt\nMultiplier: " + this.BOAT_FEE_MULTIPLIER +
											"\n------------------" + 
											"\nTotal: " + this.selectedBoat.getBoatStorageFee().getAmount() + "$");
		
		this.fillCmbPayments(this.cmBoxBoatPaymentMethod);
	}
	
	public void onBtnPayBoatStorageFeeClick(ActionEvent event) throws Exception {
		out.writeObject(new Request(Constants.PAY_BOAT_STORAGE_FEE, this.selectedBoat));
    	Response r = (Response)in.readObject();
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
    		double amount = this.selectedBoat.getBoatStorageFee().getAmount();
    		String method = ((String)this.cmBoxBoatPaymentMethod.getValue()).split(" - ")[0];
    		String details = ((String)this.cmBoxBoatPaymentMethod.getValue()).split(" - ")[1];
    		
    		Payment paymentLog = new Payment(amount, this.loggedUser.getUsername(), method,  details, LocalDate.now(), "Payment for storage fee of:\n" + this.selectedBoat.getName());
    		out.writeObject(new Request(Constants.INSERT, paymentLog));
    		in.readObject();
    		this.btnBoatsManagment.fire();
    	}
	}
	
	public void onBtnPayMembershipFee(ActionEvent event) throws Exception {
		out.writeObject(new Request(Constants.PAY_MEMBERSHIP_FEE, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
    		out.writeObject(new Request(Constants.LOGIN, this.loggedUser));
        	this.loggedUser = (User)((Response)in.readObject()).getPayload();
        	double amount = this.loggedUser.getMembershipFee().getPrice();
    		String method = ((String)this.cmBoxMemberPaymentMethod.getValue()).split(" - ")[0];
    		String details = ((String)this.cmBoxMemberPaymentMethod.getValue()).split(" - ")[1];
    		
    		Payment paymentLog = new Payment(amount, this.loggedUser.getUsername(), method,  details, LocalDate.now(), "Payment for annual fee of:\n" + this.loggedUser.getName() + " " + this.loggedUser.getSurname());
    		out.writeObject(new Request(Constants.INSERT, paymentLog));
    		in.readObject();
    		this.btnProfileManagment.fire();
    	}
	}
	
	public void onBtnDeleteBoatClick(ActionEvent event) throws IOException, ClassNotFoundException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Risk alert!");
		alert.setHeaderText("Are you sure you want to remove the boat?");
		alert.setContentText("This operation is irreversible");
		Optional<ButtonType> option = alert.showAndWait();
		
		if(option.get() == ButtonType.OK) {
			out.writeObject(new Request(Constants.DELETE, this.selectedBoat));
        	Response rs = (Response)in.readObject();
			if(rs.getStatusCode() == Constants.SUCCESS)
				this.btnBoatsManagment.fire();
		}
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
	
	@SuppressWarnings("unchecked")
	private void displayInfo() throws Exception {
		String userInfo = this.loggedUser.getUsername() + "'s info:\n" + 
						"Name:" + this.loggedUser.getName() + " Surname: " + this.loggedUser.getSurname() + 
						"\nAddress: " + this.loggedUser.getAddress() + "\nFiscal code: " + this.loggedUser.getFiscalCode() +
						"\nMembership fee exp. date: " + this.loggedUser.getMembershipFee().getExpirationDate();
		this.lblUserInfo.setText(userInfo);
		
		this.lblMemberPaymentDescription.setText("Annual fee fixed price:\n" + this.loggedUser.getMembershipFee().getPrice() + 
												"\n--------------------------" + 
												"\nTotal: " + this.loggedUser.getMembershipFee().getPrice() + "$");
		
		this.lblUsername.setText(this.loggedUser.getUsername());
		
		this.fillCmbPayments(this.cmBoxMemberPaymentMethod);
	}
	
	private void onBtnRaceActionClick(Race race, Button clickedBtn, ComboBox<String> cmb) {
		int raceId = race.getId();
		String raceState = clickedBtn.getText();
		
		try {
			if(raceState.equals(this.NOT_REGISTERED_STATE)) {	//subscription request
				RaceParticipation subscription = new RaceParticipation(raceId, this.loggedUser.getUsername(),Integer.parseInt(cmb.getValue().split(" - ")[1]));
				out.writeObject(new Request(Constants.INSERT, subscription));
				
				double amount = race.getPrice();
	    		String method = ((String)this.cmBoxRacePaymentMethod.getValue()).split(" - ")[0];
	    		String details = ((String)this.cmBoxRacePaymentMethod.getValue()).split(" - ")[1];
	    		
	    		Payment paymentLog = new Payment(amount, this.loggedUser.getUsername(), method,  details, LocalDate.now(), "Payment for the subscription to:\n" + race.getName());
	    		out.writeObject(new Request(Constants.INSERT, paymentLog));
	    		in.readObject();
			}
			else if(raceState.equals(this.REGISTERED_STATE)) { 		//unsubscribe request
				RaceParticipation subscription = new RaceParticipation(raceId, this.loggedUser.getUsername());
				out.writeObject(new Request(Constants.DELETE, subscription));
			}
			
			Response r = (Response)in.readObject();
	    	if(r.getStatusCode() != Constants.SUCCESS) return;
	    	
			this.displayRaces();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void onRadPaymentMethodClick(RadioButton sel) {
		this.txtPaymentFirstAttribute.setText("");
		this.txtPaymentSecondAttribute.setText("");
		this.dtpCardExpDate.setValue(null);
		if(sel == this.radBank) {
			this.dtpCardExpDate.setVisible(false);
			this.txtPaymentFirstAttribute.setPromptText("IBAN");
			this.txtPaymentSecondAttribute.setPromptText("Bank");
		}else if(sel == this.radCard) {
			this.dtpCardExpDate.setVisible(true);
			this.txtPaymentFirstAttribute.setPromptText("Number");
			this.txtPaymentSecondAttribute.setPromptText("CVV");
		}
	}
	
	public void onBtnAddPaymentMethod(ActionEvent evt) throws Exception{
		if(this.radCard == (RadioButton)this.toggleGroup.getSelectedToggle()) {
			String number = this.txtPaymentFirstAttribute.getText();
			int cvv = Integer.parseInt(this.txtPaymentSecondAttribute.getText());
			out.writeObject(new Request(Constants.INSERT, new CreditCard(number,cvv,this.dtpCardExpDate.getValue(),this.loggedUser.getUsername())));
	    	in.readObject();
		}else {
			String iban = this.txtPaymentFirstAttribute.getText();
			String bank = this.txtPaymentSecondAttribute.getText();
			out.writeObject(new Request(Constants.INSERT, new BankTransfer(iban,bank,this.loggedUser.getUsername())));
	    	in.readObject();
		}
		this.fillCmbPayments(cmBoxMemberPaymentMethod);
	}
	
	public void onBtnLoadBoatImgClick(ActionEvent evt) {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg","*.JPG", "*.JPEG","*.png"));
		this.boatNewImage = fileChooser.showOpenDialog(stage);
		
		if(this.boatNewImage == null) return;
		
		this.imgNewBoat.setImage(new Image(boatNewImage.getAbsolutePath()));
	}
	
	public void onBtnInsertBoatClick(ActionEvent evt) throws IOException, ClassNotFoundException {
		String boatName = this.txtBoatName.getText();
		if(boatName.equals("")) return;
		byte[] img = null;
		
		Boat newBoat;
		if(this.boatNewImage != null) {
			img = Utils.toByteArray(ImageIO.read(this.boatNewImage), this.boatNewImage.getName().substring(this.boatNewImage.getName().lastIndexOf('.') + 1));
			newBoat = new Boat(boatName,this.boatNewLenght, this.loggedUser.getUsername(),this.boatNewImage.getName(), img);
		}else {
			newBoat = new Boat(boatName,this.boatNewLenght, this.loggedUser.getUsername(),"", null);
		}
		
		out.writeObject(new Request(Constants.INSERT, newBoat));
    	Response rBoat = (Response)in.readObject();
    	
    	if(rBoat.getStatusCode() == Constants.SUCCESS) {
    		int newBoatId = Integer.parseInt((String)rBoat.getPayload());
    		BoatStorageFee fee = new BoatStorageFee(LocalDate.now(), LocalDate.now().plusYears(1), this.boatFeeNewPrice, newBoatId);
    		out.writeObject(new Request(Constants.INSERT, fee));
        	Response rFee = (Response)in.readObject();
        	
        	if(rFee.getStatusCode() == 200) this.btnBoatsManagment.fire();
    	}
	}
	
	@SuppressWarnings("unchecked")
	private void displayRaces() throws Exception{
		out.writeObject(new Request(Constants.GET_RACES, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Race> allRaces = (ArrayList<Race>)r.getPayload();
    	
		out.writeObject(new Request(Constants.GET_RACES_PARTICIPATIONS, new EmptyPayload()));
    	r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Race> userRaces = (ArrayList<Race>)r.getPayload();
    	
		this.raceModels = FXCollections.observableArrayList();
		out.writeObject(new Request(Constants.GET_BOATS, new EmptyPayload()));
		r = (Response)in.readObject();
		if(r.getStatusCode() != Constants.SUCCESS) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("No boats alert!");
			alert.setHeaderText("You can't participate");
			alert.setContentText("You can't participate to races because you haven't configured a boat yet!");
			alert.showAndWait();
			return;
		}
    	ArrayList<Boat> boats = (ArrayList<Boat>) r.getPayload();
		
    	fillCmbPayments(this.cmBoxRacePaymentMethod);
    	
    	if(this.cmBoxRacePaymentMethod.getItems().size() == 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("No boats alert!");
			alert.setHeaderText("You can't participate");
			alert.setContentText("You can't participate to races because you haven't configured a payment method yet!");
			alert.showAndWait();
			return;
    	}
    	
		for(int i = 0; i < allRaces.size(); i++) {
			Button btnAction = new Button();
			ComboBox<String> cmb = new ComboBox<String>();
			for(Boat b: boats) cmb.getItems().add(b.getName() + " - " + b.getId());
			
			btnAction.setId(Integer.toString(allRaces.get(i).getId()));
			
			cmb.getSelectionModel().selectFirst();
			btnAction.setText(this.NOT_REGISTERED_STATE);
			final Race rAtIndex = allRaces.get(i);
			btnAction.setOnAction(event -> onBtnRaceActionClick(rAtIndex,btnAction, cmb));
						
			for(int j = 0; j < userRaces.size(); j++) {
				if(userRaces.get(j).getId() == allRaces.get(i).getId()) {  //se  sono registrato
					btnAction.setText(this.REGISTERED_STATE);
					out.writeObject(new Request(Constants.GET_SUBSCRIPTED_BOAT, userRaces.get(j)));
					String indexId = Integer.toString(((Boat)((Response)in.readObject()).getPayload()).getId());
					for(int k = 0; k < cmb.getItems().size(); k++)
						if(cmb.getItems().get(k).split(" - ")[1].equals(indexId))
							cmb.getSelectionModel().select(k);
					cmb.setDisable(true);
					btnAction.setOnAction(event -> onBtnRaceActionClick(rAtIndex,btnAction, cmb));
					break;
				}
			}
			
			if(allRaces.get(i).getDate().isBefore(LocalDate.now())) {  //se gara finita
				cmb.setDisable(true);
				btnAction.setDisable(true);
				btnAction.setText("Race ended");
			}
			this.raceModels.add(new RaceModel(allRaces.get(i),btnAction,cmb));
		}
		
		this.tblRaces.setItems(raceModels);
	}
	
	private void onBtnAddBoatClick() {
		this.tabAddBoat.toFront();
		this.txtBoatName.setText("");
		this.spnBoatLenght.getEditor().setText(Integer.toString(5));
		this.imgNewBoat.setImage(new Image("sailingclub/client/gui/images/add_image.png"));
	}
	
	@SuppressWarnings("unchecked")
	private void displayBoats() throws IOException, ClassNotFoundException {
		out.writeObject(new Request(Constants.GET_BOATS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	this.grdBoats.getChildren().clear();
    	System.out.println(r.getStatusCode());
    	double numRows = 0;
    	int col = 0 , row = 0;
    	
    	if(r.getStatusCode() == Constants.SUCCESS) {
	    	ArrayList<Boat> boats = (ArrayList<Boat>)r.getPayload();
	    	numRows = Math.ceil(((double)boats.size()) / ((double)this.COLS_PER_ROW));
    	
	    	for(int i = 0; i < boats.size(); i++) {
				Button button = new Button();
				button.setMinWidth(((this.scrContainer.getWidth() - SCROLL_SIZE - this.COLS_GAP * this.COLS_PER_ROW) / this.COLS_PER_ROW) - this.GRD_PADDING);
				button.setMinHeight((this.scrContainer.getHeight() - (this.ROWS_GAP * numRows)) / N_ROWS_VISIBLE);
				button.getStyleClass().add("btnBoatsGrid");
			    ImageView view = new ImageView();
				Image boatThumbnail = SwingFXUtils.toFXImage(Utils.toBufferedImage(boats.get(i).getPicture()), null);
				view.setImage(boatThumbnail);
			    view.setPreserveRatio(true);
			    view.setFitWidth(button.getMinWidth());
			    AnchorPane imageLayout = new AnchorPane();
			    imageLayout.getChildren().add(view);
			    Label txtBoat = new Label(boats.get(i).getName());
			    txtBoat.setAlignment(Pos.CENTER);
			    txtBoat.getStyleClass().add("lblBoat");
			    AnchorPane.setBottomAnchor(txtBoat, button.getMinHeight() / 10);
			    AnchorPane.setLeftAnchor(txtBoat, 0.0);
			    AnchorPane.setRightAnchor(txtBoat, 0.0);
			    imageLayout.getChildren().add(txtBoat);
			    imageLayout.setPrefHeight(button.getMinHeight());
			    AnchorPane.setTopAnchor(view, 0.0); 
			    AnchorPane.setLeftAnchor(view, 0.0);
			    AnchorPane.setRightAnchor(view, 0.0);
			    button.setGraphic(imageLayout);
			    
			    final Boat boat = boats.get(i);
			    button.setOnAction(event -> {
					try {
						onBtnBoatsGridClick(boat);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				this.grdBoats.add(button,col, row);
				
				if(col / (COLS_PER_ROW - 1)  == 1) {
					row++;
					col = 0;
				}else col++; 
			}
    	}
		
		Button addBtn = new Button();
		addBtn.getStyleClass().add("btnBoatsGrid");
		addBtn.setMinWidth(((this.scrContainer.getWidth() - SCROLL_SIZE - this.COLS_GAP * this.COLS_PER_ROW) / this.COLS_PER_ROW) - this.GRD_PADDING);
		addBtn.setMinHeight((this.scrContainer.getHeight() - (this.ROWS_GAP * numRows)) / N_ROWS_VISIBLE);
		
		ImageView view = new ImageView(new Image("sailingclub/client/gui/images/add_boat.png"));
	    view.setPreserveRatio(true);
	    view.setFitWidth(addBtn.getMinWidth());
	    AnchorPane imageLayout = new AnchorPane();
	    imageLayout.getChildren().add(view);
	    imageLayout.setPrefHeight(addBtn.getMinHeight());
	    AnchorPane.setTopAnchor(view, 10.00);
	    AnchorPane.setBottomAnchor(view, 50.00);
	    AnchorPane.setLeftAnchor(view, 0.0);
	    AnchorPane.setRightAnchor(view, 0.0);
	    Label lbl = new Label("Add a boat");
	    imageLayout.getChildren().add(lbl);
	    AnchorPane.setBottomAnchor(lbl, 20.0);
	    lbl.setPrefWidth(addBtn.getMinWidth());
	    lbl.getStyleClass().add("lbladd");
	    lbl.setAlignment(Pos.CENTER);
	    addBtn.setGraphic(imageLayout);
	    addBtn.setOnAction(event -> onBtnAddBoatClick());
		
	    System.out.println(col + " " + row);
	    
		this.grdBoats.add(addBtn,col, row);
		
		this.grdBoats.setPadding(new Insets(10, 10, 10, 10));
	}
	
	private void fillCmbPayments(ComboBox<Object> cmb) throws Exception{
		out.writeObject(new Request(Constants.GET_CREDIT_CARDS, new EmptyPayload()));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode()!= 200) return;
		ArrayList<CreditCard> creditCards = (ArrayList<CreditCard>)r.getPayload();
    	
    	cmb.getItems().clear();

    	for(CreditCard c: creditCards) {
    		cmb.getItems().add("C. card - " + c.getCardNumber());
    	}
    	
    	cmb.getItems().add(new Separator());
    	
    	if(!cmb.getItems().isEmpty())
    		cmb.getSelectionModel().selectFirst();
    	
    	out.writeObject(new Request(Constants.GET_BANK_TRANSFERS, new EmptyPayload()));
    	r = (Response)in.readObject();
    	if(r.getStatusCode()!= 200) return;
    	ArrayList<BankTransfer> bankTransfers = (ArrayList<BankTransfer>)r.getPayload();
    	
    	for(BankTransfer b: bankTransfers) {
    		cmb.getItems().add(b.getBank() + " - " + b.getIban());
    	}
    	
    	if(!cmb.getItems().isEmpty())
    		cmb.getSelectionModel().selectFirst();
	}
}

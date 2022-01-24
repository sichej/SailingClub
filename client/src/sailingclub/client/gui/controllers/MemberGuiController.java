package sailingclub.client.gui.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sailingclub.client.gui.fxml.RaceModel;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;
import java.util.ArrayList;

public class MemberGuiController implements Initializable{
	private final String BOAT_NOT_FOUND_ALT = "sailingclub/client/gui/images/boatnotfound.jpg";
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
	
	@FXML private Button btnToggleMenu;
	@FXML private VBox vbMenu;
	@FXML private VBox vbInfo;
	@FXML private VBox vbMyRaces;
	@FXML private VBox vbAvaliableRaces;
	@FXML private ImageView imgBtnToggleMenu;
	@FXML private Button btnProfileManagment;
	@FXML private Button btnBoatsManagment;
	@FXML private Button btnRaceManagment;
	@FXML private StackPane stckBody;
	@FXML private AnchorPane tabProfileManagment;
	@FXML private AnchorPane tabBoatsManagment;
	@FXML private AnchorPane tabRaceManagment;
	@FXML private GridPane grdBoats;
	@FXML private ScrollPane scrContainer;
	@FXML private AnchorPane tabBoatOptions;
	@FXML private Label lblTitle;
	@FXML private Label lblBoatInfo;
	@FXML private Label lblName;
	@FXML private Label lblSurname;
	@FXML private Label lblUsername;
	@FXML private Label lblAddress;
	@FXML private Label lblFiscalCode;
	@FXML private Label lblDatePrize;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		raceModels =  FXCollections.observableArrayList();
		
        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		vbMenu.setVisible(false);
		vbInfo.setVisible(false);
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
	}
	
	public void setLoggedUser(User user) throws Exception{
		this.loggedUser = user;
	}
	
	public void setStreams(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}
	
	public void OnBtnToggleMenuClick(ActionEvent event) throws IOException {
		
		if(this.vbMenu.isVisible()) {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
	        pnlBackdrop.setVisible(false);
			vbMenu.setVisible(false);
		}
		else {
	        imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_opened.png"));
	        pnlBackdrop.setVisible(true);
			vbMenu.setVisible(true);
		}
		
	}
	
	public void OnBtnMenuClick(ActionEvent event) throws Exception {
		// when a member selects a specific menu option, automatically close the menu to show the full application screen
		imgBtnToggleMenu.setImage(new Image("sailingclub/client/gui/images/menu_closed.png"));
		vbMenu.setVisible(false);
		pnlBackdrop.setVisible(false);
		
		String pressedBtn = ((Button) event.getSource()).getId();
		String tab = this.btnTabAssoc.get(pressedBtn);
		
		if (tab.toString().equals("tabBoatsManagment")) {
			displayBoats();
			this.lblTitle.setText("Boats management");
		}else if(tab.toString().equals("tabProfileManagment")) {
			this.lblTitle.setText("Profile management");
			displayInfo();
		}else if(tab.toString().equals("tabRaceManagment")){
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
	
	private void OnBtnBoatsGridClick(Boat clickedBoat)  {
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
	}
	
	public void OnBtnDeleteBoatClick(ActionEvent event) throws IOException, ClassNotFoundException {
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
	
	private void displayInfo() {
		vbInfo.setVisible(true);
		lblName.setText(this.loggedUser.getName());
		lblSurname.setText(this.loggedUser.getSurname());
		lblUsername.setText(this.loggedUser.getUsername());
		lblAddress.setText(this.loggedUser.getAddress());
		lblFiscalCode.setText(this.loggedUser.getFiscalCode());
	}
	
	@SuppressWarnings("unchecked")
	private void displayRaces() throws IOException, ClassNotFoundException{
		out.writeObject(new Request(Constants.GET_RACES, this.loggedUser));
    	Response r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Race> allRaces = (ArrayList<Race>)r.getPayload();
    	
		out.writeObject(new Request(Constants.GET_RACES_PARTICIPATIONS, this.loggedUser));
    	r = (Response)in.readObject();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Race> userRaces = (ArrayList<Race>)r.getPayload();
    	
		this.raceModels =  FXCollections.observableArrayList();
		
		for(int i = 0; i < allRaces.size(); i++) {
			Button btnAction = new Button();
			for(int j = 0; j < userRaces.size(); j++) {
				if(userRaces.get(j).getId() == allRaces.get(i).getId()) {
					btnAction.setText("Unsubscribe");
					break;
				}else {
					btnAction.setText("Subscribe");
				}
			}
			this.raceModels.add(new RaceModel(allRaces.get(i),btnAction));
		}
		
		this.tblRaces.setItems(raceModels);
	}
	
	@SuppressWarnings("unchecked")
	private void displayBoats() throws IOException, ClassNotFoundException {
		out.writeObject(new Request(Constants.GET_BOATS, this.loggedUser));
    	Response r = (Response)in.readObject();
    	this.grdBoats.getChildren().clear();
    	if(r.getStatusCode() != Constants.SUCCESS) return;
    	
    	ArrayList<Boat> boats = (ArrayList<Boat>)r.getPayload();
    	int col = 0 , row = 0;
		for(int i = 0; i < boats.size(); i++) {
			Button button = new Button(boats.get(i).getName());
			double numRows = Math.ceil(((double)boats.size()) / ((double)this.COLS_PER_ROW));
			button.setMinWidth((this.scrContainer.getWidth() - SCROLL_SIZE - this.COLS_GAP * this.COLS_PER_ROW) / this.COLS_PER_ROW);
			button.setMinHeight((this.scrContainer.getHeight() - (this.ROWS_GAP * numRows)) / N_ROWS_VISIBLE);
			button.getStyleClass().add("btnBoatsGrid");
		    ImageView view = new ImageView();
			try {
				view.setImage(SwingFXUtils.toFXImage(Utils.toBufferedImage(boats.get(i).getPicture()), null));
			} catch (IOException e) {
				view.setImage(new Image(BOAT_NOT_FOUND_ALT));
				e.printStackTrace();
			}
		    view.setPreserveRatio(true);
		    view.setFitWidth((this.scrContainer.getWidth() - SCROLL_SIZE - this.COLS_GAP * this.COLS_PER_ROW) / this.COLS_PER_ROW);
		    button.setGraphic(view);
		    final Boat boat = boats.get(i);
		    button.setOnAction(event -> OnBtnBoatsGridClick(boat));
			this.grdBoats.add(button,col, row);
			
			if(col / (COLS_PER_ROW - 1)  == 1) {
				row++;
				col = 0;
			}else col++; 
		}
	}
}

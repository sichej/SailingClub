package sailingclub.client.gui.fxml;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sailingclub.common.structures.Race;

public class RaceModel {
	private SimpleIntegerProperty raceId;
	private SimpleStringProperty raceName;
	private LocalDate raceDate;
	private SimpleDoubleProperty racePrice;
	private Button btnAction;
	private ComboBox<String> cmbBoat;
	
	public RaceModel(Race race, Button btnAction, ComboBox<String> cmbBoat) {
		this.raceId = new SimpleIntegerProperty(Integer.valueOf(race.getId()));
		this.raceName = new SimpleStringProperty(race.getName());
		this.raceDate = race.getDate();
		this.racePrice = new SimpleDoubleProperty(Double.valueOf(race.getPrice()));
		this.btnAction = btnAction;
		this.cmbBoat = cmbBoat;
	}

	public int getRaceId() {
		return raceId.get();
	}

	public void setRaceId(int raceId) {
		this.raceId.set(raceId);
	}

	public String getRaceName() {
		return raceName.get();
	}

	public void setRaceName(String raceName) {
		this.raceName.set(raceName);;
	}

	public LocalDate getRaceDate() {
		return raceDate;
	}

	public void setRaceDate(LocalDate raceDate) {
		this.raceDate = raceDate;
	}

	public double getRacePrice() {
		return racePrice.get();
	}

	public void setRacePrice(double racePrice) {
		this.racePrice.set(racePrice);
	}

	public Button getBtnAction() {
		return btnAction;
	}

	public void setBtnAction(Button btnAction) {
		this.btnAction = btnAction;
	}

	public ComboBox<String> getCmbBoat() {
		return cmbBoat;
	}

	public void setCmbBoat(ComboBox<String> cmbBoat) {
		this.cmbBoat = cmbBoat;
	}
	
	
}

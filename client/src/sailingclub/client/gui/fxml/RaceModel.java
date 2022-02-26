package sailingclub.client.gui.fxml;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sailingclub.common.structures.Race;

/**
 * is the class that rappresent the model
 * for a row of a table that contains races
 */
public class RaceModel {
	private SimpleIntegerProperty raceId;
	private SimpleStringProperty raceName;
	private LocalDate raceDate;
	private SimpleDoubleProperty racePrice;
	private Button btnAction;
	private ComboBox<String> cmbBoat;
	private SimpleIntegerProperty subs;
	
	/**
	 * the onstructor for the row model
	 * @param race the race
	 * @param btnAction the action for the race
	 * @param cmbBoat the combo box for the boat selection
	 */
	public RaceModel(Race race, Button btnAction, ComboBox<String> cmbBoat) {
		this.raceId = new SimpleIntegerProperty(Integer.valueOf(race.getId()));
		this.raceName = new SimpleStringProperty(race.getName());
		this.raceDate = race.getDate();
		this.racePrice = new SimpleDoubleProperty(Double.valueOf(race.getPrice()));
		this.btnAction = btnAction;
		this.cmbBoat = cmbBoat;
	}
	
	/**
	 * another constructor
	 * @param race the race
	 * @param btnAction the action for the race
	 * @param subs the total subscriptions
	 */
	public RaceModel(Race race, Button btnAction, Integer subs) {
		this.raceId = new SimpleIntegerProperty(Integer.valueOf(race.getId()));
		this.raceName = new SimpleStringProperty(race.getName());
		this.raceDate = race.getDate();
		this.racePrice = new SimpleDoubleProperty(Double.valueOf(race.getPrice()));
		this.btnAction = btnAction;
		this.subs = new SimpleIntegerProperty(Integer.valueOf(subs));
	}

	/**
	 * returns the race id
	 * @return the race id
	 */
	public int getRaceId() {
		return raceId.get();
	}

	/**
	 * sets the race id
	 * @param raceId the race id
	 */
	public void setRaceId(int raceId) {
		this.raceId.set(raceId);
	}

	/**
	 * returns the race name
	 * @return the race name
	 */
	public String getRaceName() {
		return raceName.get();
	}

	/**
	 * sets the race name
	 * @param raceName the race name
	 */
	public void setRaceName(String raceName) {
		this.raceName.set(raceName);;
	}

	/**
	 * returns the race date
	 * @return the race date
	 */
	public LocalDate getRaceDate() {
		return raceDate;
	}

	/**
	 * sets the race date
	 * @param raceDate the race date
	 */
	public void setRaceDate(LocalDate raceDate) {
		this.raceDate = raceDate;
	}

	/**
	 * returns the race price
	 * @return the race price
	 */
	public double getRacePrice() {
		return racePrice.get();
	}

	/**
	 * sets the race price
	 * @param racePrice the race price
	 */
	public void setRacePrice(double racePrice) {
		this.racePrice.set(racePrice);
	}

	/**
	 * returns the button that perform an action
	 * @return the button that perform an action
	 */
	public Button getBtnAction() {
		return btnAction;
	}

	/**
	 * sets the button for the action
	 * @param btnAction the button for the action
	 */
	public void setBtnAction(Button btnAction) {
		this.btnAction = btnAction;
	}

	/**
	 * returns the combo box for boat selection
	 * @return the combo box for boat selection
	 */
	public ComboBox<String> getCmbBoat() {
		return cmbBoat;
	}

	/**
	 * sets the combo box for boat selection
	 * @param cmbBoat the combo box for boat selection
	 */
	public void setCmbBoat(ComboBox<String> cmbBoat) {
		this.cmbBoat = cmbBoat;
	}

	/**
	 * returns the subs number
	 * @return the combo box for boat selection
	 */
	public int getSubs() {
		return subs.get();
	}

	/**
	 * sets the subs number
	 * @param subs the subs number
	 */
	public void setSubs(int subs) {
		this.subs.set(subs);
	}
}

package sailingclub.client.gui.fxml;

import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sailingclub.common.structures.Boat;

/**
 * is the class that rappresent the model
 * for a row of a table that contains boats
 */
public class BoatModel {
	private SimpleIntegerProperty boatId;
	private SimpleStringProperty boatName;
	private SimpleDoubleProperty boatLength;
	private SimpleStringProperty boatOwner;
	private LocalDate boatFeeExpDate;
	private SimpleDoubleProperty boatFeeAmount;
	private Button btnNotify;
	
	/**
	 * the constructor
	 * @param b the boat of the row
	 */
	public BoatModel(Boat b) {
		this.boatId = new SimpleIntegerProperty(Integer.valueOf(b.getId()));
		this.boatName = new SimpleStringProperty(b.getName());
		this.boatLength = new SimpleDoubleProperty(Double.valueOf(b.getLength()));
		this.boatFeeExpDate = b.getBoatStorageFee().getExpirationDate();
		this.boatFeeAmount = new SimpleDoubleProperty(Double.valueOf(b.getBoatStorageFee().getAmount()));
		this.boatOwner = new SimpleStringProperty(b.getIdMember());
	}
	
	/**
	 * the constructor with the notify button
	 * @param b the boat of the row
	 * @param btnNotify the button of the row
	 */
	public BoatModel(Boat b, Button btnNotify) {
		this.boatId = new SimpleIntegerProperty(Integer.valueOf(b.getId()));
		this.boatName = new SimpleStringProperty(b.getName());
		this.boatFeeExpDate = b.getBoatStorageFee().getExpirationDate();
		this.btnNotify = btnNotify;
	}
	
	/**
	 * returns the button
	 * @return the button
	 */
	public Button getBtnNotify() {
		return btnNotify;
	}

	/**
	 * sets the button
	 * @param btnNotify the button
	 */
	public void setBtnNotify(Button btnNotify) {
		this.btnNotify = btnNotify;
	}

	/**
	 * returns the boat id
	 * @return the boat id
	 */
	public int getBoatId() {
		return boatId.get();
	}

	/**
	 * sets the boat id
	 * @param boatId the boat id
	 */
	public void setBoatId(int boatId) {
		this.boatId.set(boatId);;
	}

	/**
	 * returns the boat name
	 * @return the boat name
	 */
	public String getBoatName() {
		return boatName.get();
	}

	/**
	 * sets the boat name
	 * @param boatName the boat name
	 */
	public void setBoatName(String boatName) {
		this.boatName.set(boatName);
	}

	/**
	 * returns the boat len
	 * @return the boat len
	 */
	public double getBoatLength() {
		return boatLength.get();
	}

	/**
	 * sets the boat len
	 * @param boatLength the boat len
	 */
	public void setBoatLength(double boatLength) {
		this.boatLength.set(boatLength);
	}

	/**
	 * returns the boat fee exp date
	 * @return the boat fee exp date
	 */
	public LocalDate getBoatFeeExpDate() {
		return boatFeeExpDate;
	}

	/**
	 * sets the boat fee exp date
	 * @param boatFeeExpDate the boat fee exp date
	 */
	public void setBoatFeeExpDate(LocalDate boatFeeExpDate) {
		this.boatFeeExpDate = boatFeeExpDate;
	}

	/**
	 * returns the fee ammount
	 * @return the fee ammount
	 */
	public double getBoatFeeAmount() {
		return boatFeeAmount.get();
	}

	/**
	 * sets the fee ammount
	 * @param boatFeeAmount the fee ammount
	 */
	public void setBoatFeeAmount(double boatFeeAmount) {
		this.boatFeeAmount.set(boatFeeAmount);
	}

	/**
	 * returns the boat owner
	 * @return the boat owner
	 */
	public String getBoatOwner() {
		return boatOwner.get();
	}

	/**
	 * sets the boat owner
	 * @param boatOwner the boat owner
	 */
	public void setBoatOwner(String boatOwner) {
		this.boatOwner.set(boatOwner);
	}
}

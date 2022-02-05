package sailingclub.client.gui.fxml;

import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sailingclub.common.structures.Boat;

public class BoatModel {
	private SimpleIntegerProperty boatId;
	private SimpleStringProperty boatName;
	private SimpleDoubleProperty boatLength;
	private SimpleStringProperty boatOwner;
	private LocalDate boatFeeExpDate;
	private SimpleDoubleProperty boatFeeAmount;
	
	public BoatModel(Boat b) {
		this.boatId = new SimpleIntegerProperty(Integer.valueOf(b.getId()));
		this.boatName = new SimpleStringProperty(b.getName());
		this.boatLength = new SimpleDoubleProperty(Double.valueOf(b.getLength()));
		this.boatFeeExpDate = b.getBoatStorageFee().getExpirationDate();
		this.boatFeeAmount = new SimpleDoubleProperty(Double.valueOf(b.getBoatStorageFee().getAmount()));
		this.boatOwner = new SimpleStringProperty(b.getIdMember());
	}

	public int getBoatId() {
		return boatId.get();
	}

	public void setBoatId(int boatId) {
		this.boatId.set(boatId);;
	}

	public String getBoatName() {
		return boatName.get();
	}

	public void setBoatName(String boatName) {
		this.boatName.set(boatName);
	}

	public double getBoatLength() {
		return boatLength.get();
	}

	public void setBoatLength(double boatLength) {
		this.boatLength.set(boatLength);
	}

	public LocalDate getBoatFeeExpDate() {
		return boatFeeExpDate;
	}

	public void setBoatFeeExpDate(LocalDate boatFeeExpDate) {
		this.boatFeeExpDate = boatFeeExpDate;
	}

	public double getBoatFeeAmount() {
		return boatFeeAmount.get();
	}

	public void setBoatFeeAmount(double boatFeeAmount) {
		this.boatFeeAmount.set(boatFeeAmount);
	}

	public String getBoatOwner() {
		return boatOwner.get();
	}

	public void setBoatOwner(String boatOwner) {
		this.boatOwner.set(boatOwner);
	}
}

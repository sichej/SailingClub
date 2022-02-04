package sailingclub.client.gui.fxml;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sailingclub.common.structures.Payment;

public class PaymentModel {
	private SimpleIntegerProperty id;
	private SimpleDoubleProperty amount;
	private SimpleStringProperty memberId;
	private SimpleStringProperty method;
	private SimpleStringProperty details;
	private LocalDate date;
	private SimpleStringProperty purpose;
	
	public PaymentModel(Payment p) {
		this.id = new SimpleIntegerProperty(Integer.valueOf(p.getId()));
		this.amount = new SimpleDoubleProperty(Double.valueOf(p.getAmount()));
		this.memberId = new SimpleStringProperty(p.getMemberId());
		this.method = new SimpleStringProperty(p.getMethod());
		this.details = new SimpleStringProperty(p.getDetails());
		this.date = p.getDate();
		this.purpose = new SimpleStringProperty(p.getPurpose());
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public double getAmount() {
		return amount.get();
	}

	public void setAmount(double amount) {
		this.amount.set(amount);	
	}

	public String getMemberId() {
		return memberId.get();
	}

	public void setMemberId(String memberId) {
		this.memberId.set(memberId);;
	}

	public String getMethod() {
		return method.get();
	}

	public void setMethod(String method) {
		this.method.set(getDetails());
	}

	public String getDetails() {
		return details.get();
	}

	public void setDetails(String details) {
		this.details.set(getPurpose());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPurpose() {
		return purpose.get();
	}

	public void setPurpose(String purpose) {
		this.purpose.set(purpose);;
	}
}

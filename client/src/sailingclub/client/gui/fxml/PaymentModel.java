package sailingclub.client.gui.fxml;

import java.time.LocalDate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sailingclub.common.structures.Payment;

/**
 * is the class that rappresent the model
 * for a row of a table that contains payments
 */
public class PaymentModel {
	private SimpleIntegerProperty id;
	private SimpleDoubleProperty amount;
	private SimpleStringProperty memberId;
	private SimpleStringProperty method;
	private SimpleStringProperty details;
	private LocalDate date;
	private SimpleStringProperty purpose;
	
	/**
	 * the constructor
	 * @param p the payment
	 */
	public PaymentModel(Payment p) {
		this.id = new SimpleIntegerProperty(Integer.valueOf(p.getId()));
		this.amount = new SimpleDoubleProperty(Double.valueOf(p.getAmount()));
		this.memberId = new SimpleStringProperty(p.getMemberId());
		this.method = new SimpleStringProperty(p.getMethod());
		this.details = new SimpleStringProperty(p.getDetails());
		this.date = p.getDate();
		this.purpose = new SimpleStringProperty(p.getPurpose());
	}

	/**
	 * returns the payment id
	 * @return the payment id
	 */
	public int getId() {
		return id.get();
	}

	/**
	 * sets the payment id
	 * @param id the payment id
	 */
	public void setId(int id) {
		this.id.set(id);
	}

	/**
	 * returns the payment amount
	 * @return the payment amount
	 */
	public double getAmount() {
		return amount.get();
	}

	/**
	 * sets the payment amount
	 * @param amount the payment amount
	 */
	public void setAmount(double amount) {
		this.amount.set(amount);	
	}

	/**
	 * returns the payment member id
	 * @return the payment member id
	 */
	public String getMemberId() {
		return memberId.get();
	}

	/**
	 * sets the payment member id
	 * @param memberId the payment member id
	 */
	public void setMemberId(String memberId) {
		this.memberId.set(memberId);;
	}

	/**
	 * returns the payment method
	 * @return the payment method
	 */
	public String getMethod() {
		return method.get();
	}

	/**
	 * sets the payment method
	 * @param method the payment method
	 */
	public void setMethod(String method) {
		this.method.set(getDetails());
	}

	/**
	 * returns the payment details
	 * @return the payment details
	 */
	public String getDetails() {
		return details.get();
	}

	/**
	 * sets the payment details
	 * @param details the payment details
	 */
	public void setDetails(String details) {
		this.details.set(getPurpose());
	}

	/**
	 * returns the payment date
	 * @return the payment date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * sets the payment date
	 * @param date the payment date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * returns the payment purpose
	 * @return the payment purpose
	 */
	public String getPurpose() {
		return purpose.get();
	}

	/**
	 * sets the payment purpose
	 * @param purpose the payment purpose
	 */
	public void setPurpose(String purpose) {
		this.purpose.set(purpose);
	}
}

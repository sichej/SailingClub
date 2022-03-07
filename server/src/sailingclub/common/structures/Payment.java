package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * This class represent a record (log) for a payment made
 * in the system by {@code User}
 * @see CreditCard
 * @see BankTransfer
 */
public class Payment implements Insertable, Serializable {
	private static final long serialVersionUID = 1L;
	/**payment id*/
	private int id;
	/**payment amount*/
	private double amount;
	/**the payer (member)*/
	private String memberId;
	/**the payment method used*/
	private String method;
	/**the paymets method details*/
	private String details;
	/**timestamp for the payment*/
	private LocalDate date;
	/**the purpose of the payment*/
	private String purpose;
	
	/**
	 * Payment constructor
	 * 
	 * @param id  id of the payment
	 * @param amount Payment amount 
	 * @param memberId user paying
	 * @param method payment method
	 * @param details payment details
	 * @param date payment date
	 * @param purpose payment pourpose
	 */
	public Payment(int id, double amount, String memberId, String method, String details, LocalDate date,String purpose) {
		this.id = id;
		this.amount = amount;
		this.memberId = memberId;
		this.method = method;
		this.details = details;
		this.date = date;
		this.purpose = purpose;
	}
	
	/**
	 * Payment constructor
	 * 
	 * @param amount Payment amount 
	 * @param memberId user paying
	 * @param method payment method
	 * @param details payment details
	 * @param date payment date
	 * @param purpose payment pourpose
	 */
	public Payment(double amount, String memberId, String method, String details, LocalDate date, String purpose) {
		this.amount = amount;
		this.memberId = memberId;
		this.method = method;
		this.details = details;
		this.date = date;
		this.purpose = purpose;
	}
	
	/**
	 * returns the payment id
	 * @return the payment id
	 */
	public int getId() { 
		return id; 
	}
	
	/**
	 * returns the payment amount
	 * @return the payment amount
	 */
	public double getAmount() { return amount; }
	
	/**
	 * returns the member id of the payer
	 * @return the member id of the payer
	 */
	public String getMemberId() { 
		return memberId; 
	}
	
	/**
	 * returns the payment method used for the payment
	 * @return the payment method used for the payment
	 */
	public String getMethod() { 
		return method; 
	}
	
	/**
	 * returns the description
	 * @return the description
	 */
	public String getDetails() { 
		return details; 
	}
	
	/**
	 * returns the date-time of the payment
	 * @return the date time of the payment
	 */
	public LocalDate getDate() { 
		return date; 
	}
	
	/**
	 * returns the purpose of the payment
	 * @return the purpose of the payment
	 */
	public String getPurpose() { 
		return purpose; 
	}

	/**
	 * Get the attributes of the class
	 * @return String Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"amount", "member_id", "method", "details", "date", "purpose"};
	}

	/**
	 * Get the database table name
	 * @return String database table name
	 */
	@Override
	public String getInstanceName() {
		return "payment";
	}

	/**
	 * Get String[] with value of the class elements
	 * @return String value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{Double.toString(this.amount), "'" + this.memberId + "'", "'" + this.method + "'", "'" + this.details + "'","'" +  date.toString() + "'", "'" + this.purpose + "'"};
	}

	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "id";
	}
}

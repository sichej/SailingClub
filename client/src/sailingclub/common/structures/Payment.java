package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * Payment class
 */
public class Payment implements Insertable, Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double amount;
	private String memberId;
	private String method;
	private String details;
	private LocalDate date;
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
	
	public int getId() { return id; }
	public double getAmount() { return amount; }
	public String getMemberId() { return memberId; }
	public String getMethod() { return method; }
	public String getDetails() { return details; }
	public LocalDate getDate() { return date; }
	public String getPurpose() { return purpose; }

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"amount", "member_id", "method", "details", "date", "purpose"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "payment";
	}

	/**
	 * Get printable String with value of the class elements
	 * @return String  value of the class elements
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

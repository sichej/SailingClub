package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * this class represent an annual fee for a member
 * that has to pay the fee
 */
public class MembershipFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	/**fee id*/
	private int id;
	/**the fee last payent date*/
	private LocalDate paymentDate;
	/**the fee expiration date*/
	private LocalDate expirationDate;
	/**the fee amount*/
	private double price;
	/**the member which fee refers to*/
	private String idMember;
	
	/**
	 * MembershipFee constructor
	 * 
	 * @param id  MembershipFee id
	 * @param paymentDate  date of the Membership fee payment
	 * @param expirationDate  date of the Membership fee payment expiration
	 * @param price  MembershipFee price
	 * @param idMember user which the fee is referred to 
	 */
	public MembershipFee(int id, LocalDate paymentDate, LocalDate expirationDate, double price, String idMember) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = price;
		this.idMember = idMember;
	}
	
	/**
	 * MembershipFee constructor
	 * 
	 * @param paymentDate  date of the Membership fee payment
	 * @param expirationDate  date of the Membership fee payment expiration
	 * @param amount  MembershipFee price
	 * @param idMember user paying
	 */
	public MembershipFee(LocalDate paymentDate, LocalDate expirationDate, double amount, String idMember) {
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = amount;
		this.idMember = idMember;
	}
	/**
	 * returns the id of the fee
	 * @return id  the id of the fee
	 */
	public int getId() { 
		return id; 
	}
	
	/**
	 * Returns the date of the last payment
	 * @return paymentDate the date of the last payment
	 */
	public LocalDate getPaymentDate() { 
		return paymentDate; 
	}
	
	/**
	 * Returns the expiration date for the fee
	 * @return expirationDate the expiration date for the fee
	 */
	public LocalDate getExpirationDate() { 
		return expirationDate; 
	}
	
	/**
	 * Returns the amount of the fee
	 * @return price the amount of the fee
	 */
	public double getPrice() { return price; }
	
	/**
	 * Returns the member username which the fee refers to
	 * @return idMember the member username which the fee refers to
	 */
	public String getIdMember() { return idMember; }

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"payment_date", "expiration_date", "price","id_member"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "membership_fee";
	}

	/**
	 * Get String[] with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'"+this.paymentDate+"'", "'"+this.expirationDate+"'", Double.toString(this.price), "'" + this.idMember + "'"};
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

package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * MembershipFee class
 */
public class MembershipFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate paymentDate;
	private LocalDate expirationDate;
	private double price;
	private String idMember;
	
	/**
	 * MembershipFee constructor
	 * 
	 * @param id  MembershipFee id
	 * @param paymentDate  date of the Membership fee payment
	 * @param expirationDate  date of the Membership fee payment expiration
	 * @param price  MembershipFee price
	 * @param idMember user paying
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
	
	public int getId() { return id; }
	public LocalDate getPaymentDate() { return paymentDate; }
	public LocalDate getExpirationDate() { return expirationDate; }
	public double getPrice() { return price; }
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
	 * Get printable String with value of the class elements
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

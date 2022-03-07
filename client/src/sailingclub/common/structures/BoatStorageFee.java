package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * This class represent a Boat fee for the annual storage
 * in a boat garage
 */
public class BoatStorageFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the fee id*/
	private int id;
	/**the last payment date for the fee*/
	private LocalDate paymentDate;
	/**the expire date for the fee*/
	private LocalDate expirationDate;
	/**the price to pay for the fee*/
	private double amount;
	/**the boat id*/
	private int idBoat;
	
	/**
	 * BoatStorageFee constructor
	 * 
	 * @param id  BoatStorageFee id
	 * @param paymentDate  BoatStorageFee paymentDate
	 * @param expirationDate BoatStorageFee expirationDate
	 * @param amount  BoatStorageFee price
	 * @param idBoat  boat's id 
	 * 
	 */
	public BoatStorageFee(int id, LocalDate paymentDate, LocalDate expirationDate, double amount, int idBoat) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.amount = amount;
		this.idBoat = idBoat;
	}
	
	/**
	 * BoatStorageFee constructor
	 * 
	 * @param paymentDate  BoatStorageFee paymentDate
	 * @param expirationDate BoatStorageFee expirationDate
	 * @param amount  BoatStorageFee price
	 * @param idBoat  boat's id 
	 * 
	 */
	public BoatStorageFee(LocalDate paymentDate, LocalDate expirationDate, double amount, int idBoat) {
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.amount = amount;
		this.idBoat = idBoat;
	}
	
	/**
	 * returns the id of the fee
	 * @return id  the id of the fee
	 */
	public int getId() { 
		return id; 
	}
	
	/**
	 * returns the last payment date of the fee
	 * @return paymentDate  Date of the last payment
	 */
	public LocalDate getPaymentDate() { 
		return paymentDate; 
	}
	
	/**
	 * returns the expiration date for the fee
	 * @return expirationDate the expiration date for the fee
	 */
	public LocalDate getExpirationDate() { 
		return expirationDate; 
	}
	
	/**
	 * the fee amount to pay
	 * @return amount  price for one year of storage
	 */
	public double getAmount() { 
		return amount; 
	}
	
	/**
	 * returns the boat id which the fee refers to
	 * @return idBoat the boat id which the fee refers to
	 */
	public int getIdBoat() { return idBoat; }

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"payment_date", "expiration_date", "amount","id_boat"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "boat_storage_fee";
	}

	/**
	 * Get String[] with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'"+this.paymentDate+"'", "'"+this.expirationDate+"'", Double.toString(this.amount), "'" + this.idBoat + "'"};
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

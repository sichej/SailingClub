package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

/**
 * BoatStorageFee class
 */
public class BoatStorageFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate paymentDate;
	private LocalDate expirationDate;
	private double amount;
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
	 * Get id
	 * @return id  boat's id
	 */
	public int getId() { return id; }
	/**
	 * Get payment date
	 * @return paymentDate  Date of the payment
	 */
	public LocalDate getPaymentDate() { return paymentDate; }
	/**
	 * Get expiration date
	 * @return expirationDate  Expiration date
	 */
	public LocalDate getExpirationDate() { return expirationDate; }
	/**
	 * Get amount
	 * @return amount  price for one year of storage
	 */
	public double getAmount() { return amount; }
	/**
	 * Get id boat
	 * @return idBoat  boat's id
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
	 * Get printable String with value of the class elements
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

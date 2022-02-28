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
	
	public int getId() { return id; }
	public LocalDate getPaymentDate() { return paymentDate; }
	public LocalDate getExpirationDate() { return expirationDate; }
	public double getAmount() { return amount; }
	public int getIdBoat() { return idBoat; }

	@Override
	public String[] getAttributes() {
		return new String[]{"payment_date", "expiration_date", "amount","id_boat"};
	}

	@Override
	public String getInstanceName() {
		return "boat_storage_fee";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'"+this.paymentDate+"'", "'"+this.expirationDate+"'", Double.toString(this.amount), "'" + this.idBoat + "'"};
	}
	
	@Override
	public String getPk() {
		return "id";
	}
}

package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

public class BoatStorageFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate paymentDate;
	private LocalDate expirationDate;
	private double amount;
	private String idBoat;
	
	public BoatStorageFee(int id, LocalDate paymentDate, LocalDate expirationDate, double amount, String idBoat) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.amount = amount;
		this.idBoat = idBoat;
	}
	
	public int getId() { return id; }
	public LocalDate getPaymentDate() { return paymentDate; }
	public LocalDate getExpirationDate() { return expirationDate; }
	public double getAmount() { return amount; }
	public String getIdBoat() { return idBoat; }

	@Override
	public String[] getAttributes() {
		return new String[]{"id", "payment_date", "expiration_date", "amount","id_boat"};
	}

	@Override
	public String getInstanceName() {
		return "boat_storage_fee";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'" + this.id + "'", "'"+this.paymentDate+"'", "'"+this.expirationDate+"'", Double.toString(this.amount), "'" + this.idBoat + "'"};
	}
}

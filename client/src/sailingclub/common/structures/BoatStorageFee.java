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
	private int idBoat;
	
	public BoatStorageFee(int id, LocalDate paymentDate, LocalDate expirationDate, double amount, int idBoat) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.amount = amount;
		this.idBoat = idBoat;
	}
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

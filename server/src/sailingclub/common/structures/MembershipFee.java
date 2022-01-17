package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

public class MembershipFee implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate paymentDate;
	private LocalDate expirationDate;
	private double price;
	private String idMember;
	
	public MembershipFee(int id, LocalDate paymentDate, LocalDate expirationDate, double price, String idMember) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.price = price;
		this.idMember = idMember;
	}
	
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

	@Override
	public String[] getAttributes() {
		return new String[]{"payment_date", "expiration_date", "price","id_member"};
	}

	@Override
	public String getInstanceName() {
		return "membership_fee";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'"+this.paymentDate+"'", "'"+this.expirationDate+"'", Double.toString(this.price), "'" + this.idMember + "'"};
	}
	
	@Override
	public String getPk() {
		return "id";
	}
}

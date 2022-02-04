package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;

public class Payment implements Insertable, Serializable {
	
	private int id;
	private double amount;
	private String memberId;
	private String method;
	private String details;
	private LocalDate date;
	private String purpose;
	
	public Payment(int id, double amount, String memberId, String method, String details, LocalDate date,String purpose) {
		this.id = id;
		this.amount = amount;
		this.memberId = memberId;
		this.method = method;
		this.details = details;
		this.date = date;
		this.purpose = purpose;
	}
	
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

	@Override
	public String[] getAttributes() {
		return new String[]{"amount", "member_id", "method", "details", "date", "purpose"};
	}

	@Override
	public String getInstanceName() {
		return "payment";
	}

	@Override
	public String[] getValues() {
		return new String[]{Double.toString(this.amount), "'" + this.memberId + "'", "'" + this.method + "'", "'" + this.details + "'","'" +  date.toString() + "'", "'" + this.purpose + "'"};
	}

	@Override
	public String getPk() {
		return "id";
	}
}

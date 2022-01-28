package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;
import sailingclub.common.Removable;

public class CreditCard implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private int cvv;
	private LocalDate expDate;
	private String idMember;
	
	public CreditCard(String cardNumber, int cvv, LocalDate expDate, String idMember) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expDate = expDate;
		this.idMember = idMember;
	}
	
	public String getIdMember() {
		return idMember;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public LocalDate getExpirationDate() {
		return expDate;
	}

	@Override
	public String getPkValue() {
		return this.getCardNumber();
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"card_number", "cvv", "expiration_date", "id_member"};
	}

	@Override
	public String getInstanceName() {
		return "credit_card";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'" + this.cardNumber + "'", Integer.toString(this.cvv) , "'" + this.expDate.toString() + "'", "'" + this.idMember + "'" };
	}

	@Override
	public String getPk() {
		return this.getCardNumber();
	}

}

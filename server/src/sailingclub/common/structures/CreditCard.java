package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * CreditCard class
 */
public class CreditCard implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private int cvv;
	private LocalDate expDate;
	private String idMember;
	
	/**
	 * CreditCard constructor
	 * 
	 * @param cardNumber  number of the credit card
	 * @param cvv  cvv of the credit card
	 * @param expDate expiration date of the credit card
	 * @param idMember  username of the owner
	 */
	public CreditCard(String cardNumber, int cvv, LocalDate expDate, String idMember) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expDate = expDate;
		this.idMember = idMember;
	}
	/**
	 * Get id memeber
	 * @return idMember  member's id
	 */
	public String getIdMember() {
		return idMember;
	}
	/**
	 * Get credit card number
	 * @return cardNumber  card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * Get cvv
	 * @return cvv  card cvv
	 */
	public int getCvv() {
		return cvv;
	}

	/**
	 * Get expiration date
	 * @return expDate  card expiration date
	 */
	public LocalDate getExpirationDate() {
		return expDate;
	}

	/**
	 * Get the Primary Key value
	 * @return String  Primary Key value
	 */
	@Override
	public String getPkValue() {
		return this.getCardNumber();
	}

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"card_number", "cvv", "expiration_date", "id_member"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "credit_card";
	}

	/**
	 * Get String with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.cardNumber + "'", Integer.toString(this.cvv) , "'" + this.expDate.toString() + "'", "'" + this.idMember + "'" };
	}

	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return this.getCardNumber();
	}

}

package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDate;

import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * This class is the implementation for a credit card
 * that allows the member to make payments with credit card
 */
public class CreditCard implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the card number (identifier)*/
	private String cardNumber;
	/**the card cvv*/
	private int cvv;
	/**the card expiration date*/
	private LocalDate expDate;
	/**the owner (member) of the card*/
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
	 * Returns the id member owner of the credit card
	 * @return idMember  the id member owner of the credit card
	 */
	public String getIdMember() {
		return idMember;
	}
	/**
	 * Returns the credit card number (identifier)
	 * @return cardNumber  the credit card number (identifier)
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * returns the cvv of the card
	 * @return cvv the cvv of the card
	 */
	public int getCvv() {
		return cvv;
	}

	/**
	 * Returns the expiration date for the card
	 * @return expDate the expiration date for the card
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
	 * Get String[] with value of the class elements
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

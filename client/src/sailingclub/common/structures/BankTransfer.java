package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;

/**
 * Is an implementation of the structure for a bank account
 * that allows the {@code Member} to make banks transfers
 */
public class BankTransfer implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the iban of the bank account*/
	private String iban;
	/**the bank name of the bank account*/
	private String bank;
	/**the owner of the bank account*/
	private String idMember;
	
	/**
	 * BankTransfer constructor
	 * @param iban  the iban of the customer
	 * @param bank  the bank of the customer
	 * @param idMember  user id of the customer
	 * 
	 */
	public BankTransfer(String iban, String bank, String idMember) {
		this.idMember = idMember;
		this.bank = bank;
		this.iban = iban;
	}
	
	/**
	 * returns the iban of the bank account
	 * @return  the iban of the bank account
	 */
	public String getIban() { return iban; }
	
	/**
	 * returns the bank of the bank account
	 * @return bank the bank of the bank account
	 */
	public String getBank() { return bank; }
	
	/**
	 * return the owner of the bank account
	 * @return idMember the owner (id) of the bank account
	 */
	public String getIdMember() { return idMember; }

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"iban", "bank", "id_member"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "bank_transfer";
	}

	/**
	 * Get String with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.iban + "'", "'" + this.bank + "'", "'" + this.idMember + "'"};
	}
	
	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "iban";
	}
}

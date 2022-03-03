package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;

/**
 * BankTransfer class
 */
public class BankTransfer implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private String iban;
	private String bank;
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
	 * Get iban
	 * @return iban
	 */
	public String getIban() { return iban; }
	
	/**
	 * Get bank 
	 * @return bank
	 */
	public String getBank() { return bank; }
	
	/**
	 * Get idMember
	 * @return idMember
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

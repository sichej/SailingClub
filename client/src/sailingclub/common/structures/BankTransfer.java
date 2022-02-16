package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;

/*
 * BankTransfer class
 */
public class BankTransfer implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private String iban;
	private String bank;
	private String idMember;
	
	/*
	 * BankTransfer constructor
	 * @param iban  iban
	 * @param bank  bank
	 * @param idMember  user id
	 * 
	 */
	public BankTransfer(String iban, String bank, String idMember) {
		this.idMember = idMember;
		this.bank = bank;
		this.iban = iban;
	}
	
	/*
	 * Get iban
	 * @return iban
	 */
	public String getIban() { return iban; }
	/*
	 * Get bank 
	 * @return bank
	 */
	public String getBank() { return bank; }
	/*
	 * Get idMember
	 * @return idMember
	 */
	public String getIdMember() { return idMember; }

	@Override
	public String[] getAttributes() {
		return new String[]{"iban", "bank", "id_member"};
	}

	@Override
	public String getInstanceName() {
		return "bank_transfer";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'" + this.iban + "'", "'" + this.bank + "'", "'" + this.idMember + "'"};
	}
	
	@Override
	public String getPk() {
		return "iban";
	}
}

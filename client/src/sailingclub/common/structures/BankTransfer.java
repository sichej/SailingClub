package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;

public class BankTransfer implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private String iban;
	private String bank;
	private String idMember;
	
	public BankTransfer(String iban, String bank, String idMember) {
		this.idMember = idMember;
		this.bank = bank;
		this.iban = iban;
	}
	
	public String getIban() { return iban; }
	public String getBank() { return bank; }
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
		return new String[]{"'" + this.iban + "'", this.bank, "'" + this.idMember + "'"};
	}
}

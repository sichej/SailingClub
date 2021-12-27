package sailingclub.common.structures;

import java.io.Serializable;

import sailingclub.common.Translatable;

public class BankTransfer implements Translatable, Serializable{
	private static final long serialVersionUID = 1L;
	private String iban;
	private String bank;
	private String id_member;
	
	public BankTransfer(String iban, String bank, String id_member) {
		this.id_member = id_member;
		this.bank = bank;
		this.iban = iban;
	}
	
	@Override
	public String[] getSQLAttributes() {
		return new String[]{"iban", "bank", "id_member"};
	}

	@Override
	public String getSQLTableName() {
		return "bank_transfer";
	}

	@Override
	public String[] getSQLValues() {
		return new String[]{"'" + this.iban + "'", this.bank, "'" + this.id_member + "'"};
	}

	@Override
	public Class<?>[] getSQLTypes() {
		return new Class[]{this.iban.getClass(), this.bank.getClass(), this.id_member.getClass()};
	}

	@Override
	public Object getSQLPrimaryKeyValue() {
		return this.iban;
	}

	@Override
	public String getSQLPrimaryKeyName() {
		return "iban";
	}
}

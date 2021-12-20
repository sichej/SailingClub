package server;

public class UserSQLModel implements SQLModel{
	private String username;
	private String name;
	private String surname;
	private String address;
	private String fiscal_code;
	private String user_type;
	private String password;
	
	public UserSQLModel(String username, String name, String surname, String address, String fiscal_code, String user_type, String password) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.fiscal_code = fiscal_code;
		this.user_type = user_type;
		this.password = password;
	}
	
	@Override
	public String[] getSQLAttributes() {
		return new String[]{"username", "name", "surname", "address", "fiscal_code", "user_type", "password"};
	}

	@Override
	public String getSQLTableName() {
		return "user";
	}

	@Override
	public String[] getSQLValues() {
		return new String[]{this.username, "'" + this.name + "'", this.surname, "'" + this.address + "'", "'" + this.fiscal_code + "'", "'" + this.user_type + "'", "'" + this.password + "'"};
	}

	@Override
	public Class<?>[] getSQLTypes() {
		return new Class[]{this.username.getClass(), this.name.getClass(), this.surname.getClass(), this.address.getClass(), this.fiscal_code.getClass(), this.user_type.getClass(), this.password.getClass()};
	}

	@Override
	public Object getSQLPrimaryKeyValue() {
		return this.username;
	}

	@Override
	public String getSQLPrimaryKeyName() {
		return "username";
	}
}

package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * User class
 */
public class User implements Insertable, Serializable, Removable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String name;
	private String surname;
	private String address;
	private String fiscalCode;
	private String userType;
	private String password;
	private MembershipFee fee;
	
	/**
	 * User constructor
	 * @param username  
	 * @param name
	 * @param address
	 * @param fiscalCode
	 * @param userType  can be member or employee
	 * @param password  should to be hashed with md5 encryption
	 * @param fee  Membership fee
	 */
	public User(String username, String name, String surname, String address, String fiscalCode, String userType, String password, MembershipFee fee) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.fiscalCode = fiscalCode;
		this.userType = userType;
		this.password = password;
		this.fee = fee;
	}
	/**
	 * User constructor
	 * @param username  	 
	 * @param password  shuld to be hashed with md5 encription
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/**
	 * User constructor
	 * @param username 
	 */
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() { return username; }
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public String getAddress() { return address; }
	public String getFiscalCode() { return fiscalCode; }
	public String getUserType() { return userType; }
	public String getPassword() { return password; }
	public MembershipFee getMembershipFee() { return fee; }

	@Override
	public String[] getAttributes() {
		return new String[]{"username", "name", "surname", "address", "fiscal_code", "user_type", "password"};
	}

	@Override
	public String getInstanceName() {
		return "user";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'"+this.username+"'", "'" + this.name + "'", "'"+this.surname+"'", "'" + this.address + "'", "'" + this.fiscalCode + "'", "'" + this.userType + "'", "'" + this.password + "'"};
	}
	
	@Override
	public String getPk() {
		return "username";
	}

	@Override
	public String getPkValue() {
		return "'" + this.getUsername() + "'";
	}
}

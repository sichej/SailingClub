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
	
	/**
	 * Get Username
	 * @return username  Member's username
	 */
	public String getUsername() { return username; }
	/**
	 * Get name
	 * @return name  get Member's name
	 */
	public String getName() { return name; }
	/**
	 * Get lastname
	 * @return surname  member's lastname
	 */
	public String getSurname() { return surname; }
	/**
	 * Get address
	 * @return address  member's address
	 */
	public String getAddress() { return address; }
	/**
	 * Get fiscal code
	 * @return fiscalCode  member's fiscal code
	 */
	public String getFiscalCode() { return fiscalCode; }
	/**
	 * Get user type
	 * @return userType  type of user
	 */
	public String getUserType() { return userType; }
	/**
	 * Get password
	 * @return password  member's password
	 */
	public String getPassword() { return password; }
	/**
	 * Get fee
	 * @return fee  member fee
	 */
	public MembershipFee getMembershipFee() { return fee; }

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"username", "name", "surname", "address", "fiscal_code", "user_type", "password"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "user";
	}

	/**
	 * Get printable String with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'"+this.username+"'", "'" + this.name + "'", "'"+this.surname+"'", "'" + this.address + "'", "'" + this.fiscalCode + "'", "'" + this.userType + "'", "'" + this.password + "'"};
	}
	
	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "username";
	}

	/**
	 * Get the Primary Key value
	 * @return String  Primary Key value
	 */
	@Override
	public String getPkValue() {
		return "'" + this.getUsername() + "'";
	}
}

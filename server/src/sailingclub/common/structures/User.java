package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * this class represent the user which can be a {@code Member} or a 
 * {@code Employee}, the user can login into the system and use the proper
 * GUI.
 */
public class User implements Insertable, Serializable, Removable{
	private static final long serialVersionUID = 1L;
	/**the user username*/
	private String username;
	/**the user real name*/
	private String name;
	/**the user surname*/
	private String surname;
	/**the user address*/
	private String address;
	/**the user fiscal code*/
	private String fiscalCode;
	/**the user type (member or employee)*/
	private String userType;
	/**the user password*/
	private String password;
	/**the membership fee only for members*/
	private MembershipFee fee;
	
	/**
	 * User constructor
	 * @param username the user's username
	 * @param name the user's name
	 * @param address the user's address
	 * @param fiscalCode the user's fiscal code
	 * @param userType  can be member or employee
	 * @param password  should to be hashed with md5 encryption
	 * @param fee  Membership fee
	 * @param surname the user's surname 
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
	 * @param username the user's username
	 * @param password  shuld to be hashed with md5 encription
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * User constructor
	 * @param username the user's username
	 */
	public User(String username) {
		this.username = username;
	}
	
	/**
	 * returns the username of the user
	 * @return username the username of the user
	 */
	public String getUsername() { 
		return username; 
	}
	
	/**
	 * Returns the real name of the user
	 * @return name the real name of the user
	 */
	public String getName() { 
		return name; 
	}
	
	/**
	 * Returns the lastname of the user
	 * @return surname the lastname of the user
	 */
	public String getSurname() { 
		return surname; 
	}
	
	/**
	 * Returns the address of the user
	 * @return address the address of the user
	 */
	public String getAddress() { 
		return address; 
	}
	
	/**
	 * Return the fiscal code of the user
	 * @return fiscalCode the fiscal code of the user
	 */
	public String getFiscalCode() {
		return fiscalCode; 
	}
	
	/**
	 * Returns the user type (Member/Employee)
	 * @return userType type of user
	 */
	public String getUserType() { 
		return userType;
	}
	
	/**
	 * Returns the user's password
	 * @return password the user's password
	 */
	public String getPassword() {
		return password; 
	}
	
	/**
	 * Returns the membership fee for the user (only if user is a mameber)
	 * @return fee the membership fee for the user
	 */
	public MembershipFee getMembershipFee() { 
		return fee; 
	}

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
	 * Get String[] with value of the class elements
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

package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDateTime;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * Notification class
 */
public class Notification implements Serializable, Removable, Insertable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String idMember;
	private String text;
	private LocalDateTime dateTime;
	
	/**
	 * Notification constructor
	 * @param id  id notifcation
	 * @param idMember  member's id
	 * @param text  notification's text
	 * @param dateTime  notification's date
	 */
	public Notification(int id, String idMember, String text, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.idMember = idMember;
		this.text = text;
		this.dateTime = dateTime;
	}
	/**
	 * Notification constructor
	 * @param idMember  member's id
	 * @param text  notification's text
	 * @param dateTime  notification's date
	 */
	public Notification(String idMember, String text, LocalDateTime dateTime) {
		super();
		this.idMember = idMember;
		this.text = text;
		this.dateTime = dateTime;
	}
	
	/**
	 * Get id 
	 * @return id  Notification id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Get id member
	 * @return idMember  member's id
	 */
	public String getIdMember() {
		return idMember;
	}
	/**
	 * Get text
	 * @return text  notification text
	 */
	public String getText() {
		return text;
	}
	/**
	 * Get date 
	 * @return dateTime  dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"id_member", "text", "date_time"};
	}
	
	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "notification";
	}
	
	/**
	 * Get printable String with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.idMember + "'", "'" + this.text + "'", "'" + this.dateTime.toString() + "'"};
	}
	
	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPk() {
		return "id";
	}
	
	/**
	 * Get the Primary Key value
	 * @return String  Primary Key value
	 */
	@Override
	public String getPkValue() {
		return Integer.toString(this.id);
	}
}

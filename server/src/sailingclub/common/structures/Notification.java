package sailingclub.common.structures;

import java.io.Serializable;
import java.time.LocalDateTime;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * This class represent a notification structure, the notifications
 * are used to notify a member for payments
 * @see User
 */
public class Notification implements Serializable, Removable, Insertable{
	private static final long serialVersionUID = 1L;
	/**notification id*/
	private int id;
	/**the member for who the notification is*/
	private String idMember;
	/**notification text*/
	private String text;
	/**timestamp for the notification*/
	private LocalDateTime dateTime;
	
	/**
	 * Notification constructor
	 * @param id the id for the notifcation
	 * @param idMember the member for who the notification is
	 * @param text notification's text
	 * @param dateTime notification's datetime
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
	 * Returns the id for the notification
	 * @return id  the id for the notification
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the member id which the notification is sended to
	 * @return idMember the member id which the notification is sended to
	 */
	public String getIdMember() {
		return idMember;
	}
	
	/**
	 * Returns the notification text
	 * @return text the notification text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Returns the date time (timestamp) for the notification
	 * @return dateTime the date time (timestamp) for the notification
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
	 * Get String[] with value of the class elements
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

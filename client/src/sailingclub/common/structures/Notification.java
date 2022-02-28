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
	 * @param idMemer  member's id
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
	 * @param idMemer  member's id
	 * @param text  notification's text
	 * @param dateTime  notification's date
	 */
	public Notification(String idMember, String text, LocalDateTime dateTime) {
		super();
		this.idMember = idMember;
		this.text = text;
		this.dateTime = dateTime;
	}
	
	public int getId() {
		return id;
	}

	public String getIdMember() {
		return idMember;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String[] getAttributes() {
		return new String[]{"id_member", "text", "date_time"};
	}
	
	@Override
	public String getInstanceName() {
		return "notification";
	}
	
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.idMember + "'", "'" + this.text + "'", "'" + this.dateTime.toString() + "'"};
	}
	
	@Override
	public String getPk() {
		return "id";
	}
	
	@Override
	public String getPkValue() {
		return Integer.toString(this.id);
	}
}

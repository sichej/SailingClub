package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * Is an implementation of a boat data structure
 * the boat is owned by the {@code Member}
 */
public class Boat implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	/**the boat id*/
	private int id;
	/**the boat name*/
	private String name;
	/**the boat length*/
	private double length;
	/**the boat owner*/
	private String idMember;
	/**the boat annual storage fee*/
	private BoatStorageFee boatStorageFee;
	/**the picture file name*/
	private String pictureName;
	/**the picture as byte array*/
	private byte[] picture;
	
	/**
	 * Boat constructor
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember  username of the owner
	 */
	public Boat(String name, double length, String idMember) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = null;
	}
	
	/**
	 * Boat constructor
	 * @param id  boat's id
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember username of the owner
	 * @param boatStorageFee  boat's storage fee
	 */
	public Boat(int id, String name, double length, String idMember, BoatStorageFee boatStorageFee) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = boatStorageFee;
	}
	
	/**
	 * Boat constructor
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember  username of the owner
	 * @param pictureName  name of the boat's picture 
	 */
	public Boat(String name, double length, String idMember, String pictureName) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = null;
		this.pictureName = pictureName;
	}
	
	/**
	 * Boat constructor
	 * @param id boat's id
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember  username of the owner
	 * @param pictureName  name of the boat's picture 
	 * @param picture  Picture of the boat
	 * @param boatStorageFee  boat storage fee
	 */
	public Boat(int id, String name, double length, String idMember, String pictureName, byte[] picture,BoatStorageFee boatStorageFee) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = boatStorageFee;
		this.pictureName = pictureName;
		this.picture = picture;
	}
	
	/**
	 * Boat constructor
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember  username of the owner
	 * @param picture  name of the boat's picture 
	 * @param pictureName  name of the boat's picture 
	 */
	public Boat(String name, double length, String idMember, String pictureName, byte[] picture) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.pictureName = pictureName;
		this.picture = picture;
	}
	
	/**
	 * Boat constructor
	 * @param id boat's id
	 */
	public Boat(int id) {
		this.id = id;
	}
	
	/**
	 * returns the boat id
	 * @return id  the boat id
	 */
	public int getId() { 
		return id; 
	}
	
	/**
	 * returns the name of the boat
	 * @return name  the name of the boat
	 */
	public String getName() { 
		return name; 
	}
	
	/**
	 * returns the length of the boat
	 * @return length  boat's length
	 */
	public double getLength() { 
		return length; 
	}
	/**
	 * returns the owner of the boat
	 * @return idMember  username of the owner
	 */
	public String getIdMember() { 
		return idMember; 
	}
	
	/**
	 * returns the picture of the boat
	 * @return picture of the boat as byte[]
	 */
	public byte[] getPicture() { 
		return picture; 
	}
	
	/**
	 * returns the boat picture file name
	 * @return pictureName the boat picture file name
	 */
	public String getPictureName() { 
		return pictureName; 
	}
	
	/**
	 * Get Boat Storage Fee
	 * @return boatStorageFee
	 */
	public BoatStorageFee getBoatStorageFee() { 
		return boatStorageFee; 
	}

	/**
	 * Set boat storage fee
	 * @param boatStorageFee  boat storage fee
	 */
	public void setBoatStorageFee(BoatStorageFee boatStorageFee) { 
		this.boatStorageFee = boatStorageFee; 
	}

	/**
	 * Get the attributes of the class
	 * @return String  Array String with params
	 */
	@Override
	public String[] getAttributes() {
		return new String[]{"name", "length", "id_member", "picture"};
	}

	/**
	 * Get the database table name
	 * @return String  database table name
	 */
	@Override
	public String getInstanceName() {
		return "boat";
	}

	/**
	 * Gets String[] with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.name + "'", Double.toString(this.length), "'" + this.idMember + "'",  "'" + this.pictureName +"'"};
	}
	
	@Override
	/**
	 * returns the primary key name
	 * @return String  Primary key name
	 */
	public String getPk() {
		return "id";
	}

	/**
	 * Get primary key of the table
	 * @return String  Primary key
	 */
	@Override
	public String getPkValue() {
		return Integer.toString(this.id);
	}
}

package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

/**
 * Boat class
 */
public class Boat implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double length;
	private String idMember;
	private BoatStorageFee boatStorageFee;
	private String pictureName;
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
	 * @param idMember  username of the owner
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
	 * @param name  boat's name
	 * @param length  boat's length
	 * @param idMember  username of the owner
	 * @param picture  name of the boat's picture 
	 * @param boatStorageFee  boat's storage fee
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
	 * Get id
	 * @return id  boat's id
	 */
	public int getId() { return id; }
	/**
	 * Get Name
	 * @return name  boat's name
	 */
	public String getName() { return name; }
	/**
	 * Get length
	 * @return length  boat's length
	 */
	public double getLength() { return length; }
	/**
	 * Get id Member
	 * @return idMember  username of the owner
	 */
	public String getIdMember() { return idMember; }
	/**
	 * Get Picture
	 * @return picture  
	 */
	public byte[] getPicture() { return picture; }
	/**
	 * Get Picture's name
	 * @return pictureName
	 */
	public String getPictureName() { return pictureName; }
	/**
	 * Get Boat Storage Fee
	 * @return boatStorageFee
	 */
	public BoatStorageFee getBoatStorageFee() { return boatStorageFee; }

	/**
	 * Set boat storage fee
	 * @param boatStorageFee
	 */
	public void setBoatStorageFee(BoatStorageFee boatStorageFee) { this.boatStorageFee = boatStorageFee; }

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
	 * Get printable String with value of the class elements
	 * @return String  value of the class elements
	 */
	@Override
	public String[] getValues() {
		return new String[]{"'" + this.name + "'", Double.toString(this.length), "'" + this.idMember + "'",  "'" + this.pictureName +"'"};
	}
	
	@Override
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

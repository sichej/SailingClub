package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;

public class Boat implements Insertable, Removable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double length;
	private String idMember;
	private BoatStorageFee boatStorageFee;
	private String pictureName;
	private byte[] picture;
	
	public Boat(String name, double length, String idMember) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = null;
	}

	public Boat(String name, double length, String idMember, String pictureName) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = null;
		this.pictureName = pictureName;
	}
	
	public Boat(int id, String name, double length, String idMember, String pictureName, byte[] picture,BoatStorageFee boatStorageFee) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.boatStorageFee = boatStorageFee;
		this.pictureName = pictureName;
		this.picture = picture;
	}
	
	public Boat(String name, double length, String idMember, String pictureName, byte[] picture) {
		this.name = name;
		this.length = length;
		this.idMember = idMember;
		this.pictureName = pictureName;
		this.picture = picture;
	}
	
	public Boat(int id) {
		this.id = id;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public double getLength() { return length; }
	public String getIdMember() { return idMember; }
	public byte[] getPicture() { return picture; }
	public String getPictureName() { return pictureName; }

	public BoatStorageFee getBoatStorageFee() { return boatStorageFee; }

	public void setBoatStorageFee(BoatStorageFee boatStorageFee) { this.boatStorageFee = boatStorageFee; }

	@Override
	public String[] getAttributes() {
		return new String[]{"name", "length", "id_member", "picture"};
	}

	@Override
	public String getInstanceName() {
		return "boat";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'" + this.name + "'", Double.toString(this.length), "'" + this.idMember + "'",  "'" + this.pictureName +"'"};
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

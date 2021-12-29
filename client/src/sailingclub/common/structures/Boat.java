package sailingclub.common.structures;

import java.io.Serializable;

import sailingclub.common.Insertable;

public class Boat implements Insertable, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double length;
	private String idMember;
	
	public Boat(int id, String name, double length, String idMember) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.idMember = idMember;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public double getLength() { return length; }
	public String getIdMember() { return idMember; }

	@Override
	public String[] getAttributes() {
		return new String[]{"name", "length", "id_member"};
	}

	@Override
	public String getInstanceName() {
		return "boat";
	}

	@Override
	public String[] getValues() {
		return new String[]{"'" + this.name + "'", Double.toString(this.length), "'" + this.idMember + "'"};
	}
}

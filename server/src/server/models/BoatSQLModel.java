package server.models;

import java.io.Serializable;

public class BoatSQLModel implements SQLModel, Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Double length;
	private String id_member;
	
	public BoatSQLModel(int id) {
		this.id = id;
		this.name = null;
		this.length = null;
		this.id_member = null;
	}
	
	public BoatSQLModel(String name, Double length, String id_member) {
		this.id = null;
		this.name = name;
		this.length = length;
		this.id_member = id_member;
	}
	
	@Override
	public String[] getSQLAttributes() {
		return new String[]{"name", "length", "id_member"};
	}

	@Override
	public String getSQLTableName() {
		return "boat";
	}

	@Override
	public String[] getSQLValues() {
		return new String[]{"'" + this.name + "'", this.length.toString(), "'" + this.id_member + "'"};
	}

	@Override
	public Class<?>[] getSQLTypes() {
		return new Class[]{this.name.getClass(), this.length.getClass(), this.id_member.getClass()};
	}

	@Override
	public Object getSQLPrimaryKeyValue() {
		return this.id;
	}

	@Override
	public String getSQLPrimaryKeyName() {
		return "id";
	}
}

package server;

public class BoatSQLModel implements SQLModel{
	private Integer id;
	private String name;
	private Double length;
	private String id_member;
	
	public BoatSQLModel(int id, String name, Double length, String id_member) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.id_member = id_member;
	}
	
	@Override
	public String[] getSQLAttributes() {
		return new String[]{"id", "name", "length", "id_member"};
	}

	@Override
	public String getSQLTableName() {
		return "boat";
	}

	@Override
	public String[] getSQLValues() {
		return new String[]{this.id.toString(), "'" + this.name + "'", this.length.toString(), "'" + this.id_member + "'"};
	}

	@Override
	public Class<?>[] getSQLTypes() {
		return new Class[]{this.id.getClass(), this.name.getClass(), this.length.getClass(), this.id_member.getClass()};
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

package sailingclub.common;

public interface Translatable{
	public String[] getSQLAttributes();
	public String getSQLTableName();
	public String[] getSQLValues();
	public Class<?>[] getSQLTypes();
	public Object getSQLPrimaryKeyValue();
	public String getSQLPrimaryKeyName();
}

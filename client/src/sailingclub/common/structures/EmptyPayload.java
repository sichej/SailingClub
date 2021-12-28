package sailingclub.common.structures;

import java.io.Serializable;
import sailingclub.common.Translatable;

public class EmptyPayload implements Translatable, Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public String[] getSQLAttributes() { return null; }

	@Override
	public String getSQLTableName() { return null; }

	@Override
	public String[] getSQLValues() { return null; }

	@Override
	public Class<?>[] getSQLTypes() { return null; }

	@Override
	public Object getSQLPrimaryKeyValue() { return null; }

	@Override
	public String getSQLPrimaryKeyName() { return null; }
}

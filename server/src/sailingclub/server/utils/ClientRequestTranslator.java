package sailingclub.server.utils;

import sailingclub.common.Actions;
import sailingclub.common.Request;
import sailingclub.common.Translatable;
import sailingclub.common.structures.EmptyPayload;

public class ClientRequestTranslator {

	public ClientRequestTranslator() {};
	
	public String translate(Request request) {
		String query = "";
		Translatable model = (Translatable)request.getPayload();
		if(model instanceof EmptyPayload) return "";
		
		switch(request.getHeader()) {
			case Actions.INSERT: 
				query += "INSERT INTO " + model.getSQLTableName() + "(" 
					  + String.join(",", model.getSQLAttributes()) + ") VALUES ("		
					  + String.join(",", model.getSQLValues()) + ");";
				break;
				
			case Actions.DELETE:
				query += "DELETE FROM " + model.getSQLTableName() + " WHERE "
				      + model.getSQLPrimaryKeyName() + " = " + model.getSQLPrimaryKeyValue() + ";";
			break;
			case Actions.SELECT:
				break;
			case Actions.LOGIN:
				query += "SELECT * FROM user WHERE username = " + model.getSQLPrimaryKeyValue() + " and password = " + model.getSQLValues()[6] + ";";
				break;
		}	
		return query;
	}
}

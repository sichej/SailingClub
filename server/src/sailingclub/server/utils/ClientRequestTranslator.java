package sailingclub.server.utils;

import sailingclub.common.Actions;
import sailingclub.common.Request;
import sailingclub.common.Translatable;

public class ClientRequestTranslator {

	public ClientRequestTranslator() {};
	
	public String translate(Request request) {
		String query = "";
		Translatable model = (Translatable)request.getPayload();
		
		switch(request.getAction()) {
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
		}	
		
		System.out.println(query);
		
		return query;
	}
}

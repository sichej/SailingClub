package server.api;

import server.SQLModel;

public class ClientRequestTranslator {

	public ClientRequestTranslator() {};
	
	public String translate(Request request) {
		String query = "";
		SQLModel model = (SQLModel)request.getPayload();
		
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
		}	
		
		System.out.println(query);
		
		return query;
	}
}

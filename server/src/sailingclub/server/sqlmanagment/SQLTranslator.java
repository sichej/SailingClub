package sailingclub.server.sqlmanagment;

import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Insertable;
import sailingclub.common.structures.User;

public class SQLTranslator {

	public SQLTranslator() {};
	
	public String RequestToSQL(Request request) {
		String query = "";
		Object model = request.getPayload();

		switch(request.getHeader()) {
			case Constants.INSERT: 
				Insertable insertableModel = (Insertable)model;
				query += "INSERT INTO " + insertableModel.getInstanceName() + "(" 
					  + String.join(",", insertableModel.getAttributes()) + ") VALUES ("		
					  + String.join(",", insertableModel.getValues()) + ");";
				break;
			/*case Actions.DELETE:  //bisogna decidere se serviranno solo delete by id faremo un interfaccia deletable dove ci saranno i metodi geit ecc..
				query += "DELETE FROM " + model.getSQLTableName() + " WHERE "
				      + model.getSQLPrimaryKeyName() + " = " + model.getSQLPrimaryKeyValue() + ";";
			break;*/
			case Constants.LOGIN:
				User user = (User)model;
				query += "SELECT * FROM user WHERE username = '" + user.getUsername() + "' and password = '" + user.getPassword()+ "';";
				break;
			case Constants.CLOSE_CONNECTION: break;
		}	
		return query;
	}
	
	public Response SQLToResponse(String queryResult) {
		return new Response(0,null);
	}
}

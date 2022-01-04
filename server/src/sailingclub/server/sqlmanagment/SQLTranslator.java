package sailingclub.server.sqlmanagment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Insertable;
import sailingclub.common.structures.User;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.Boat;

public class SQLTranslator {
	private int lastRequest;
	
	public SQLTranslator() {
		this.lastRequest = -1;
	};
	
	public String RequestToSQL(Request request) throws ClassNotFoundException {
		String query = "";
		Object model = request.getPayload();
		
		this.lastRequest = request.getHeader();

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
			case Constants.GET_BOAT_BY_ID:
				Boat boat = (Boat)model;
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND id_boat = " + boat.getId() + " ;";
				break;
			case Constants.PAY_BOAT_STORAGE_FEE:
				BoatStorageFee bsf = ((Boat)model).getBoatStorageFee();
				query += "UPDATE boat_storage_fee SET payment_date = " + LocalDate.now() + " , expiration_date = " + bsf.getExpirationDate().plusYears(1) + " WHERE id_boat = " + bsf.getId() + " ;";
				break;
			default: throw new ClassNotFoundException();
		}	
		return query;
	}
	
	public Response SQLToResponse(ResultSet queryResult) throws SQLException {
		Response response = null;
		switch(this.lastRequest) {
		case Constants.INSERT:
			response = new Response(Constants.SUCCESS,queryResult.getInt(1));
			break;
		case Constants.GET_BOAT_BY_ID:
			response = new Response(Constants.SUCCESS,queryResult.getInt(1));
			break;
		}
		
		if(response == null) throw new SQLException();
		
		return response;
	}
}

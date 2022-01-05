package sailingclub.server.sqlmanagment;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Insertable;
import sailingclub.common.structures.User;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Boat;

public class SQLTranslator {
	private int lastRequest;
	private DateTimeFormatter dateFormatter;
	
	public SQLTranslator() {
		this.lastRequest = -1;
		this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
				
				if(!Arrays.asList(insertableModel.getAttributes()).contains(insertableModel.getPk()))  //se la table non è in AI
					query += "SELECT LAST_INSERT_ID() AS last_id;";
				break;
			/*case Actions.DELETE:  //bisogna decidere se serviranno solo delete by id faremo un interfaccia deletable dove ci saranno i metodi geit ecc..
				query += "DELETE FROM " + model.getSQLTableName() + " WHERE "
				      + model.getSQLPrimaryKeyName() + " = " + model.getSQLPrimaryKeyValue() + ";";
			break;*/
			case Constants.LOGIN:
				User user = (User)model;
				query += "SELECT * FROM user WHERE username = '" + user.getUsername() + "' and password = '" 
					  + user.getPassword()+ "';";
				break;
			case Constants.CLOSE_CONNECTION: break;
			case Constants.GET_BOAT_BY_ID:
				Boat boat = (Boat)model;
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND bt.id = " 
					  + boat.getId() + " ;";
				break;
			case Constants.PAY_BOAT_STORAGE_FEE:
				BoatStorageFee bsf = ((Boat)model).getBoatStorageFee();
				query += "UPDATE boat_storage_fee SET payment_date = '" + LocalDate.now() + "' , expiration_date = '"
					  + bsf.getExpirationDate().plusYears(1) + "' WHERE id_boat = " + ((Boat)model).getId() + "; " 
					  + "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND id_boat = " 
					  + ((Boat)model).getId() + ";";
				break;
			default: throw new ClassNotFoundException();
		}	
		return query;
	}
	
	public Response SQLToResponse(List<Map<String, String>> queryResult) throws Exception {
		Response response = null;
		switch(this.lastRequest) {
		case Constants.INSERT:
			if(queryResult != null)
				response = new Response(Constants.SUCCESS, queryResult.get(0).get("last_id"));
			else 
				response = new Response(Constants.SUCCESS, new EmptyPayload());
			break;
		case Constants.PAY_BOAT_STORAGE_FEE:
		case Constants.GET_BOAT_BY_ID:
			Map<String, String> wr = queryResult.get(0);
			for (Map.Entry<String, String> entry : wr.entrySet()) {
			    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
			}
			LocalDate pDate = LocalDate.parse(wr.get("payment_date"), dateFormatter);
			LocalDate eDate = LocalDate.parse(wr.get("expiration_date"), dateFormatter);
			BoatStorageFee fee = new BoatStorageFee(Integer.parseInt(wr.get("id")),pDate,eDate,Double.parseDouble(wr.get("amount")),Integer.parseInt(wr.get("id_boat")));
			Boat boat = new Boat(Integer.parseInt(wr.get("id_boat")),wr.get("name"),Double.parseDouble(wr.get("length")),wr.get("id_member"),fee);
			response = new Response(Constants.SUCCESS,boat);
			break;
		}
		
		if(response == null) throw new SQLException();
		
		return response;
	}
}

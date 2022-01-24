package sailingclub.server.sqlmanagment;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.Insertable;
import sailingclub.common.Removable;
import sailingclub.common.structures.User;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.MembershipFee;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.Boat;

public class SQLTranslator {
	private int lastRequest;
	private DateTimeFormatter dateFormatter;
	
	public SQLTranslator() {
		this.lastRequest = -1;
		this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	};
	
	public String RequestToSQL(Request request) throws RequestToSQLException {
		String query = "";
		Object model = request.getPayload();
		
		this.lastRequest = request.getHeader();

		switch(request.getHeader()) {
			case Constants.INSERT:
				Insertable insertableModel = (Insertable)model;
				query += "INSERT INTO " + insertableModel.getInstanceName() + "(" 
					  + String.join(",", insertableModel.getAttributes()) + ") VALUES ("		
					  + String.join(",", insertableModel.getValues()) + ");";
				
				if(!Arrays.asList(insertableModel.getAttributes()).contains(insertableModel.getPk()))  //se la table non � in AI
					query += "SELECT LAST_INSERT_ID() AS last_id;";
				break;
			case Constants.DELETE:
				Removable deletableModel = (Removable) model;
				query += "DELETE FROM " + deletableModel.getInstanceName() + " WHERE "
				      + deletableModel.getPk() + " = " + deletableModel.getPkValue() + ";";
			break;
			case Constants.LOGIN:
				User user = (User)model;
				query += "SELECT * FROM user, membership_fee f WHERE username = '" + user.getUsername() + "' AND password = '" 
					  + user.getPassword()+ "' AND f.id_member = user.username";
				break;
			case Constants.CLOSE_CONNECTION: break;
			case Constants.GET_BOAT_BY_ID:
				Boat boat = (Boat)model;
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND bt.id = " 
					  + boat.getId() + " ;";
				break;
			case Constants.GET_RACES:
				query += "SELECT * FROM race";
				break;
			case Constants.PAY_BOAT_STORAGE_FEE:
				BoatStorageFee bsf = ((Boat)model).getBoatStorageFee();
				query += "UPDATE boat_storage_fee SET payment_date = '" + LocalDate.now() + "' , expiration_date = '"
					  + bsf.getExpirationDate().plusYears(1) + "' WHERE id_boat = " + ((Boat)model).getId() + "; " 
					  + "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND bs.id_boat = " 
					  + ((Boat)model).getId() + ";";
				break;
			case Constants.PAY_MEMBERSHIP_FEE:
				MembershipFee msf = ((User)model).getMembershipFee();
				query += "UPDATE membership_fee SET payment_date = '" + LocalDate.now() + "' , expiration_date = '"
						  + msf.getExpirationDate().plusYears(1) + "' WHERE id_member = '" + ((User)model).getUsername() + "'; " 
						  + "SELECT * FROM membership_fee ms, user u WHERE u.username = ms.id_member AND ms.id_member = '" 
						  + ((User)model).getUsername() + "';";
				break;
			case Constants.GET_BOATS:
				user = (User)model;
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND id_member = '" + user.getUsername() + "' ;";
				break;
			default: throw new RequestToSQLException();
		}	
		return query;
	}
	
	public Response SQLToResponse(List<Map<String, String>> queryResult) throws SQLToResponseException, IOException {
		Response response = null;
		switch(this.lastRequest) {
		case Constants.INSERT:
			if(queryResult != null)
				response = new Response(Constants.SUCCESS, queryResult.get(0).get("last_id"));
			else 
				response = new Response(Constants.SUCCESS, new EmptyPayload("No id to be returned!"));
			break;
		case Constants.DELETE:
				response = new Response(Constants.SUCCESS, new EmptyPayload("Item deleted!"));
			break;
		case Constants.PAY_BOAT_STORAGE_FEE:
		case Constants.GET_BOAT_BY_ID:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Boat not found!"));
				break;
			}
			
			Map<String, String> fRes = queryResult.get(0);
			byte[] img = Utils.toByteArray(ImageIO.read(new File("images/" + fRes.get("picture"))),"jpg");
			LocalDate pDate = LocalDate.parse(fRes.get("payment_date"), dateFormatter);
			LocalDate eDate = LocalDate.parse(fRes.get("expiration_date"), dateFormatter);
			BoatStorageFee fee = new BoatStorageFee(Integer.parseInt(fRes.get("id")),pDate,eDate,Double.parseDouble(fRes.get("amount")),Integer.parseInt(fRes.get("id_boat")));
			Boat boat = new Boat(Integer.parseInt(fRes.get("id_boat")),fRes.get("name"),Double.parseDouble(fRes.get("length")),fRes.get("id_member"),fRes.get("picture"),img,fee);
			response = new Response(Constants.SUCCESS,boat);
			break;
		case Constants.GET_BOATS:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Boat not found!"));
				break;
			}

			ArrayList<Boat> boats = new ArrayList<Boat>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> bRes = queryResult.get(i);
				img = Utils.toByteArray(ImageIO.read(new File("images/" + bRes.get("picture"))),"jpg");
				pDate = LocalDate.parse(bRes.get("payment_date"), dateFormatter);
				eDate = LocalDate.parse(bRes.get("expiration_date"), dateFormatter);
				fee = new BoatStorageFee(Integer.parseInt(bRes.get("id")),pDate,eDate,Double.parseDouble(bRes.get("amount")),Integer.parseInt(bRes.get("id_boat")));
				boat = new Boat(Integer.parseInt(bRes.get("id_boat")),bRes.get("name"),Double.parseDouble(bRes.get("length")),bRes.get("id_member"),bRes.get("picture"),img,fee);
				boats.add(boat);
			}
			response = new Response(Constants.SUCCESS,boats);
			break;
		
		case Constants.GET_RACES:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Races not found!"));
				break;
			}
			ArrayList<Race> races = new ArrayList<Race>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> rRes = queryResult.get(i);
				//img = Utils.toByteArray(ImageIO.read(new File("images/" + bRes.get("picture"))),"jpg");
				LocalDate date = LocalDate.parse(rRes.get("date"), dateFormatter);
				//eDate = LocalDate.parse(rRes.get("expiration_date"), dateFormatter);
				//fee = new BoatStorageFee(Integer.parseInt(rRes.get("id")),pDate,eDate,Double.parseDouble(rRes.get("amount")),Integer.parseInt(rRes.get("id_boat")));
				Race race = new Race(date,Double.parseDouble(rRes.get("price")));
				races.add(race);
			}
			response = new Response(Constants.SUCCESS,races);
			break;
		case Constants.PAY_MEMBERSHIP_FEE:
		case Constants.LOGIN:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Wrong login!"));
				break;
			}
			
			Map<String, String> uRes = queryResult.get(0);
			LocalDate pmDate = LocalDate.parse(uRes.get("payment_date"), dateFormatter);
			LocalDate emDate = LocalDate.parse(uRes.get("expiration_date"), dateFormatter);
			MembershipFee mfee = new MembershipFee(Integer.parseInt(uRes.get("id")), pmDate, emDate, Double.parseDouble(uRes.get("price")), uRes.get("id_member"));
			User user = new User(uRes.get("username"), uRes.get("name"), uRes.get("surname"), uRes.get("address"), uRes.get("fiscal_code"), uRes.get("user_type"), uRes.get("password"),mfee);
			response = new Response(Constants.SUCCESS, user);
			break;
		
	}
		
		if(response == null) throw new SQLToResponseException();
		
		return response;
	}
}

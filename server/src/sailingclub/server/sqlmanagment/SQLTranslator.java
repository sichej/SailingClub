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
import sailingclub.common.structures.CreditCard;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.MembershipFee;
import sailingclub.common.structures.Payment;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.RaceParticipation;
import sailingclub.common.structures.BankTransfer;
import sailingclub.common.structures.Boat;

public class SQLTranslator {
	private int lastRequest;
	private DateTimeFormatter dateFormatter;
	private User loggedUser;
	
	public SQLTranslator() {
		this.lastRequest = -1;
		this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	};
	
	public String requestToSql(Request request) throws RequestToSQLException, IOException {
		String query = "";
		Object model = request.getPayload();
		
		this.lastRequest = request.getHeader();

		switch(request.getHeader()) {
			case Constants.INSERT:
				Insertable insertableModel = (Insertable)model;
				query += "INSERT INTO " + insertableModel.getInstanceName() + "(" 
					  + String.join(",", insertableModel.getAttributes()) + ") VALUES ("		
					  + String.join(",", insertableModel.getValues()) + ");";
				
				if(!Arrays.asList(insertableModel.getAttributes()).contains(insertableModel.getPk()))  //se la table non in AI
					query += "SELECT LAST_INSERT_ID() AS last_id;";
				if(model instanceof Boat && !((Boat)model).getPictureName().equals("")) {
					byte[] img = ((Boat)model).getPicture();
					File f = new File("images/" + ((Boat)model).getPictureName());
					String ext = f.getName().substring(f.getName().lastIndexOf('.') + 1);
					ImageIO.write(Utils.toBufferedImage(img), ext , f);
				}
				break;
			case Constants.DELETE:
				Removable deletableModel = (Removable) model;
				query += "DELETE FROM " + deletableModel.getInstanceName() + " WHERE "
				      + deletableModel.getPk() + " = " + deletableModel.getPkValue() + ";";
			break;
			case Constants.LOGIN:
				User user = (User)model;
				query += "SELECT * FROM user LEFT JOIN membership_fee ON membership_fee.id_member = user.username WHERE user.username = '" 
					  + user.getUsername() + "' AND user.password = '" + user.getPassword()+ "'";
				break;
			case Constants.CLOSE_CONNECTION: break;
			case Constants.GET_BOAT_BY_ID:
				Boat boat = (Boat)model;
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND bt.id = " 
					  + boat.getId() + " ;";
				break;
			case Constants.GET_CREDIT_CARDS:
				query += "SELECT * FROM credit_card WHERE id_member = '" + loggedUser.getUsername() + "';";
				break;
			case Constants.GET_BANK_TRANSFERS:
				query += "SELECT * FROM bank_transfer WHERE id_member = '" + loggedUser.getUsername() + "';";
				break;
			case Constants.GET_RACES:
				query += "SELECT * FROM race";
				break;
			case Constants.GET_RACES_PARTICIPATIONS:
				query += "SELECT * FROM race r ,race_participation rp WHERE r.id = rp.id_race AND rp.id_member = '" + this.loggedUser.getUsername() + "';";
				break;
			case Constants.GET_RACES_SUB:
				query += "SELECT count(*) AS subs FROM race_participation rp WHERE rp.id_race = " + (String)model + " GROUP BY rp.id_race;";
				break;
			case Constants.PAY_BOAT_STORAGE_FEE:
				BoatStorageFee bsf = ((Boat)model).getBoatStorageFee();
				query += "UPDATE boat_storage_fee SET payment_date = '" + LocalDate.now() + "' , expiration_date = '"
					  + bsf.getExpirationDate().plusYears(1) + "' WHERE id_boat = " + ((Boat)model).getId() + "; " 
					  + "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND bs.id_boat = " 
					  + ((Boat)model).getId() + ";";
				break;
			case Constants.PAY_MEMBERSHIP_FEE:
				MembershipFee msf = this.loggedUser.getMembershipFee();
				query += "UPDATE membership_fee SET payment_date = '" + LocalDate.now() + "' , expiration_date = '"
						  + msf.getExpirationDate().plusYears(1) + "' WHERE id_member = '" + this.loggedUser.getUsername() + "'; " 
						  + "SELECT * FROM membership_fee ms, user u WHERE u.username = ms.id_member AND ms.id_member = '" 
						  + this.loggedUser.getUsername() + "';";
				break;
			case Constants.GET_BOATS:
				query += "SELECT * FROM boat_storage_fee bs, boat bt WHERE bt.id = bs.id_boat AND id_member = '" + this.loggedUser.getUsername() + "' ;";
				break;
			case Constants.GET_ALL_BOATS:
				query += "SELECT * FROM boat_storage_fee bs, boat b WHERE bs.id_boat = b.id;";
				break;
			case Constants.LOGOUT:
				this.loggedUser = null;
				query += "SELECT null";
				break;
			case Constants.GET_SUBSCRIPTED_BOAT:
				int rid = ((Race)model).getId();
				query += "SELECT * FROM race_participation rp WHERE rp.id_race = " + rid + " AND rp.id_member = '" + this.loggedUser.getUsername() + "';";
				break;
			case Constants.GET_MEMBERS:
				query += "SELECT username FROM user WHERE user_type = 'member';";
				break;
			case Constants.GET_MEMBER_BY_USERNAME:
				query += "SELECT * FROM user, membership_fee f WHERE username = '" + (String)model + "' AND f.id_member = user.username";
				break;
			case Constants.GET_PAYMENTS:
				query += "SELECT * FROM payment;";
				break;
			case Constants.UPDATE_RACE:
				Race race = ((Race)model);
				query += "UPDATE race SET date='" + race.getDate().toString() + "', price = " + race.getPrice() + ", name = '" + 
						  race.getName() + "' WHERE id = " + race.getId() + ";";
				break;
			case Constants.UPDATE_BOAT:
				Boat uBoat = ((Boat)model);
				BoatStorageFee uBsf = ((Boat)model).getBoatStorageFee();
				query += "UPDATE boat SET name='" + uBoat.getName() + "', length = " + uBoat.getLength() + "WHERE id = " + uBoat.getId() + ";";
				query += "UPDATE boat_storage_fee SET expiration_date='" + uBsf.getExpirationDate().toString() + "', amount = " + uBsf.getAmount() + "WHERE id = " + uBsf.getId() + ";";
				break;
			case Constants.UPDATE_MEMBER:
				User uMem = ((User)model);
				String oldUsername = uMem.getUsername().split(",")[0];
				String newUsername = uMem.getUsername().split(",")[1];
				query += "UPDATE user SET username = '" + newUsername + "', name='" + uMem.getName() + "', surname = '" + uMem.getSurname() +
						"' , address='" + uMem.getAddress() + "', fiscal_code='" + uMem.getFiscalCode()  + "' , password='" + uMem.getPassword() + 
						"' WHERE username = '" + oldUsername + "';";
				break;
			default: throw new RequestToSQLException();
		}	
		return query;
	}
	
	public Response sqlToResponse(List<Map<String, String>> queryResult) throws SQLToResponseException, IOException {
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
			File f = new File("images/" + fRes.get("picture"));
			String ext = f.getName().substring(f.getName().lastIndexOf('.') + 1);
			byte[] img;
			
			if(f.exists() && !f.isDirectory()) { 
				img = Utils.toByteArray(ImageIO.read(f),ext);
			}else {
				img = Utils.toByteArray(ImageIO.read(new File("images/generic.jpg")),"jpg");
			}
			
			LocalDate pDate = LocalDate.parse(fRes.get("payment_date"), dateFormatter);
			LocalDate eDate = LocalDate.parse(fRes.get("expiration_date"), dateFormatter);
			BoatStorageFee fee = new BoatStorageFee(Integer.parseInt(fRes.get("id")),pDate,eDate,Double.parseDouble(fRes.get("amount")),Integer.parseInt(fRes.get("id_boat")));
			Boat boat = new Boat(Integer.parseInt(fRes.get("id_boat")),fRes.get("name"),Double.parseDouble(fRes.get("length")),fRes.get("id_member"),fRes.get("picture"),img,fee);
			response = new Response(Constants.SUCCESS,boat);
			break;
		case Constants.GET_ALL_BOATS:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Boat not found!"));
				break;
			}
			
			ArrayList<Boat> boats = new ArrayList<Boat>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> bRes = queryResult.get(i);
				f = new File("images/" + bRes.get("picture"));
				ext = f.getName().substring(f.getName().lastIndexOf('.') + 1);
			
				if(f.exists() && !f.isDirectory()) { 
					img = Utils.toByteArray(ImageIO.read(f),ext);
				}else {
					img = Utils.toByteArray(ImageIO.read(new File("images/generic.jpg")),"jpg");
				}
				pDate = LocalDate.parse(bRes.get("payment_date"), dateFormatter);
				eDate = LocalDate.parse(bRes.get("expiration_date"), dateFormatter);
				fee = new BoatStorageFee(Integer.parseInt(bRes.get("id")),pDate,eDate,Double.parseDouble(bRes.get("amount")),Integer.parseInt(bRes.get("id_boat")));
				boat = new Boat(Integer.parseInt(bRes.get("id_boat")),bRes.get("name"),Double.parseDouble(bRes.get("length")),bRes.get("id_member"),bRes.get("picture"),img,fee);
				boats.add(boat);
			}
			response = new Response(Constants.SUCCESS,boats);
			break;
		case Constants.GET_BOATS:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Boat not found!"));
				break;
			}

			boats = new ArrayList<Boat>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> bRes = queryResult.get(i);
				
				f = new File("images/" + bRes.get("picture"));
				ext = f.getName().substring(f.getName().lastIndexOf('.') + 1);
				
				if(f.exists() && !f.isDirectory()) { 
					img = Utils.toByteArray(ImageIO.read(f),ext);
				}else {
					img = Utils.toByteArray(ImageIO.read(new File("images/generic.jpg")),"jpg");
				}

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
				LocalDate date = LocalDate.parse(rRes.get("date"), dateFormatter);
				Race race = new Race(Integer.parseInt(rRes.get("id")),date,Double.parseDouble(rRes.get("price")), rRes.get("name"));
				races.add(race);
			}
			response = new Response(Constants.SUCCESS,races);
			break;
		case Constants.GET_CREDIT_CARDS:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Credit cards not found!"));
				break;
			}
			
			ArrayList<CreditCard> cc = new ArrayList<CreditCard>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> cRes = queryResult.get(i);
				cc.add(new CreditCard(cRes.get("card_number"),Integer.parseInt(cRes.get("cvv")), LocalDate.parse(cRes.get("expiration_date"), dateFormatter), cRes.get("id_member")));
			}
			response = new Response(Constants.SUCCESS, cc);
			break;
		case Constants.GET_BANK_TRANSFERS:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Bank account not found!"));
				break;
			}
			
			ArrayList<BankTransfer> bnkt = new ArrayList<BankTransfer>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> bnRes = queryResult.get(i);
				bnkt.add(new BankTransfer(bnRes.get("iban"), bnRes.get("bank"), bnRes.get("id_member")));
			}
			response = new Response(Constants.SUCCESS, bnkt);
			break;
		case Constants.GET_RACES_SUB:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, "0");
				break;
			}
			response = new Response(Constants.SUCCESS, queryResult.get(0).get("subs"));
			break;
		case Constants.GET_RACES_PARTICIPATIONS:
			ArrayList<Race> usRaces = new ArrayList<Race>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> rRes = queryResult.get(i);
				LocalDate date = LocalDate.parse(rRes.get("date"), dateFormatter);
				Race race = new Race(Integer.parseInt(rRes.get("id")),date,Double.parseDouble(rRes.get("price")), rRes.get("name"));
				usRaces.add(race);
			}
			response = new Response(Constants.SUCCESS, usRaces);
			break;

		case Constants.GET_MEMBERS:
			ArrayList<String> members = new ArrayList<String>();
			for(int i = 0; i < queryResult.size(); i++){
				Map<String, String> mRes = queryResult.get(i);
				String member = mRes.get("username");
				members.add(member);
			}
			response = new Response(Constants.SUCCESS, members);
			break;
		case Constants.GET_SUBSCRIPTED_BOAT:
			response = new Response(Constants.SUCCESS, new Boat(Integer.parseInt(queryResult.get(0).get("id_boat"))));
			break;
		case Constants.GET_MEMBER_BY_USERNAME:
		if(queryResult.isEmpty()) {
			response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Wrong login!"));
			break;
		}
		
		Map<String, String> umRes = queryResult.get(0);
		LocalDate pmDate = LocalDate.parse(umRes.get("payment_date"), dateFormatter);
		LocalDate emDate = LocalDate.parse(umRes.get("expiration_date"), dateFormatter);
		MembershipFee mfee = new MembershipFee(Integer.parseInt(umRes.get("id")), pmDate, emDate, Double.parseDouble(umRes.get("price")), umRes.get("id_member"));
		User user = new User(umRes.get("username"), umRes.get("name"), umRes.get("surname"), umRes.get("address"), umRes.get("fiscal_code"), umRes.get("user_type"), umRes.get("password"),mfee);
		response = new Response(Constants.SUCCESS, user);
			break;
		case Constants.PAY_MEMBERSHIP_FEE:
		case Constants.LOGIN:
			if(queryResult.isEmpty()) {
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Wrong login!"));
				break;
			}
			
			Map<String, String> uRes = queryResult.get(0);
			mfee = null;
			if(uRes.get("id") != null) {
				pmDate = LocalDate.parse(uRes.get("payment_date"), dateFormatter);
				emDate = LocalDate.parse(uRes.get("expiration_date"), dateFormatter);
				mfee = new MembershipFee(Integer.parseInt(uRes.get("id")), pmDate, emDate, Double.parseDouble(uRes.get("price")), uRes.get("id_member"));
			}
			
			user = new User(uRes.get("username"), uRes.get("name"), uRes.get("surname"), uRes.get("address"), uRes.get("fiscal_code"), uRes.get("user_type"), uRes.get("password"),mfee);
			response = new Response(Constants.SUCCESS, user);
			this.loggedUser = user;
			break;
		case Constants.LOGOUT:
			if(this.loggedUser == null)
				response = new Response(Constants.SUCCESS, new EmptyPayload("Logged out!"));
			else
				response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Wrong login!"));
			break;
		case Constants.GET_PAYMENTS:
			ArrayList<Payment> payments = new ArrayList<Payment>();
			for(Map<String, String> m: queryResult){
				LocalDate date = LocalDate.parse(m.get("date"), dateFormatter);
				payments.add(new Payment(Integer.parseInt(m.get("id")), Double.parseDouble(m.get("amount")), m.get("member_id"), m.get("method"), m.get("details"),date, m.get("purpose")));
			}
			response = new Response(Constants.SUCCESS, payments);
			break;
		case Constants.UPDATE_MEMBER:
		case Constants.UPDATE_RACE:
		case Constants.UPDATE_BOAT:
			response = new Response(Constants.SUCCESS, new EmptyPayload("Updated"));
			break;
		}
		if(response == null) throw new SQLToResponseException();
		
		return response;
	}
}

package sailingclub.common;

/**
 * Class with all the constant for the query 
 * @author Andrea Bertogalli e Edoardo Sichelli
 *
 */
public class Constants {
	/**make an insert request*/
	public static final int INSERT = 0; 
	/**make a delete request*/
	public static final int DELETE = 1;
	/**make close connection request*/
	public static final int CLOSE_CONNECTION = 3;
	/**make a login request*/
	public static final int LOGIN = 4;
	/**make boat by id request*/
	public static final int GET_BOAT_BY_ID = 5;
	/**make a pay boat fee request*/
	public static final int PAY_BOAT_STORAGE_FEE = 6;
	/**make a pay member fee request*/
	public static final int PAY_MEMBERSHIP_FEE = 8;
	/**make a get boats request*/
	public static final int GET_BOATS = 9;
	/**make a get races request*/
	public static final int GET_RACES = 10;
	/**make a get races participations request*/
	public static final int GET_RACES_PARTICIPATIONS = 11;
	/**make a logout request*/
	public static final int LOGOUT = 12;
	/**make a get credits card request*/
	public static final int GET_CREDIT_CARDS = 13;
	/**make a get bank accounts request*/
	public static final int GET_BANK_TRANSFERS = 14;
	/**make a get boat participation for a race request*/
	public static final int GET_SUBSCRIPTED_BOAT = 15;
	/**make a get notifications request*/
	public static final int GET_NOTIFICATIONS = 26;
	
	/**make a get members request*/
	public static final int GET_MEMBERS = 16;
	/**make a get member by username request*/
	public static final int GET_MEMBER_BY_USERNAME = 17;
	/**make a get races participations count request*/
	public static final int GET_RACES_SUB = 18;
	/**make a get all boats request*/
	public static final int GET_ALL_BOATS = 21;
	/**make a get all payments request*/
	public static final int GET_PAYMENTS = 22;
	/**make a update request for a race*/
	public static final int UPDATE_RACE = 23;
	/**make a update request for a boat*/
	public static final int UPDATE_BOAT = 24;
	/**make a update request for a member*/
	public static final int UPDATE_MEMBER = 25;
	
	/**request made is succesfull 200 ok*/
	public static final int SUCCESS = 200;
	/**request made is wrong 400 bad_request*/
	public static final int BAD_REQUEST = 400;
	/**request made is succesfull, but server got problems 500 server error*/
	public static final int INTERNAL_SERVER_ERROR = 500;
}

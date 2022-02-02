package sailingclub.common;

public class Constants {
	public static final int INSERT = 0; 
	public static final int DELETE = 1;
	public static final int SELECT = 2;
	public static final int CLOSE_CONNECTION = 3;
	public static final int LOGIN = 4;
	public static final int GET_BOAT_BY_ID = 5;
	public static final int PAY_BOAT_STORAGE_FEE = 6;
	public static final int PAY_MEMBERSHIP_FEE = 8;
	public static final int GET_BOATS = 9;
	public static final int GET_RACES = 10;
	public static final int GET_RACES_PARTICIPATIONS = 11;
	public static final int LOGOUT = 12; 
	public static final int GET_CREDIT_CARDS = 13;
	public static final int GET_BANK_TRANSFERS = 14;
	public static final int GET_SUBSCRIPTED_BOAT = 15;

	// Constants for emp
	public static final int GET_MEMBERS = 16;
	public static final int GET_MEMBER_BY_USERNAME = 17;
	public static final int GET_RACES_PARTICIPATIONS_EMP = 18;
	public static final int GET_BOATS_EMP = 19;
	public static final int GET_SUBSCRIPTED_BOAT_EMP = 20;
	public static final int GET_ALL_BOATS_EMP = 21;
	
	public static final int SUCCESS = 200;
	public static final int BAD_REQUEST = 400;
	public static final int INTERNAL_SERVER_ERROR = 500;
}

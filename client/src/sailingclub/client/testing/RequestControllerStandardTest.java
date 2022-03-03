package sailingclub.client.testing;

import static org.junit.Assert.assertEquals;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sailingclub.client.RequestController;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;

/**
 * this class test the client
 * more specificately tests all the request that the client can make to the server
 */
public class RequestControllerStandardTest {
	private RequestController controller;
	private Socket socket;
	
	
	/**
	 * initializes the tests
	 * @throws Exception
	 */
	@Before
	public void initialize() throws Exception {
		String ip = "localhost";
		int port = 5555;
		socket = new Socket(ip, port);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		controller = new RequestController(out, in);
	}
	
	
	/**
	 * tests the insertion and the deletion
	 */
	@Test
	public void testInsertDelete(){
		Race race = new Race(LocalDate.now(), 55.6, "TestRace");
		Response r = controller.makeRequest(Constants.INSERT, race);
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		r = controller.makeRequest(Constants.DELETE, new Race(Integer.parseInt((String)r.getPayload())));
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
	
	
	/**
	 * tests the login
	 */
	@Test
	public void testLogin() {
		User user = new User("user_for_test", Utils.stringToDigest("x"));
		Response r = controller.makeRequest(Constants.LOGIN, user);
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
		
	
	/**
	 * tests the boat fee payments
	 */
	@Test
	public void testPayBoatStorageFee() {
		Response r = controller.makeRequest(Constants.GET_BOAT_BY_ID, new Boat(1));
		Boat b = (Boat)r.getPayload();
		r = controller.makeRequest(Constants.PAY_BOAT_STORAGE_FEE, b);
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
	
	
	/**
	 * tests the member fee payments
	 */
	@Test
	public void testPayMembershipFee() {
		Response r = controller.makeRequest(Constants.LOGIN, new User("user_for_test", Utils.stringToDigest("x")));
		r = controller.makeRequest(Constants.PAY_MEMBERSHIP_FEE, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
	
	
	/**
	 * tests all the gets requests
	 */
	@Test
	public void testGetRequests() {
		controller.makeRequest(Constants.LOGIN, new User("user_for_test", Utils.stringToDigest("x")));
		
		Response r = controller.makeRequest(Constants.GET_BOATS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_RACES, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_RACES_PARTICIPATIONS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_CREDIT_CARDS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_BANK_TRANSFERS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_SUBSCRIPTED_BOAT, new Race(16));
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_NOTIFICATIONS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_MEMBERS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_MEMBERS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_BOAT_BY_ID, new Boat(1));
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		assertEquals(((Boat)r.getPayload()).getId(), 1);
		
		r = controller.makeRequest(Constants.GET_MEMBER_BY_USERNAME, "user_for_test");
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		assertEquals(((User)r.getPayload()).getUsername(), "user_for_test");
		
		r = controller.makeRequest(Constants.GET_RACES_SUB, "16");
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_ALL_BOATS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_PAYMENTS, new EmptyPayload());
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
	
	
	/**
	 * tests the user request
	 */
	@Test
	public void testUpdateRequests() {
		Response r = controller.makeRequest(Constants.LOGIN, new User("user_for_test", Utils.stringToDigest("x")));
		User u = (User)r.getPayload();
		
		int randomNum = ThreadLocalRandom.current().nextInt(10, 21);
		r = controller.makeRequest(Constants.UPDATE_RACE, new Race(16, LocalDate.now(), randomNum , "TestRace"));
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.GET_RACES, new EmptyPayload());
		@SuppressWarnings("unchecked")
		ArrayList<Race> races = (ArrayList<Race>)r.getPayload();
		System.out.print(races.size());
		for(Race rac: races) {
			if(rac.getId() == 16) {
				assertEquals((int)rac.getPrice(), randomNum);
			}
		}
		
		Boat ob = (Boat)((Response)controller.makeRequest(Constants.GET_BOAT_BY_ID, new Boat(1))).getPayload();
		Boat nb = new Boat(ob.getId(),ob.getName(), randomNum , "", ob.getBoatStorageFee());
		r = controller.makeRequest(Constants.UPDATE_BOAT, nb);
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		Boat vb = (Boat)((Response)controller.makeRequest(Constants.GET_BOAT_BY_ID, new Boat(1))).getPayload();
		assertEquals((int)vb.getLength(), randomNum);
		
		r = controller.makeRequest(Constants.UPDATE_MEMBER, new User(u.getUsername() + "," + u.getUsername(),
					u.getName() + Integer.toString(randomNum), u.getSurname(), u.getAddress(), u.getFiscalCode(), u.getUserType(),
					u.getPassword(), u.getMembershipFee()));
		
		
		r = controller.makeRequest(Constants.LOGIN, new User("user_for_test", Utils.stringToDigest("x")));
		User nu = (User)r.getPayload();
		assertEquals(u.getName() + Integer.toString(randomNum), nu.getName());
	}
	
	
	/**
	 * finalize the tests execution
	 */
	@After
	public void finalize() throws Exception {
		controller.closeConnection();
		this.socket.close();
	}
}

package sailingclub.client.testing;

import static org.junit.Assert.assertEquals;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sailingclub.client.RequestController;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.Race;

public class RequestControllerStandardTest {
	private RequestController controller;
	private Socket socket;
	
	@Before
	public void initialize() throws Exception {
		String ip = "localhost";
		int port = 5555;
		socket = new Socket(ip, port);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		controller = new RequestController(out, in);
	}
	
	@Test
	public void testAddRemoveRace(){
		Race race = new Race(LocalDate.now(), 55.6, "TestRace");
		
		Response r = controller.makeRequest(Constants.INSERT, race);
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
		
		r = controller.makeRequest(Constants.DELETE, new Race(Integer.parseInt((String)r.getPayload())));
		assertEquals(r.getStatusCode(), Constants.SUCCESS);
	}
	
	@After
	public void finalize() throws Exception {
		controller.closeConnection();
		this.socket.close();
	}
}

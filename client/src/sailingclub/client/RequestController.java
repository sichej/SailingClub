package sailingclub.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.EmptyPayload;

public class RequestController {
	private ObjectOutputStream out;
	private ObjectInputStream in;
		
	public RequestController(ObjectOutputStream out, ObjectInputStream in) {
			this.out = out;
			this.in = in;
	}

	public <T> Response makeRequest(int header, T payload) {
		Response r = null;
		try {
			out.writeObject(new Request(header, payload));
	    	r = (Response)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public void closeConnection() {
		try {
			out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

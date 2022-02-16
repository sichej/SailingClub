package sailingclub.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sailingclub.common.Constants;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.common.structures.EmptyPayload;

/**
 * Allows to make {@code Request} to the server and get {@code Response}
 * @see sailingclub.common.Request
 * @see sailingclub.common.Response
 * @see sailingclub.common.Removable
 * @see sailingclub.common.Insertable
 * the {@code Request} structure (Header, [serializable,Insertable, Removeble] Payload)
 */
public class RequestController {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	/**
	 * the class constructor
	 * @param out the output object stream to the server
	 * @param in the input object stream from the server
	 */
	public RequestController(ObjectOutputStream out, ObjectInputStream in) {
			this.out = out;
			this.in = in;
	}

	/**
	 * @param <T> the generic type of the payload
	 * @param header the {@code Request} header
	 * @param payload the {@code Request} payload
	 * @see sailingclub.common.Request
     * @see sailingclub.common.Response
     * @see sailingclub.common.Removable
 	 * @see sailingclub.common.Insertable
	 * @return the the {@code Response}
	 */
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
	
	/**
	 * it closes the connection with the server
	 * using a special request and closing the socket
	 * @see sailingclub.common.Constants#CLOSE_CONNECTION
	 */
	public void closeConnection() {
		try {
			out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

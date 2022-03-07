package sailingclub.common;

import java.io.Serializable;

/**
 * this class rappresent a response from the server
 * received by the client
 */
public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	/**the response status code*/
	private int statusCode;
	/**the response payload type*/
	private Class<?> payloadType;
	/**the response payload*/
	private Object payload;
	
	/**
	 * Response constructor
	 * @param statusCode the status of the response
	 * @param payload the response payload, the data
	 */
	public Response(int statusCode, Object payload) {
		this.statusCode = statusCode;
		this.payloadType = payload.getClass();
		this.payload = payload;
	}

	/**
	 * returns the status code for the response
	 * @return the status code for the response
	 */
	public int getStatusCode() { return this.statusCode; }
	
	/**
	 * returns the payload
	 * @return the payload
	 */
	public Object getPayload() { return this.payload; }
	
	/**
	 * returns the payload type
	 * @return the payload type
	 */
	public Class<?> getPayloadType() { return this.payloadType; }
}

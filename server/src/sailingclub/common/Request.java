package sailingclub.common;

import java.io.Serializable;

/**
 * this class rappresent a request for the server
 * made by the client
 */
public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private int header;
	private Class<?> payloadType;
	private Object payload;
	
	/**
	 * the class constructor
	 * @param header the request header 
	 * @param payload the request payload
	 * @see Constants
	 */
	public Request(int header, Object payload) {
		this.header = header;
		this.payloadType = payload.getClass();
		this.payload = payload;
	}

	/**
	 * it returns the header
	 * @return the header
	 */
	public int getHeader() { return header; }
	
	/**
	 * it resturns the payload
	 * @return the payload
	 */
	public Object getPayload() { return payload; }
	
	/**
	 * returns the payload type
	 * @return the payload type
	 */
	public Class<?> getPayloadType() { return payloadType; }
}

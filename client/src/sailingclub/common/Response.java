package sailingclub.common;

import java.io.Serializable;

/*
 * Responde class
 */
public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	private int stausCode;
	private Class<?> payloadType;
	private Object payload;
	
	/*
	 * Response constructor
	 * @param statusCode  Response code
	 * @param payload  Response payload
	 */
	public Response(int stausCode, Object payload) {
		this.stausCode = stausCode;
		this.payloadType = payload.getClass();
		this.payload = payload;
	}

	public int getStatusCode() { return this.stausCode; }
	public void setStatusCode(int stausCode) { this.stausCode = stausCode; }
	public Object getPayload() { return this.payload; }
	public void setPayload(Object payload) { this.payload = payload; }
	public Class<?> getPayloadType() { return this.payloadType; }
	public void setPayloadType(Class<?> payloadType) { this.payloadType = payloadType; }
}

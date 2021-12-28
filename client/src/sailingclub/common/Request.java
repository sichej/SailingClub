package sailingclub.common;

import java.io.Serializable;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private int header;
	private Class<?> payloadType;
	private Object payload;
	
	public Request(int header, Object payload) {
		this.header = header;
		this.payloadType = payload.getClass();
		this.payload = payload;
	}

	public int getHeader() { return header; }
	public void setHeader(int action) { this.header = action; }
	public Object getPayload() { return payload; }
	public void setPayload(Object payload) { this.payload = payload; }
	public Class<?> getPayloadType() { return payloadType; }
	public void setPayloadType(Class<?> payloadType) { this.payloadType = payloadType; }
}

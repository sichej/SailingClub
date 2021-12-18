package server.api;

public class Request {
	private int action;
	private Class<?> payloadType;
	private Object payload;
	
	public Request(int action, Object payload) {
		this.action = action;
		this.payloadType = payload.getClass();
		this.payload = payload;
	}

	public Request(Request request) {
		this.action = request.getAction();
		this.payload = request.getPayload();
		this.payloadType = request.getPayloadType();
	}

	public int getAction() { return action; }
	public void setAction(int action) { this.action = action; }
	public Object getPayload() { return payload; }
	public void setPayload(Object payload) { this.payload = payload; }
	public Class<?> getPayloadType() { return payloadType; }
	public void setPayloadType(Class<?> payloadType) { this.payloadType = payloadType; }
}

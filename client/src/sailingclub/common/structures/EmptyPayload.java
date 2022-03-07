package sailingclub.common.structures;

import java.io.Serializable;

/**
 * This class is useful for requests and responses that don't require
 * a specific data payload
 */
public class EmptyPayload implements Serializable{
	private static final long serialVersionUID = 1L;
	/**a message delivered as payload (optional)*/
	private String message;
	
	/**
	 * EmptyPayload constructor
	 */
	public EmptyPayload() {
		this.message = "";
	}
	
	/**
	 * EmptyPayload constructor
	 * @param message  message to send
	 */
	public EmptyPayload(String message) {
		this.message = message;
	}
	
	/**
	 * @return String  the message 
	 */
	@Override
	public String toString() {
		return "SRV_MESSAGE: " + message;
	}
}

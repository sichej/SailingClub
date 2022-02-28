package sailingclub.common.structures;

import java.io.Serializable;

/**
 * EmptyPayload class
 */
public class EmptyPayload implements Serializable{
	private static final long serialVersionUID = 1L;
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

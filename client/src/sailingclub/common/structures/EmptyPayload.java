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
	
	public EmptyPayload(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "SRV_MESSAGE: " + message;
	}
}

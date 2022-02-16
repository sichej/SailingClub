package sailingclub.server.sqlmanagment;

/**
 * implements a {@code Response} translation exception
 */
public class SQLToResponseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor from superclass
	 */
	public SQLToResponseException() { super(); }
	
	/**
	 * constructor with custom message
	 * @param message the message to display
	 */
	public SQLToResponseException(String message) { super(message); }	
}

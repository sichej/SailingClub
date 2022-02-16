package sailingclub.server.sqlmanagment;

/**
 * implements a {@code Request} translation exception
 */
public class RequestToSQLException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * constructor from superclass
	 */
	public RequestToSQLException() { super(); }
	
	/**
	 * constructor with custom message
	 * @param message the message to display
	 */
	public RequestToSQLException(String message) { super(message); }
}

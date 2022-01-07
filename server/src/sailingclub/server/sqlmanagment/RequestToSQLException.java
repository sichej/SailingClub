package sailingclub.server.sqlmanagment;

public class RequestToSQLException extends Exception {
	private static final long serialVersionUID = 1L;
	public RequestToSQLException() { super(); }
	public RequestToSQLException(String message) { super(message); }
}

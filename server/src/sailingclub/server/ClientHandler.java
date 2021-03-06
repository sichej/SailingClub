package sailingclub.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import sailingclub.common.Constants;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.server.sqlmanagment.SQLTranslator;
import sailingclub.server.sqlmanagment.DatabaseManager;
import sailingclub.server.sqlmanagment.RequestToSQLException;
import sailingclub.server.sqlmanagment.SQLToResponseException;
import sailingclub.common.Request;
import sailingclub.common.Response;

/**
 * is the handler for each client
 */
public class ClientHandler implements Runnable {
	private Socket socket;
	private SQLTranslator translator;
	private DatabaseManager dbManager;

	/**
	 * the contructor
	 * @param socket the socket on which a client is connected
	 * @param conf the server configuration
	 */
	public ClientHandler(Socket socket, ServerConfiguration conf) {
		this.socket = socket;
		this.translator = new SQLTranslator();
		this.dbManager = new DatabaseManager(conf.getDbUser(), conf.getDbPassword());
	}

	/**
	 * the run method, it's executed on handling
	 */
	public void run() {
		ObjectInputStream in;
		ObjectOutputStream out;

		try {
			in = new ObjectInputStream(this.socket.getInputStream());
			out = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("An error occurred while opening the strem!");
			try { this.socket.close(); } catch (IOException ie) {}
			return;
		}

		System.out.println("Client " + this.socket.getInetAddress().getHostAddress() + " " + this.socket.getPort() + " connected!");

		while(true){
			try {
				Response response = null;
				try {
					Request request = (Request)in.readObject();  //read the incoming request
					if(request.getHeader() == Constants.CLOSE_CONNECTION) break;  //if it's a close connection request
					
					String query  = this.translator.requestToSql(request); //convert the request into a db request
					
					System.out.println("TRYING TO EXECUTE:\n" + query);
					
					List<Map<String, String>> queryResult = this.dbManager.executeSQLStatement(query);  //execute the query
					response = this.translator.sqlToResponse(queryResult); //translate the query result into a java response object
				}catch(RequestToSQLException rtse) {  //in case of bad request
					rtse.printStackTrace();
					response = new Response(Constants.BAD_REQUEST, new EmptyPayload("Bad request"));
				}catch(SQLToResponseException stre) {  //in case of server error
					stre.printStackTrace();
					response = new Response(Constants.INTERNAL_SERVER_ERROR, new EmptyPayload("Internal server error"));
				}
				out.writeObject(response);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("An error occurred in the socket stream!");
				break;
			}
		}

		try { socket.close();} catch (IOException ie) {}
		System.out.println("Client " + socket.getInetAddress().getHostAddress() + " " + socket.getPort() + " disconnected!");
	}
}
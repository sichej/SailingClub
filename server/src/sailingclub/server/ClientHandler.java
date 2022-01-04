package sailingclub.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import sailingclub.common.Constants;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.server.sqlmanagment.SQLTranslator;
import sailingclub.server.sqlmanagment.DatabaseManager;
import sailingclub.common.Request;
import sailingclub.common.Response;

public class ClientHandler implements Runnable {
	private Socket socket;
	private SQLTranslator translator;
	private DatabaseManager dbManager;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		this.translator = new SQLTranslator();
		this.dbManager = new DatabaseManager();
	}

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
					
					String query  = this.translator.RequestToSQL(request); //convert the request into a db request
					
					System.out.println("TRYING TO EXECUTE:\n" + query);
					
					List<Map<String, String>> queryResult = this.dbManager.executeSQLStatement(query);  //execute the query
					response = this.translator.SQLToResponse(queryResult); //translate the query result into a java response object
				}catch(ClassNotFoundException cnfe) {  //in case of bad request
					response = new Response(Constants.BAD_REQUEST, new EmptyPayload());
				}catch(Exception sqle) {
					sqle.printStackTrace();
					response = new Response(Constants.INTERNAL_SERVER_ERROR, new EmptyPayload());
				}
			
				out.writeObject(response);
				
			} catch (IOException e) {
				System.out.println("An erro occurred in the socket stream!");
				break;
			}
		}

		try { socket.close();} catch (IOException ie) {}
		System.out.println("Client " + socket.getInetAddress().getHostAddress() + " " + socket.getPort() + " disconnected!");
	}
}
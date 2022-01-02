package sailingclub.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
				Request rq = (Request)in.readObject();
				if(rq.getHeader() == Constants.CLOSE_CONNECTION) break;
				
				String query  = translator.RequestToSQL(rq);
				System.out.println("TRYING TO EXECUTE:\n" + query);
				this.dbManager.executeSQLStatement(query);
				
				out.writeObject(new Response(Constants.SUCCESS, new EmptyPayload()));
			} catch (IOException e) {
				System.out.println("An erro occurred in the socket stream!");
				break;
			}catch(ClassNotFoundException ce) {
				try {
					out.writeObject(new Response(Constants.BAD_REQUEST, new EmptyPayload()));
					break;
				}catch(IOException ie) {}
			}catch(Exception ex) {
				try {
					out.writeObject(new Response(Constants.INTERNAL_SERVER_ERROR, new EmptyPayload()));
					break;
				}catch(IOException ie) {}
			}
		}

		try { socket.close();} catch (IOException ie) {}
		System.out.println("Client " + socket.getInetAddress().getHostAddress() + " " + socket.getPort() + " disconnected!");
	}
}
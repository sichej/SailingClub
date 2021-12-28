package sailingclub.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sailingclub.common.Actions;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.Request;
import sailingclub.common.Response;
import sailingclub.server.utils.ClientRequestTranslator;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ClientRequestTranslator translator;

    public ClientHandler(Socket socket){
        this.socket = socket;
        this.translator = new ClientRequestTranslator();
    }

    public void run() {
    	System.out.println("Client " + this.socket.getInetAddress().getHostAddress() + " " + this.socket.getPort() + " connected!");
    	
    	try {
			ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
			Request rq;
			
			do {
				rq = (Request)in.readObject();
				System.out.println(translator.translate(rq)); //TODO ELEAB QUERY
				out.writeObject(new Response(200, new EmptyPayload()));
			}while(rq.getHeader() != Actions.CLOSE_CONNECTION);
			
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
        System.out.println("Client " + socket.getInetAddress().getHostAddress() + " " + socket.getPort() + " disconnected!");
    }
}

/*ClientRequestTranslator translator = new ClientRequestTranslator();
Request req = new Request(Actions.INSERT, new BoatSQLModel("nubarca",11.11,"Cody"));
String query = translator.translate(req);

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = null;
	conn = DriverManager.getConnection("jdbc:mysql://localhost/sailing_club", "root", "");
	System.out.println("Database is connected !\n");

	conn.createStatement().execute(query);
	Statement selectStmt = conn.createStatement();
	ResultSet rs = selectStmt.executeQuery(query);
	List<Map<String, String>> queryResult = wrapQueryResult(rs);
	printTable(queryResult);

	conn.close();
} catch (Exception e) {
	System.out.print("Do not connect to DB - Error:" + e);
}*/

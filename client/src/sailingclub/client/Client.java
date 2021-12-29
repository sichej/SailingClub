package sailingclub.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sailingclub.common.Actions;
import sailingclub.common.Response;
import sailingclub.common.Request;
import sailingclub.common.structures.*;
import sailingclub.common.structures.EmptyPayload;

public class Client {
	public static void main(String[] args) {
		System.out.println("CLIENT");
        try {
        	Socket socket = new Socket("localhost", 12345);
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	
        	/*for(int i = 0; i < 3; i++) {
				out.writeObject(new Request(Actions.INSERT, new Boat("pippo",2.2,"Alice")));
				Response rs = (Response)in.readObject();
				System.out.println("SRV> " + rs.getStatusCode());
        	}*/
			out.writeObject(new Request(Actions.LOGIN, new User("Alicia", "9dd4e461268c8034f5c8564e155c67a6")));
			Response rs = (Response)in.readObject();
			System.out.println("SRV> " + rs.getStatusCode());
        	
        	out.writeObject(new Request(Actions.CLOSE_CONNECTION, new EmptyPayload()));
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

package sailingclub.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Request;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.User;

public class Client {
	public static void main(String[] args) {
		System.out.println("CLIENT");
        try {
        	Socket socket = new Socket("localhost", 12345);
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	
			out.writeObject(new Request(Constants.LOGIN, new User("Alicia", "9dd4e461268c8034f5c8564e155c67a6")));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV> " + rs.getStatusCode());
        	
        	out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

package sailingclub.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sailingclub.common.*;
import sailingclub.common.structures.Boat;

public class Client {
	public static void main(String[] args) {
		System.out.println("CLIENT");
        try {
        	Socket socket = new Socket("localhost", 1234);
        	
        	ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            //ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            
            out.writeObject(new Request(1,new Boat(105)));
            out.flush();
            //System.out.print(in.readAllBytes());
            out.writeObject(new Request(3,new Boat(105)));
            out.flush();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

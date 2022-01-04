package sailingclub.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Request;
import sailingclub.common.structures.BankTransfer;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.User;

public class Client {
	public static void main(String[] args) {
		System.out.println("CLIENT");
        try {
        	Socket socket = new Socket("localhost", 12345);
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	
        	out.writeObject(new Request(Constants.GET_BOAT_BY_ID, new Boat(101)));
        	Response rs = (Response)in.readObject();
        	out.writeObject(new Request(Constants.PAY_BOAT_STORAGE_FEE, (Boat)(rs.getPayload())));
        	rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());
        	
			/*out.writeObject(new Request(Constants.INSERT, new BankTransfer("IT91C1234567890123456789012", "Credit Agricole", "Alicia")));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/
        	
        	out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

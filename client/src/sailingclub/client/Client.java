package sailingclub.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;

import sailingclub.common.Constants;
import sailingclub.common.Response;
import sailingclub.common.Utils;
import sailingclub.common.Request;
import sailingclub.common.structures.BankTransfer;
import sailingclub.common.structures.Boat;
import sailingclub.common.structures.BoatStorageFee;
import sailingclub.common.structures.EmptyPayload;
import sailingclub.common.structures.Race;
import sailingclub.common.structures.User;

public class Client {

	public static void main(String[] args) {
		System.out.println("CLIENT");
        try {
        	Socket socket = new Socket("localhost", 12345);
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	
        	/*out.writeObject(new Request(Constants.GET_BOAT_BY_ID, new Boat(105)));
        	Response rs = (Response)in.readObject();
        	System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/
        	
        	/*out.writeObject(new Request(Constants.PAY_BOAT_STORAGE_FEE, (Boat)(rs.getPayload())));
        	rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + ((Boat)rs.getPayload()).getBoatStorageFee().getExpirationDate()
					+ ((Boat)rs.getPayload()).getBoatStorageFee().getPaymentDate());*/
        	
			/*out.writeObject(new Request(Constants.INSERT, new BankTransfer("IT88C1234567890123456789012", "Credit Agricole", "Alicia")));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/
        	
        	/*Boat bt = new Boat("Titanic", 166.78, "Berto");
        	out.writeObject(new Request(Constants.INSERT, bt));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());
			
			int newid = Integer.parseInt((String)rs.getPayload());
			
			BoatStorageFee bs = new BoatStorageFee(LocalDate.now(), LocalDate.now().plusYears(1), 100, newid);
        	out.writeObject(new Request(Constants.INSERT, bs));
        	Response rs1 = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs1.getPayload());*/
        	
        	
        	/*out.writeObject(new Request(Constants.LOGIN, new User("Alexander", Utils.stringToDigest("x"))));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/
        	
        	/*out.writeObject(new Request(Constants.REMOVE_BOAT, new Boat(103)));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/

			/*out.writeObject(new Request(Constants.CREATE_RACE, new Race(0, LocalDate.parse("2024-10-24"), 200)));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());*/

			out.writeObject(new Request(Constants.ADD_MEMBER, new User("edo", "edoardo", "sichelli", "via gentileschi", "QWERTYUIOPLKJHGF", "member", "9dd4e461268c8034f5c8564e155c67a6")));
        	Response rs = (Response)in.readObject();
			System.out.println("SRV SAYS: \nSTATUS:  " + rs.getStatusCode() + "\nPAYLOAD:  " + rs.getPayload());
        	
        	out.writeObject(new Request(Constants.CLOSE_CONNECTION, new EmptyPayload()));
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}

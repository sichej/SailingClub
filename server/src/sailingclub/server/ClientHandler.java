package sailingclub.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sailingclub.common.Actions;
import sailingclub.common.Request;
import sailingclub.server.utils.ClientRequestTranslator;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }

    public void run(){
    	System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " " + clientSocket.getPort() + " connected!");
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            
            ClientRequestTranslator t = new ClientRequestTranslator();
            Request rq = null;
            
            do {
            	Object msg = in.readObject();
            	if(msg instanceof Request) {
            		rq = (Request)msg;
            		System.out.print(t.translate(rq));
            		out.writeBytes("SRV: REQUEST RECEIVED!\n");
            		out.flush();
                }
            }while(rq.getAction() != Actions.CLOSE_CONNECTION);
            out.close();
            in.close();
            clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " " + clientSocket.getPort() + " disconnected!");
    }
}
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import server.utils.Actions;
import server.utils.ClientRequestTranslator;
import server.utils.Request;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }

    public void run(){
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            ClientRequestTranslator t = new ClientRequestTranslator();
            Request rq = null;
            do {
            	Object msg = in.readObject();
            	System.out.print("RECEIVED SMTH:\n");
            	if(msg instanceof Request) {
            		rq = (Request)msg;
            		System.out.print(t.translate(rq));
                }
            }while(rq.getAction() != Actions.CLOSE_CONNECTION);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
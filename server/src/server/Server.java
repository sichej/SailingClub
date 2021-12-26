package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public void run(){
		ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
   		 	System.out.println("SERVER LISTENING ON PORT: 1234\n");
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress() + " " + client.getPort());
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

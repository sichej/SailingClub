package sailingclub.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The main class allows to launch  the servers
 * the server adopt a concurrent solution, 1 thread per client
 * and implements a transaltor between client requests
 * and sql query
 * @author Andrea Bertogalli and Edoardo Sichelli
 */
public class Server {
	/**
	 * the main method
	 * @param args command lines arguments
	 */
	public static void main(String[] args){
		ServerConfiguration conf = new ServerConfiguration("config/srv_conf.json");
		ServerSocket server = null;
		System.out.println(conf.toString() + "\n");
		
        try {
            server = new ServerSocket(conf.getServerPort());
            server.setReuseAddress(true);
            System.out.println("SERVER LISTENING ON PORT: " + conf.getServerPort());
            while (true) {
                Socket client = server.accept();
                new Thread(new ClientHandler(client,conf)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

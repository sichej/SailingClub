package sailingclub.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sailingclub.server.utils.Functionalities;
import sailingclub.server.utils.ServerConfiguration;

public class Server {
	public static void main(String[] args){
		ServerConfiguration conf = Functionalities.loadServerConfigurations();
		ServerSocket server = null;
		
		System.out.println(conf.toString() + "\n");
		
        try {
            server = new ServerSocket(conf.getServerPort());
            server.setReuseAddress(true);
            System.out.println("SERVER LISTENING ON PORT: " + conf.getServerPort());
            while (true) {
                Socket client = server.accept();
                new Thread(new ClientHandler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

package sailingclub.server;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class ServerConfiguration {
	private int serverPort;
	private String dbUser;
	private String dbPassword;
	
	public ServerConfiguration(String path) {
		Gson gson = new Gson();
		ServerConfiguration conf = null;
		try {
			conf = gson.fromJson(new FileReader(path), ServerConfiguration.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.serverPort = conf.getServerPort();
		this.dbUser = conf.getDbUser();
		this.dbPassword = conf.getDbPassword();
	}
	
	public int getServerPort() { return serverPort; }
	public void setServerPort(int serverPort) { this.serverPort = serverPort; }
	public String getDbUser() { return dbUser; }
	public void setDbUser(String dbUser) { this.dbUser = dbUser; }
	public String getDbPassword() { return dbPassword; }
	public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

	@Override
	public String toString() {
		return "ServerConfiguration [serverPort=" + serverPort + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword
				+ "]";
	}
}

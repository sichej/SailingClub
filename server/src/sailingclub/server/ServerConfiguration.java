package sailingclub.server;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * it's a wrapper for the server configuration
 * the configurations are in srv_conf.json
 */
public class ServerConfiguration {
	private int serverPort;
	private String dbUser;
	private String dbPassword;
	
	/**
	 * the constructor
	 * @param path the path to the json config file
	 */
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
	
	/**
	 * return the server port
	 * @return the server port
	 */
	public int getServerPort() { return serverPort; }
	
	/**
	 * sets the server port
	 * @param serverPort the server port
	 */
	public void setServerPort(int serverPort) { this.serverPort = serverPort; }
	
	/**
	 * returns the username for the db
	 * @return the db user
	 */
	public String getDbUser() { return dbUser; }
	
	
	/**
	 * sets the user for the db
	 * @param dbUser the user of the db
	 */
	public void setDbUser(String dbUser) { this.dbUser = dbUser; }
	
	/**
	 * returns the db password
	 * @return the passowrd of the db
	 */
	public String getDbPassword() { return dbPassword; }
	
	/**
	 * sets the db passsowrd
	 * @param dbPassword the password of the db
	 */
	public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

	@Override
	public String toString() {
		return "ServerConfiguration [serverPort=" + serverPort + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword
				+ "]";
	}
}

package sailingclub.server.utils;

public class ServerConfiguration {
	private int serverPort;
	private String dbUser;
	private String dbPassword;
	
	public ServerConfiguration(int serverPort, String dbUser, String dbPassword) {
		this.serverPort = serverPort;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
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

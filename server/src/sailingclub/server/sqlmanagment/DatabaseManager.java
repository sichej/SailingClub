package sailingclub.server.sqlmanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * allows to make queries to the db
 * @see SQLTranslator
 */
public class DatabaseManager {
	private String user;
	private String psw;
	
	/**
	 * The constructor
	 * @param user the user of the database
	 * @param psw the password for the database
	 */
	public DatabaseManager(String user, String psw) {
		this.user = user;
		this.psw = psw;
	}
	
	private List<Map<String, String>> wrapQueryResult(ResultSet rs) throws SQLException {
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		ResultSetMetaData meta = rs.getMetaData();
		while (rs.next()) {
			Map<String,String> map = new HashMap<String,String>();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String key = meta.getColumnName(i);
				String value = rs.getString(key);
				map.put(key, value);
			}
			table.add(map);
		}
		return table;
	}
	
	/**
	 * allows to execute an sql statement and return the the result wrapped in a list of maps
	 * @param query the sql statement to execute
	 * @return a list of maps that represent the result table
	 * @throws ClassNotFoundException occur if there are an unparasable object
	 * @throws SQLException custom excepion for no request handling
	 */
	public List<Map<String, String>> executeSQLStatement(String query) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/sailing_club?allowMultiQueries=true",this.user,this.psw );
		System.out.println("Database is connected !\n");
		Statement stmt = conn.createStatement();
		boolean isResultSet = stmt.execute(query);
		boolean hasToBeWrapped = true;
		
		if(!isResultSet) 
			hasToBeWrapped = stmt.getMoreResults();  //se ha più risultati prende il secondo
		
		ResultSet rs = stmt.getResultSet();
		
		List<Map<String, String>> wr = null;
		if(hasToBeWrapped) {
			wr = wrapQueryResult(rs);
			rs.close();
		}
		
		conn.close();
		
		return wr;
	}
}

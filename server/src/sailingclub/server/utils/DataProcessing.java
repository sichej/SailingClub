package sailingclub.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sailingclub.common.structures.Boat;

public class DataProcessing {
	private static void printTable(List<Map<String, String>> table) {
		table.get(0).forEach((key, value) -> System.out.print(key + "\t"));
		System.out.println("\n");
		for(Map<String, String> row: table) {
			row.forEach((key, value) -> System.out.print(value + "\t"));
			System.out.println();
		} 
	}
	
	public static List<Map<String, String>> wrapQueryResult(ResultSet rs) throws SQLException {
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
}


/*ClientRequestTranslator translator = new ClientRequestTranslator();
		Request req = new Request(Actions.INSERT, new BoatSQLModel("nubarca",11.11,"Cody"));
		String query = translator.translate(req);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sailing_club", "root", "");
			System.out.println("Database is connected !\n");

			conn.createStatement().execute(query);
			Statement selectStmt = conn.createStatement();
			ResultSet rs = selectStmt.executeQuery(query);
			List<Map<String, String>> queryResult = wrapQueryResult(rs);
			printTable(queryResult);

			conn.close();
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}*/

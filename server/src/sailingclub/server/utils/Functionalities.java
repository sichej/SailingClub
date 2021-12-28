package sailingclub.server.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

public class Functionalities {
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
	
	public static ServerConfiguration loadServerConfigurations() {
		Gson gson = new Gson();
		ServerConfiguration conf = null;
		try {
			conf = gson.fromJson(new FileReader("config/srv_conf.json"), ServerConfiguration.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return conf;
	}
}
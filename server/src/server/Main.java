package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) throws SQLException {
	
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	       	 	Connection conn = null;
	            conn = DriverManager.getConnection("jdbc:mysql://localhost/sailig_club","root", "");
	            System.out.print("Database is connected !");
	            
		   		Statement selectStmt = conn.createStatement();
		   		String query = "SELECT * FROM user;";
				ResultSet rs = selectStmt.executeQuery(query);
				
				while (rs.next()) {
				System.out.print(rs.getString(1));
				}
			 
			 /*
			 ArrayList allRows = new ArrayList();
			 int numberColumns = 7;
			 System.out.print(rs.getString(0));*/
				conn.close();
	        }
	        catch(Exception e) {
	            System.out.print("Do not connect to DB - Error:"+e);
	        }
		 

	}
}

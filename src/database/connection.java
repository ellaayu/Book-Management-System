package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class connection {
	public static Connection con = null;
	public static PreparedStatement stmt;
	public static ResultSet rs = null;
	public static void conndb() throws Exception{
	 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dblibrary?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	
			if(!con.isClosed()) {
			//	System.out.println("Successfully connect DB... ");
			}
		}catch(Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}

	}
	
}

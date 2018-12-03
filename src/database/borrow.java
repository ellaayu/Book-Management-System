package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class borrow {
	public static  Connection con = null;
	public static PreparedStatement stmt;
	public static ResultSet rs = null;
	public static void select() throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
			//개인 컴퓨터의 phpmyadmin 주소
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dblibrary?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	
			if(!con.isClosed()) {
				//System.out.println("Successfully connect DB... ");
			}
		}catch(Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}
		
		book_sys.SeeBook();

	}
	public static void borrowbook() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Input ID of user: ");
		String id_user = scan.next();
		System.out.println(id_user);

		String sql="select name_user from user where id_card = '"+id_user+"'";

		 stmt = con.prepareStatement(sql);
		 rs = stmt.executeQuery(sql);
		 
		 String name = rs.getString("name_user");
		 System.out.format("%s", name);
	}
}


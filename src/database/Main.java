package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {

		connection.conndb();
		//borrow.borrowbook();
		try{
			System.out.println("	=================================");
			System.out.println("	Welcome in Book Management System");
			System.out.println("	=================================");
			for(int i = 0; i<100; i++) {

			book_sys.select();
			}
 
		    } catch (SQLException se){
		        System.out.println(se.getMessage());
		    } finally {
		       // con.close();
		    }
	}
	
	
}


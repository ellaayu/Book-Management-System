package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class borrow {
	public static Connection con = null;
	public static PreparedStatement stmt;
	public static ResultSet rs = null;
	public static PreparedStatement stmtt;
	public static ResultSet rss = null;

	public static void iya() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			// 개인 컴퓨터의 phpmyadmin 주소
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dblibrary?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

			if (!con.isClosed()) {
				// System.out.println("Successfully connect DB... ");
			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}

		borrowbook();
		SeeBorrow();
	}

	public static void borrowbook() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.print("Input ID of user: ");
		String id_user = scan.next();
		System.out.print("Input ID of book: ");
		String id_book = scan.next();
		System.out.print("Input ID of borrow (YYYY/MM/DD): ");
		String borrow_b = scan.next();
		System.out.print("Input ID of return (YYYY/MM/DD): ");
		String return_b = scan.next();
		System.out.println("");
		System.out.println("\n============== Borrowed Book List ================\n");
		
		String sql="select * from user where id_card like '%"+id_user+"%'";
//		String sql = "select u.id_card, u.name_user, u.status_user, b.name_book "
//				+ "from book b "
//				+ "left join user u on u.id_card=b.id_card "
//				+ "where u.id_card like '%" + id_user + "%' || b.id_book like '%" + id_book + "%'";
		stmt = con.prepareStatement(sql);
	
		// execute the query, and get a java resultset
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			
			String user_id = rs.getString("id_card");
			String user_name = rs.getString("name_user");
			String user_writer = rs.getString("status_user");

			// print the results
			//System.out.format("%s. %s\n     Status    : %s\n \n", user_id, user_name, user_writer);
			String sql_in="insert into borrow(id_borrow, user_name,book_name, date_out,	date_back) values(?, ?, ?, ?, ?)";
			 stmt = con.prepareStatement(sql_in);
			    stmt.setString(1, user_id);
			    stmt.setString(2, user_name);
			    stmt.setString(3, "");
			    stmt.setString(4, borrow_b);
			    stmt.setString(5, return_b);

			    stmt.executeUpdate();
		}
		String sql_b="select name_book from book where id_book like '%"+id_book+"%'";
		stmtt = con.prepareStatement(sql_b);
		rss = stmt.executeQuery(sql_b);
		while (rss.next()) {
			String namebook = rss.getString("name_book");
			//System.out.println(namebook);
			
			// print the results
			String sql_n="update borrow set book_name = (?) where date_out = (?)";
			 stmtt = con.prepareStatement(sql_n);
			    stmtt.setString(1, namebook);
			    stmtt.setString(2, borrow_b);

			    stmtt.executeUpdate();
		}	
		
	}
	public static void SeeBorrow() throws Exception {

		String query = "SELECT * FROM borrow";

		// create the java statement
		stmt = con.prepareStatement(query);

		// execute the query, and get a java resultset
		rs = stmt.executeQuery(query);

		// iterate through the java resultset
		while (rs.next()) {
			String book_Name = rs.getString("user_name");
			String book_writer = rs.getString("book_name");
			String book_year = rs.getString("date_out");
			String book_publisher_book = rs.getString("date_back");

			// print the results

			System.out.format("- Name           : %s\n   Book           : %s\n   Date of loan   : %s\n     Date of Return : %s\n",
					book_Name,book_writer, book_year, book_publisher_book);
		}
	}
}

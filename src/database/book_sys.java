package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class book_sys {
//database를 연결한다
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

// SeeBook() 클래스를 부른다
		//SeeBook();
		Choosing();
		
			
	}
	public static void Choosing() throws Exception{
	//책의 메뉴 선택

			System.out.println("_____________________________________________");
			Scanner scan = new Scanner(System.in);
			System.out.println("|| 1. List of Book                         ||\n|| "
					+ "2. Insert new Book                      ||\n|| "
					+ "3. Update Book Information              ||\n|| "
					+ "4. Delete Book                          ||\n|| "
					+ "5. Search Book                          ||\n|| "
					+ "6. Exit                                 ||\n");
			System.out.print("Select your option :  ");
			int select_num = scan.nextInt();
			
			if(select_num==1) {
				System.out.println("\n============== List of Book ================\n");
				SeeBook();
			}
			else if(select_num == 2) {
	//"1" 선택하면 insertBook() 클래스을 부른다
				System.out.println("\n============== Insert Book ================\n");
				insertBook();
				

			}else if(select_num == 3 ) {
				System.out.println("\n============== Update Book ================\n");
				Update();
				

			}else if(select_num == 4 ) {
				System.out.println("\n============== Delete Book ================\n");
				Delete();
				

			}else if(select_num == 5 ){
				System.out.println("\n============== Search Book ================\n");
				Search();
				

			}else {
				System.exit(0);
				System.out.println("~~ Bye Bye ~~");
			}
	}
	
	public static void insertBook() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Input ID of Book: ");
		String id_book = scan.next();
		
		System.out.print("Input Name of Book: ");
		//String[] name_book = scan.next().split(" ");

		String name_book = scan.next();
		String[] numbersArray = name_book.split(" "); // splitting string by spaces
		System.out.println(numbersArray.toString());
		// output ["1","23","456"]
//		for(int i = 0; i < name_book.length; i++){
//            System.out.println("===="+name_book[i]);
//        }
//        String nb = Arrays.toString(name_book);        
//		scan.next();
		//System.out.println(nb);
		
		
		System.out.print("Input Writer of Book: ");
		String[] writer_book = scan.nextLine().split(" ");
		
        for(int i = 0; i < writer_book.length; i++){
          //  System.out.println(writer_book[i]);
        }
        String wb = Arrays.toString(writer_book);
        scan.next();
        
		System.out.print("Input Year of Book: ");
		String year_book = scan.next();
		
		System.out.print("Input Publisher of Book: ");
		String[] publisher_book = scan.nextLine().split(" ");
        for(int i = 0; i < publisher_book.length; i++){
           // System.out.println(publisher_book[i]);
        }
        String pb = Arrays.toString(publisher_book);
        scan.next();
		
		String sql="insert into book(id_book, name_book,writer_book,year_book, publisher_book) values(?, ?, ?, ?, ?)";
		 stmt = con.prepareStatement(sql);
		    stmt.setString(1, id_book);
		    stmt.setString(2, name_book);
		    stmt.setString(3, wb);
		    stmt.setString(4, year_book);
		    stmt.setString(5, pb);

		    stmt.executeUpdate();

	}
	
	public static void SeeBook() throws Exception{

		 String query = "SELECT * FROM book";

	      // create the java statement
	       stmt = con.prepareStatement(query);
	      
	      // execute the query, and get a java resultset
	       rs = stmt.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String id = rs.getString("id_book");
	        String book_Name = rs.getString("name_book");
	        String book_writer = rs.getString("writer_book");
	        String book_year = rs.getString("year_book");
	        String book_publisher_book = rs.getString("publisher_book");

	        
	        // print the results
	        System.out.format("%s. %s\n     Writer    : %s\n     Year      : %s\n     Publisher : %s\n", id,book_Name, book_writer,book_year,book_publisher_book);
	      }
	}
	
	public static void Delete() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Input ID of book : ");
		String id_book = scan.next();

		System.out.print("Are you sure that you want to delete this ? (y/n)");
		String y_or_n = scan.next();
		System.out.println(y_or_n);
		if(y_or_n.equals("y")) {
			String sql="delete from book where id_book = (?)";
			 stmt = con.prepareStatement(sql);
			 stmt.setString(1, id_book);
			 
			 stmt.executeUpdate();
			 System.out.println("Book already deleted!!");
		}else {
			Choosing();
		}
		
		
	}
	public static void Search() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Search by :\n 1. ID \n 2. Name \n Select your option : ");
		int choose = scan.nextInt();
		
		if(choose==1) {
			System.out.print("Insert ID : ");
			int update_id = scan.nextInt();
			
			String sql="select * from book where id_book like '%"+update_id+"%'";
			stmt = con.prepareStatement(sql);
		      // execute the query, and get a java resultset
		    rs = stmt.executeQuery(sql);
		    while (rs.next())
		      {
		        String id = rs.getString("id_book");
		        String book_Name = rs.getString("name_book");
		        String book_writer = rs.getString("writer_book");
		        String book_year = rs.getString("year_book");
		        String book_publisher_book = rs.getString("publisher_book");

		        
		        // print the results
		        System.out.format("%s. %s\n     Writer    : %s\n     Year      : %s\n     Publisher : %s\n", id,book_Name, book_writer,book_year,book_publisher_book);
		      }
		}else if(choose==2) {
			System.out.print("Insert Name : ");
			String update_name = scan.next();
			
			String sql="select * from book where name_book like '%"+update_name+"%'";
			stmt = con.prepareStatement(sql);
		      // execute the query, and get a java resultset
		    rs = stmt.executeQuery(sql);
		    while (rs.next())
		      {
		        String id = rs.getString("id_book");
		        String book_Name = rs.getString("name_book");
		        String book_writer = rs.getString("writer_book");
		        String book_year = rs.getString("year_book");
		        String book_publisher_book = rs.getString("publisher_book");

		        
		        // print the results
		        System.out.format("%s. %s\n     Writer    : %s\n     Year      : %s\n     Publisher : %s\n", id,book_Name, book_writer,book_year,book_publisher_book);
		      }
		}
	}
	
	public static void Update() throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.print("Choose book's ID : ");
		int up_id = scan.nextInt();
		
		String sql="select * from book where id_book like '%"+up_id+"%'";
		stmt = con.prepareStatement(sql);

	      // execute the query, and get a java resultset
	    rs = stmt.executeQuery(sql);
	    while (rs.next())
	      {
	        String id = rs.getString("id_book");
	        String book_Name = rs.getString("name_book");
	        String book_writer = rs.getString("writer_book");
	        String book_year = rs.getString("year_book");
	        String book_publisher_book = rs.getString("publisher_book");

	        
	        // print the results
	        System.out.format("%s. %s\n     Writer    : %s\n     Year      : %s\n     Publisher : %s\n", id,book_Name, book_writer,book_year,book_publisher_book);
	      
	      }
	    System.out.println("Update book's \n 1. Name \n 2. Writer \n 3. Year \n 4. Publisher \n 5. Date of Entry \n 6. Home");
	    System.out.print("Choose the number : ");
	    int choose_2 = scan.nextInt();
		if(choose_2==1) {
			System.out.print("Update Name : ");
			String update_name = scan.next();
			
			String query="update book set name_book = (?) where id_book = (?)";
			 stmt = con.prepareStatement(query);
			 stmt.setString(1, update_name);
			 stmt.setInt(2, up_id);
			 stmt.executeUpdate();
			 System.out.println("Book's name already update!!");
		}else if (choose_2==2) {
			System.out.print("Update Writer name : ");
			String update_writer = scan.next();
			
			String query="update book set writer_book = (?) where id_book = (?)";
			 stmt = con.prepareStatement(query);
			 stmt.setString(1, update_writer);
			 stmt.setInt(2, up_id);
			 stmt.executeUpdate();
			 System.out.println("Book's writer name already update!!");
		}else if (choose_2==3) {
			System.out.println("Update Year of book : ");
			int update_year = scan.nextInt();
			
			String query="update book set year_book = (?) where id_book = (?)";
			 stmt = con.prepareStatement(query);
			 stmt.setInt(1, update_year);
			 stmt.setInt(2, up_id);
			 stmt.executeUpdate();
			 System.out.println("Book's year already update!!");
		}else if (choose_2==4) {
			System.out.println("Update Publisher name : ");
			String update_publisher = scan.next();
			
			String query="update book set publisher_book = (?) where id_book = (?)";
			 stmt = con.prepareStatement(query);
			 stmt.setString(1, update_publisher);
			 stmt.setInt(2, up_id);
			 stmt.executeUpdate();
			 System.out.println("Book's Publiser already update!!");
		}else if (choose_2==5) {
			System.out.println("Update Date of Entry : ");
			String update_entry = scan.next();
			
			String query="update book set date_come_book = (?) where id_book = (?)";
			 stmt = con.prepareStatement(query);
			 stmt.setString(1, update_entry);
			 stmt.setInt(2, up_id);
			 stmt.executeUpdate();
			 System.out.println("Book's date of entry already update!!");
		}else if(choose_2==6) {
			Choosing();
		}
		}
		
	}
	


//STEP 1. Import required packages
import java.sql.*;

public class Database {
   // JDBC driver name and database URL
   public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/first";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "1234";
   
   Connection conn = null;
   Statement stmt = null;
   
   public Database() {
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      stmt = conn.createStatement();
	      
	      System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	   System.out.println("Goodbye!");
   }
   
   public void addClient(String[] strings) {
		String user = strings[1];
		String pass = strings[2];
		String town = strings[3];
		String mail = strings[4];
		
		
		
		//STEP 4: Execute a query
	      System.out.println("Use right database...");  
	      
	      String sql = "use app";
	      try {
		      sql = "use app";
		      stmt.executeUpdate(sql);
				
		      sql = String.format("insert into clients (user, pass, town, mail) values ('%s', '%s', '%s', '%s')", user, pass, town, mail);
		      stmt.executeUpdate(sql);
	      } catch (SQLException e) {
				e.printStackTrace();
	      }
   }
   
}//end Database class
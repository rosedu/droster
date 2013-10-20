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
   String query = null;
   ResultSet rs = null;
   
   public Database() {
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      stmt = conn.createStatement();
	      
	      query = "use app";
	      stmt.executeUpdate(query);
	      
	      System.out.println("Database connection successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
   }
   
   public String addClient(String[] strings) {
		String user = strings[1];
		String pass = strings[2];
		String town = strings[3];
		String mail = strings[4];
		
		
		
		//STEP 4: Execute a query
	      System.out.println("Use right database...");  
	      try {
		      query = String.format("insert into clients (user, pass, town, mail) values ('%s', '%s', '%s', '%s')", user, pass, town, mail);
		      stmt.executeUpdate(query);
	      } catch (SQLException e) {
				e.printStackTrace();
	      }
	      System.out.println("user autentificat");
	      return "te-au autentificat";
   }

	public String loginClient(String[] strings) {
		String user = strings[1];
		String pass = strings[2];
		
		String response = "";
		boolean logged = false;
		
		query = "select * from clients";
		
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String u = rs.getString("user");
				String p = rs.getString("pass");
				
				if(user.equals(u) && pass.equals(p)) {
					System.out.println("user logat");
					response += "yes";
					logged = true;
					break;
				}
			}
			
			if(logged == false) {
				System.out.println("incercare logare user inexistent");
				response += "no&";
			}
			else {
				response += getEvents();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return response;
	}
	
	private String getEvents() {
		String events = "";
		
		try {
			query = "select * from events";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String town = rs.getString("town");
				String data = rs.getString("data");
				String creator = rs.getString("creator");
				String description = rs.getString("description");
				String guide = rs.getString("guide");
				String participants = rs.getString("participants");
				
				events += String.format("&%s:%s:%s:%s:%s:%s", town, data, creator, description, guide, participants);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return events;
	}

	public String addEvent(String[] strings) {
		String response = "";
		
		String town = strings[1];
		String data = strings[2];
		String creator = strings[3];
		String description = strings[4];
		String guide = strings[5];
		String participants = strings[6];
		
		try {
			query = String.format("insert into events (town, data, creator, description, guide, participants)" +
					" values ('%s', '%s', '%s', '%s', '%s', '%s')", town, data, creator, description, guide, participants);
			System.out.println(query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response += "yes";
		response += getEvents();
		
		return response;
	}
   
}//end Database class
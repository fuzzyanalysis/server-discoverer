

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Launcher {

	
	public Launcher() {
		
	}
	
	public static void main(String[] args) {
		
		int maxDomainCharSize = 63;
		
		
		// 1. Create All Possible Server Names  (length-based first)
		// 		a. character-size based
		//		b. exhaustive
		//		c. 
		
		

		System.out.println("-------- Oracle JDBC Connection Testing ------");
 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.105:1521:orcl", "scott",
					"Sc0ttSc0tt");
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		// 2. Store possible server names in database
		
		
		// 3. Search for servers with given criteria
		
		
		// 4. Apply Mainpoint
		
		
		// 5. Store results of server searches
		
		
		
		
		
	}

}

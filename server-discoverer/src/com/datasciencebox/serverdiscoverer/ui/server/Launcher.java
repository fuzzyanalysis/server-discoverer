
package com.datasciencebox.serverdiscoverer.ui.server;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import com.datasciencebox.serverdiscoverer.bo.server.ServerBO;
import com.datasciencebox.serverdiscoverer.dao.server.ServerDAO;


public class Launcher {

	
	public Launcher() {
		
	}
	
	public static void main(String[] args) {
		
		int maxDomainCharSize = 63;
		
		
		// 1. Create All Possible Server Names  (length-based first)
		// 		a. character-size based
		//		b. exhaustive
		//		c. 
		ServerDAO server = ServerBO.newServer("192.168.0.105");
		
		server.connect();
		
		server.getSockets();


		
		// 2. Store possible server names in database
		
		
		// 3. Search for servers with given criteria
		
		
		// 4. Apply Mainpoint
		
		
		// 5. Store results of server searches
		
		
		
		
		
	}

}

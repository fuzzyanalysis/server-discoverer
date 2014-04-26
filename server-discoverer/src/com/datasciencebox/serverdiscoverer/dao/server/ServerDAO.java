package com.datasciencebox.serverdiscoverer.dao.server;

import java.beans.Statement;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CosNaming._BindingIteratorImplBase;

public class ServerDAO {

	public String ip;
	public String domainName;
	public Map<String, String> sockets;
	
    int startPortRange=0;  
    int stopPortRange=65365;
    
    static Connection connection = null;
	
	public ServerDAO(String ip) {

		this.ip = ip;
	}
	
	
	
	public Server newServer() {
		return new Server();
	}
		
	public static boolean updateServer(Server server) {
		
		if(connect()) {
			
			try {
				String UPDATE_SERVER_SQL = "";
				
				PreparedStatement ps = connection.prepareStatement("");			
				ResultSet rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
		
		
		
	}
	
	public static boolean connect() {
		
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return false;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 		
 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.105:1521:orcl", "scott",
					"Sc0ttSc0tt");
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
 
		}
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			return true;
		} else {
			System.out.println("Failed to make connection!");
		}
		return false;
		
	}
	
	public void getSockets() {		
		
		try {
			Socket clientSocket = new Socket(ip, 80);
			System.out.println(clientSocket.getInetAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

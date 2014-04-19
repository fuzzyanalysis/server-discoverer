package com.datasciencebox.serverdiscoverer.bo.server;

import com.datasciencebox.serverdiscoverer.dao.server.ServerDAO;

public class ServerBO {

	public static ServerDAO newServer(String ip) {

		return new ServerDAO(ip);
	
	}
	
	
}

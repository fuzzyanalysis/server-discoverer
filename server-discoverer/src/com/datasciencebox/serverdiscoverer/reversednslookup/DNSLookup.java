package com.datasciencebox.serverdiscoverer.reversednslookup;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.datasciencebox.serverdiscoverer.dao.server.Server;
import com.datasciencebox.serverdiscoverer.dao.server.ServerHome;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class DNSLookup implements Runnable {

	public final String ip;
	
	DNSLookup(String ip) {
		this.ip = ip;
	}

	@Override
	public void run() {
				
        try {
        	// get InetAddress
        	InetAddress inetAddr = InetAddress.getByName(ip);
        	
        	// save to database?
        	boolean saveToDB = true;

			// Get canonical host name
			String canonicalHostname = inetAddr.getCanonicalHostName();
			 
			// get Server location
			LookupService cl = new LookupService("resources/GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE
							| LookupService.GEOIP_CHECK_CACHE);
			Location location = cl.getLocation(ip);		    			            
			
			Server server = new Server(ip);
					
			
			server.setServerIpAddress(ip);
			server.setServerDomainName(canonicalHostname);
			server.setServerLongitude(new BigDecimal(Float.toString(location.longitude)));
			server.setServerLattitude(new BigDecimal(Float.toString(location.latitude)));
			server.setServerCity(location.city);
			server.setServerCountry(location.countryName);
			server.setDateModified(new Date());
						
			System.out.println("IP: " + ip);
			System.out.println("Canonical Hostname: " + server.getServerDomainName());
			System.out.println("Lattitude, Longtitude: " + server.getServerLattitude() + ", " + server.getServerLongitude());
			System.out.println("Location: " + server.getServerCountry());
			System.out.println("==============================");	
			
			if(saveToDB) {
				
				ServerHome serverHome = new ServerHome();
				serverHome.add(server);				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


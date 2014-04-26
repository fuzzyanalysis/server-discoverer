package com.datasciencebox.serverdiscoverer.reversednslookup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;

import com.datasciencebox.serverdiscoverer.dao.server.Server;
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
			LookupService cl = new LookupService("GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE
							| LookupService.GEOIP_CHECK_CACHE);
			Location location = cl.getLocation(ip);		    			            
			
			Server server = new Server();
			
			server.setIp(ip);
			server.setDomainName(canonicalHostname);
			server.setLongtitude(location.longitude);
			server.setLattitude(location.latitude);
			server.setCountry(location.countryName);
						
			System.out.println("IP: " + ip);
			System.out.println("Canonical Hostname: " + server.getDomainName());
			System.out.println("Lattitude, Longtitude: " + server.getLattitude() + ", " + server.getLongtitude());
			System.out.println("Location: " + server.getCountry());
			System.out.println("==============================");	
			
			if(saveToDB) {
				
				
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


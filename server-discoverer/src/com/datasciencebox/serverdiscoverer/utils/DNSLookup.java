package com.datasciencebox.serverdiscoverer.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

			// Get canonical host name
			String canonicalHostname = inetAddr.getCanonicalHostName();
			 
			// get Server location
			LookupService cl = new LookupService("GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE
							| LookupService.GEOIP_CHECK_CACHE);
			Location location = cl.getLocation(ip);		    			            
			
			System.out.println("IP: " + ip);
			System.out.println("Canonical Hostname: " + canonicalHostname);
			System.out.println("Location: " + location.city + ", " + location.countryName);
			System.out.println("==============================");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


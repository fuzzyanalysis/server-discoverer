package com.datasciencebox.serverdiscoverer.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.time.TimeTCPClient;
import org.apache.commons.net.whois.WhoisClient;

public class CommonsNetDemo {

	public static void main(String[] args) {

		//exampleTimeTCPClient();
		
		exampleWhois();

	}
	
	public static void exampleTimeTCPClient() {
		
		TimeTCPClient client = new TimeTCPClient();
		try {
			// We want to timeout if a response takes longer than 60 seconds
			client.setDefaultTimeout(60000);
			client.connect("www.asu.edu");
			System.out.println(client.getDate());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void exampleWhois() {
		
        int index;
        String handle, host;
        InetAddress address = null;
        WhoisClient whois;

        whois = new WhoisClient();
        // We want to timeout if a response takes longer than 60 seconds
        whois.setDefaultTimeout(60000);
        host = "www.jcu.edu.au";

        try
        {
            address = InetAddress.getByName(host);
            System.out.println("[" + address.getHostName() + "]");
        }
        catch (UnknownHostException e)
        {
            System.err.println("Error unknown host: " + e.getMessage());
            System.exit(1);
        }

        try
        {
            whois.connect(address);
            System.out.print(whois.query("smith"));
            whois.disconnect();
        }
        catch (IOException e)
        {
            System.err.println("Error I/O exception: " + e.getMessage());
            System.exit(1);
        }
    		
	}

}

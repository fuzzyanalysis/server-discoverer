package com.datasciencebox.serverdiscoverer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

	public static void main(String[] args) throws Exception {
        
		Port ports[] = {
				new Port(20, " FTP – data port (File Transfer Protocol)"),
				new Port(21, " FTP – command port (File Transfer Protocol) "),
				new Port(22, " SSH (Secure Shell) "),
				new Port(23, " Telnet "),
				new Port(25, " SMTP – Sending Mail (Simple Mail Transfer Protocol) "),
				new Port(43, " WHOIS – Domain Name Lookup "),
				new Port(53, " DNS (Domain Name System) "),
				new Port(67, " DHCP – Server (Dynamic Host Configuration Protocol) "),
				new Port(68, " DHCP – Client (Dynamic Host Configuration Protocol) "),
				new Port(80, " HTTP – Webservers such as Apache2 and IIS – more info on accessing websites on alternate ports "),
				new Port(110, " POP3 – Receiving emails (Post Office Protocol, version 3) "),
				new Port(123, " NTP (Network Time Protocol) "),
				new Port(143, " IMAP – Receiving emails (Internet Message Access Protocol) "),
				new Port(443, " SSL – Webserver with encryption (Secure Socket Layer) . Plesk Control Panel also uses this port "),
				new Port(1433, " MSSQL – Database (Microsoft SQL Server) "),
				new Port(2082, " cPanel "),
				new Port(2083, " cPanel over SSL "),
				new Port(2095, " cPanel Webmail "),
				new Port(2096, " cPanel Webmail over SSL "),
				new Port(3128, " Squid Proxy – Default Squid Proxy Port. Port 8080 is another common port for squid. "),
				new Port(3306, " MySQL – Database "),
				new Port(8080, " HTTP Port (Alternate port generally used when port 80 is already in use – more info) "),
				new Port(8443, " Plesk Control Panel ") };	

		Server server = new Server("192.168.0.1", "localhost", null);
		
		for (int i = 0; i < ports.length; i++) {
				
			ExecutorService executor = Executors.newSingleThreadExecutor();
	        Future<?> future = executor.submit(new Scan(server.getIp(), ports[i].getPortNumber()));
	
	        try {
	            System.out.println("Started..");
	            System.out.println(future.get(10, TimeUnit.SECONDS));
	            System.out.println("Finished!");
	        } catch (TimeoutException e) {
	            System.out.println("Terminated!");
	        }
	
	        executor.shutdownNow();
		}
    }

	
	/*
	
	public static void main(String[] args) {
		// We will store the threads so that we can check if they are done
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 4; i++) {

			Runnable task = new Scan("www.a" + i + ".com");
			Thread worker = new Thread(task);
			// We can set the name of the thread
			worker.setName(String.valueOf(i));
			// Start the thread, never call method run() direct
			worker.start();
			// Remember the thread for later usage
			threads.add(worker);
		}
		long startTime = System.nanoTime();
		long endTime = 0;
		long totalTime = 0;
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					try {
						endTime = System.nanoTime();
						totalTime = endTime - startTime;
						if(totalTime > 50000) {
							thread.currentThread().stop();
						} else {
							thread.currentThread().sleep(5000);	
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("Time taken : " + totalTime);			
		} while (running > 0);

	}
	
	*/
}

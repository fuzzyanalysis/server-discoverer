package com.datasciencebox.serverdiscoverer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class Main {

	public static Port ports[] = {
			// new Port(20, " FTP – data port (File Transfer Protocol)"),
			// new Port(21, " FTP – command port (File Transfer Protocol) "),
			// new Port(22, " SSH (Secure Shell) "),
			// new Port(23, " Telnet "),
			// new Port(25,
			// " SMTP – Sending Mail (Simple Mail Transfer Protocol) "),
			// new Port(43, " WHOIS – Domain Name Lookup "),
			// new Port(53, " DNS (Domain Name System) "),
			// new Port(67,
			// " DHCP – Server (Dynamic Host Configuration Protocol) "),
			// new Port(68,
			// " DHCP – Client (Dynamic Host Configuration Protocol) "),
			new Port(
					80,
					" HTTP – Webservers such as Apache2 and IIS – more info on accessing websites on alternate ports "),
			// new Port(110,
			// " POP3 – Receiving emails (Post Office Protocol, version 3) "),
			// new Port(123, " NTP (Network Time Protocol) "),
			// new Port(143,
			// " IMAP – Receiving emails (Internet Message Access Protocol) "),
			new Port(
					443,
					" SSL – Webserver with encryption (Secure Socket Layer) . Plesk Control Panel also uses this port "),
			// new Port(1433, " MSSQL – Database (Microsoft SQL Server) "),
			// new Port(2082, " cPanel "),
			// new Port(2083, " cPanel over SSL "),
			// new Port(2095, " cPanel Webmail "),
			// new Port(2096, " cPanel Webmail over SSL "),
			// new Port(
			// 3128,
			// " Squid Proxy – Default Squid Proxy Port. Port 8080 is another common port for squid. "),
			// new Port(3306, " MySQL – Database "),
			// new Port(
			// 8080,
			// " HTTP Port (Alternate port generally used when port 80 is already in use – more info) "),
			new Port(8443, " Plesk Control Panel ") };

	public static void main(String[] args) throws Exception {

		// generate all possible ip addresses

		try {

			for (int a = 200; a < 256; a++) {
				for (int b = 200; b < 256; b++) {
					for (int c = 200; c < 256; c++) {
						for (int d = 200; d < 256; d++) {

							String ip = a + "." + b + "." + c + "." + d;

							InetAddress inetAddr = InetAddress.getByName(ip);

							// Get the host name
							String hostname = inetAddr.getHostName();

							// Get canonical host name
							String canonicalHostname = inetAddr
									.getCanonicalHostName();

							// get Server location
							LookupService cl = new LookupService(
									"GeoLiteCity.dat",
									LookupService.GEOIP_MEMORY_CACHE
											| LookupService.GEOIP_CHECK_CACHE);
							Location location = cl.getLocation(ip);

							System.out.println("IP: " + ip);							
							System.out.println("Canonical Hostname: "
									+ canonicalHostname);
							if(location!=null) {
								System.out.println(
										"\nCity: " + location.city + 
										"\nCountry: " + location.countryName + 
										"\nRegion: " + location.region + 
										"\nCoordinates: " + location.latitude + ", " + location.longitude);	
								System.out.println("");
							}

							Process process = Runtime.getRuntime().exec("traceroute " + ip);
							InputStream iStream = process.getInputStream();
							if(iStream != null) {
								InputStreamReader inputStreamReader = new InputStreamReader(iStream);
								BufferedReader bufReader = new BufferedReader(inputStreamReader);
								if(bufReader.readLine() != null) {
									while (bufReader.readLine() != null) {
									    System.out.println("PING: " + bufReader.readLine());
									}	
								} else {
									System.out.println("no ping.");
								}								
							}

							
							
							// get ping
//							String command = "ping " + ip;
//							Process process = Runtime.getRuntime ().exec (command);
//							BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
//							process.destroy();
//							StringBuffer sb = new StringBuffer();  
//							String line;  
//							while ((line = br.readLine()) != null) {  
//							  sb.append(line).append("\n");  
//							  break;
//							}  
//							String answer = sb.toString(); 
//							System.out.println(answer);

							// Scan all ports
							scanPorts(ip);

							System.out
									.println("==============================");

						}
					}
				}
			}

		} catch (UnknownHostException e) {
			System.out.println("Host not found: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void scanPorts(String ip) {

		final ExecutorService es = Executors.newFixedThreadPool(20);
		// final String ip = "127.0.0.1";
		final int timeout = 3;
		final List<Future<Boolean>> futures = new ArrayList<>();
		int checkedPorts = 0;
		//for (int port = 1; port <= 65535; port++) {
		for (int i = 0; i <= ports.length; i++) {
			int port = ports[i].getPortNumber();
			futures.add(portIsOpen(es, ip, port, timeout));
		}
		es.shutdown();
		int openPorts = 0;
		for (final Future<Boolean> f : futures) {

			try {
				checkedPorts++;
				if (f.get()) {
					openPorts++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("There are " + openPorts + " open ports on host "
				+ ip + " (probed with a timeout of " + timeout + "ms).  "
				+ checkedPorts + " ports were checked.");
	}

	public static Future<Boolean> portIsOpen(final ExecutorService es,
			final String ip, final int port, final int timeout) {
		return es.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					System.out.println(port + " ");
					socket.close();
					return true;
				} catch (Exception ex) {
					return false;
				}
			}
		});

		//
		// for (int i = 0; i < ports.length; i++) {
		//
		// try {
		// ExecutorService executor = Executors.newSingleThreadExecutor();
		// Future<?> future = executor.submit(new Scan(ip, ports[i]
		// .getPortNumber()));
		// System.out.print("-");
		// future.get(10, TimeUnit.SECONDS);
		// executor.shutdownNow();
		//
		// } catch (Exception ex) {
		// continue;
		// }
		// System.out.println("");
		//
		// }

	}

}

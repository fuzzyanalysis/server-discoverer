package com.datasciencebox.serverdiscoverer.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.net.whois.WhoisClient;

/**
 * * MyRunnable will count the sum of the number from 1 to the parameter *
 * countUntil and then write the result to the console. *
 * <p>
 * * MyRunnable is the task which will be performed * * @author Lars Vogel *
 */

public class Scan implements Runnable {
	private final String host;

	Scan(String host) {
		this.host = host;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {

		// long sum = 0;
		// for (long i = 1; i < countUntil; i++) {
		// sum += i;
		// }
		// System.out.println(sum);

		InetAddress ia = null;

		try {

			// host =
			// JOptionPane.showInputDialog("Enter the Host name to scan:\n example: xxx.com");
			if (host != null) {
				ia = InetAddress.getByName(host);

				Port ports[] = {
						new Port(20, "FTP – data port (File Transfer Protocol)"),
						new Port(21," FTP – command port (File Transfer Protocol) "),
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

				int port = 0;
				String hostname = ia.getHostName();

				for (int i = 0; i < ports.length; i++) {
					try {
						Socket s = new Socket(ia, ports[i].getPortNumber());
						System.out.println("Server is listening on port " + ports[i].getPortNumber() + " of " + hostname);
						System.out.println(ports[i].getUse());
						s.close();

					} catch (IOException ex) {
						// The remote host is not listening on this port
						System.out.println("x " + ports[i].getPortNumber() + " ( " + hostname + " ) - " + ex.getMessage());
					}
				}
			}

		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
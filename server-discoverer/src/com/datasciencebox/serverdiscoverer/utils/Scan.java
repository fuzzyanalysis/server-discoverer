package com.datasciencebox.serverdiscoverer.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import oracle.net.aso.s;

import org.apache.commons.net.whois.WhoisClient;
import org.omg.CosNaming._BindingIteratorImplBase;

/**
 * * MyRunnable will count the sum of the number from 1 to the parameter *
 * countUntil and then write the result to the console. *
 * <p>
 * * MyRunnable is the task which will be performed * * @author Lars Vogel *
 */

public class Scan implements Runnable {
	private final String host;
	private final int port;

	Scan(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {

		InetAddress ia = null;

		try {

			// host =
			// JOptionPane.showInputDialog("Enter the Host name to scan:\n example: xxx.com");

			if (host != null) {
				ia = InetAddress.getByName(host);
				Socket s;
				try {
					s = new Socket(ia, port);
					System.out.println(port + " open.");
					s.close();

				} catch (Exception ex) {
					return;
				}

			}

		} catch (UnknownHostException e) {
			System.err.println(e);
		}

	}
}
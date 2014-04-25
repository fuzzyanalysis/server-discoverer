package com.datasciencebox.serverdiscoverer.reversednslookup;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReverseDNSLookupDemo {

	public static void main(String[] args) {

		for (int a = 192; a < 256; a++) {
			for (int b = 192; b < 256; b++) {
				for (int c = 192; c < 256; c++) {
					
					int threadCount = 20;

					//ExecutorService executor = Executors.newSingleThreadExecutor();
					ExecutorService executor = Executors.newFixedThreadPool(threadCount);
					final List<Future<Boolean>> futures = new ArrayList<>();
				
					for (int d = 192; d < 256; d++) {						

						// add ip calls as threads
						for(int x=0; x<threadCount && (x+d)<256; x++) {
							String ip = a + "." + b + "." + c + "." + (d+x);
							futures.add(lookup(executor, ip));
							d++;
						}
											
					}
					
					for (final Future<Boolean> f : futures) {//
						try {
							if (f.get() !=null && f.get()) {							
								
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							//e.printStackTrace();
						}

					}
					
					executor.shutdownNow();
				}
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public static Future<Boolean> lookup(final ExecutorService es, final String ip) {
		return (Future<Boolean>) es.submit(new DNSLookup(ip));
	}

}

package com.datasciencebox.serverdiscoverer.reversednslookup;

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
					for (int d = 192; d < 256; d++) {

						String ip = a + "." + b + "." + c + "." + d;

						ExecutorService executor = Executors
								.newSingleThreadExecutor();
						Future<?> future = executor.submit(new DNSLookup(ip));

						try {

							//future.get(100, TimeUnit.SECONDS);
							future.get();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						executor.shutdownNow();

					}
				}
			}
		}

	}

}
